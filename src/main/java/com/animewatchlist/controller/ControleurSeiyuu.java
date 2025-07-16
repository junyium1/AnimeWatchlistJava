package com.animewatchlist.controller;

import com.animewatchlist.entity.Seiyuu;
import com.animewatchlist.service.ServiceSeiyuu;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seiyuus")
public class ControleurSeiyuu {
    
    private final ServiceSeiyuu serviceSeiyuu;
    
    public ControleurSeiyuu(ServiceSeiyuu serviceSeiyuu) {
        this.serviceSeiyuu = serviceSeiyuu;
    }
    
    @GetMapping
    public List<Seiyuu> obtenirTousLesSeiyuus() {
        return serviceSeiyuu.obtenirTousLesSeiyuus();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Seiyuu> obtenirSeiyuuParId(@PathVariable Long id) {
        Optional<Seiyuu> seiyuu = serviceSeiyuu.obtenirSeiyuuParId(id);
        return seiyuu.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Seiyuu creerSeiyuu(@RequestBody Seiyuu seiyuu) {
        return serviceSeiyuu.sauvegarderSeiyuu(seiyuu);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Seiyuu> mettreAJourSeiyuu(@PathVariable Long id, @RequestBody Seiyuu seiyuu) {
        if (!serviceSeiyuu.existeSeiyuu(id)) {
            return ResponseEntity.notFound().build();
        }
        seiyuu.setId(id);
        return ResponseEntity.ok(serviceSeiyuu.sauvegarderSeiyuu(seiyuu));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerSeiyuu(@PathVariable Long id) {
        if (!serviceSeiyuu.existeSeiyuu(id)) {
            return ResponseEntity.notFound().build();
        }
        serviceSeiyuu.supprimerSeiyuu(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/recherche")
    public List<Seiyuu> rechercherSeiyuus(@RequestParam String nom) {
        return serviceSeiyuu.rechercherSeiyuusParNom(nom);
    }
    
    @GetMapping("/recherche-japonais")
    public List<Seiyuu> rechercherSeiyuusParNomJaponais(@RequestParam String nomJaponais) {
        return serviceSeiyuu.rechercherSeiyuusParNomJaponais(nomJaponais);
    }
    
    @GetMapping("/agence/{agence}")
    public List<Seiyuu> obtenirSeiyuusParAgence(@PathVariable String agence) {
        return serviceSeiyuu.obtenirSeiyuusParAgence(agence);
    }
    
    @GetMapping("/anime/{animeId}")
    public List<Seiyuu> obtenirSeiyuusParAnime(@PathVariable Long animeId) {
        return serviceSeiyuu.obtenirSeiyuusParAnime(animeId);
    }
    
    @GetMapping("/{id}/collaborateurs")
    public List<Seiyuu> obtenirCollaborateurs(@PathVariable Long id) {
        return serviceSeiyuu.obtenirCollaborateurs(id);
    }
    
    @GetMapping("/collaborations")
    public ResponseEntity<Long> compterCollaborations(@RequestParam Long seiyuu1Id, @RequestParam Long seiyuu2Id) {
        Long count = serviceSeiyuu.compterCollaborations(seiyuu1Id, seiyuu2Id);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/plus-actifs")
    public List<Seiyuu> obtenirSeiyuusLesPlusActifs() {
        return serviceSeiyuu.obtenirSeiyuusLesPlusActifs();
    }
} 