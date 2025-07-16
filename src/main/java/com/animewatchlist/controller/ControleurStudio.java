package com.animewatchlist.controller;

import com.animewatchlist.entity.Studio;
import com.animewatchlist.service.ServiceStudio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/studios")
public class ControleurStudio {
    
    private final ServiceStudio serviceStudio;
    
    public ControleurStudio(ServiceStudio serviceStudio) {
        this.serviceStudio = serviceStudio;
    }
    
    @GetMapping
    public List<Studio> obtenirTousLesStudios() {
        return serviceStudio.obtenirTousLesStudios();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Studio> obtenirStudioParId(@PathVariable Long id) {
        Optional<Studio> studio = serviceStudio.obtenirStudioParId(id);
        return studio.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Studio creerStudio(@RequestBody Studio studio) {
        return serviceStudio.sauvegarderStudio(studio);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Studio> mettreAJourStudio(@PathVariable Long id, @RequestBody Studio studio) {
        if (!serviceStudio.existeStudio(id)) {
            return ResponseEntity.notFound().build();
        }
        studio.setId(id);
        return ResponseEntity.ok(serviceStudio.sauvegarderStudio(studio));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerStudio(@PathVariable Long id) {
        if (!serviceStudio.existeStudio(id)) {
            return ResponseEntity.notFound().build();
        }
        serviceStudio.supprimerStudio(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/recherche")
    public List<Studio> rechercherStudios(@RequestParam String nom) {
        return serviceStudio.rechercherStudiosParNom(nom);
    }
    
    @GetMapping("/statut/{statut}")
    public List<Studio> obtenirStudiosParStatut(@PathVariable String statut) {
        return serviceStudio.obtenirStudiosParStatut(statut);
    }
    
    @GetMapping("/siege/{siege}")
    public List<Studio> obtenirStudiosParSiege(@PathVariable String siege) {
        return serviceStudio.obtenirStudiosParSiege(siege);
    }
    
    @GetMapping("/fondateur/{fondateur}")
    public List<Studio> obtenirStudiosParFondateur(@PathVariable String fondateur) {
        return serviceStudio.obtenirStudiosParFondateur(fondateur);
    }
    
    @GetMapping("/anime/{animeId}")
    public List<Studio> obtenirStudiosParAnime(@PathVariable Long animeId) {
        return serviceStudio.obtenirStudiosParAnime(animeId);
    }
    
    @GetMapping("/plus-productifs")
    public List<Studio> obtenirStudiosLesPlusProductifs() {
        return serviceStudio.obtenirStudiosLesPlusProductifs();
    }
    
    @GetMapping("/actifs")
    public List<Studio> obtenirStudiosActifs() {
        return serviceStudio.obtenirStudiosActifs();
    }
} 