package com.animewatchlist.controller;

import com.animewatchlist.dto.AnimeDTO;
import com.animewatchlist.entity.Anime;
import com.animewatchlist.entity.Seiyuu;
import com.animewatchlist.entity.Studio;
import com.animewatchlist.service.ServiceAnime;
import com.animewatchlist.service.ServiceSeiyuu;
import com.animewatchlist.service.ServiceStudio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animes")
public class ControleurAnime {
    
    private final ServiceAnime serviceAnime;
    private final ServiceSeiyuu serviceSeiyuu;
    private final ServiceStudio serviceStudio;
    
    public ControleurAnime(ServiceAnime serviceAnime, ServiceSeiyuu serviceSeiyuu, ServiceStudio serviceStudio) {
        this.serviceAnime = serviceAnime;
        this.serviceSeiyuu = serviceSeiyuu;
        this.serviceStudio = serviceStudio;
    }
    
    @GetMapping
    public List<Anime> obtenirTousLesAnimes() {
        return serviceAnime.obtenirTousLesAnimes();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Anime> obtenirAnimeParId(@PathVariable Long id) {
        Optional<Anime> anime = serviceAnime.obtenirAnimeParId(id);
        return anime.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Anime creerAnime(@RequestBody AnimeDTO animeDTO) {
        // Création de l'objet Anime
        Anime anime = new Anime();
        anime.setTitre(animeDTO.getTitre());
        anime.setSynopsis(animeDTO.getSynopsis());
        anime.setGenre(animeDTO.getGenre());
        anime.setAnnee(animeDTO.getAnnee());
        anime.setSaison(animeDTO.getSaison());
        anime.setNombreEpisodes(animeDTO.getNombreEpisodes());
        anime.setStatut(animeDTO.getStatut());
        anime.setType(animeDTO.getType());
        anime.setSource(animeDTO.getSource());
        anime.setClassification(animeDTO.getClassification());
        anime.setDuree(animeDTO.getDuree());
        anime.setGenres(animeDTO.getGenres());
        anime.setUrlImage(animeDTO.getUrlImage());
        anime.setNoteMoyenne(animeDTO.getNoteMoyenne());
        anime.setClassement(animeDTO.getClassement());
        anime.setPopularite(animeDTO.getPopularite());
        anime.setNombreMembres(animeDTO.getNombreMembres());
        anime.setNombreFavoris(animeDTO.getNombreFavoris());
        anime.setWishlist(animeDTO.getWishlist() != null ? animeDTO.getWishlist() : false);
        if (animeDTO.getEtatVisionnage() != null) {
            try {
                anime.setEtatVisionnage(Anime.EtatVisionnage.valueOf(animeDTO.getEtatVisionnage()));
            } catch (IllegalArgumentException e) {
                anime.setEtatVisionnage(Anime.EtatVisionnage.WISHLIST);
            }
        } else {
            anime.setEtatVisionnage(Anime.EtatVisionnage.WISHLIST);
        }
        anime.setAvis(animeDTO.getAvis());

        // Gestion des seiyuus
        List<Seiyuu> seiyuus = new ArrayList<>();
        if (animeDTO.getSeiyuuIds() != null) {
            for (Long seiyuuId : animeDTO.getSeiyuuIds()) {
                serviceSeiyuu.obtenirSeiyuuParId(seiyuuId).ifPresent(seiyuus::add);
            }
        }
        if (animeDTO.getNouveauxSeiyuus() != null) {
            for (Seiyuu nouveau : animeDTO.getNouveauxSeiyuus()) {
                seiyuus.add(serviceSeiyuu.sauvegarderSeiyuu(nouveau));
            }
        }
        anime.setSeiyuus(seiyuus);

        // Gestion des studios
        List<Studio> studios = new ArrayList<>();
        if (animeDTO.getStudioIds() != null) {
            for (Long studioId : animeDTO.getStudioIds()) {
                serviceStudio.obtenirStudioParId(studioId).ifPresent(studios::add);
            }
        }
        if (animeDTO.getNouveauxStudios() != null) {
            for (Studio nouveau : animeDTO.getNouveauxStudios()) {
                studios.add(serviceStudio.sauvegarderStudio(nouveau));
            }
        }
        anime.setStudios(studios);

        // TODO: Gérer les studios de la même façon si besoin

        return serviceAnime.sauvegarderAnime(anime);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Anime> mettreAJourAnime(@PathVariable Long id, @RequestBody Anime anime) {
        if (!serviceAnime.existeAnime(id)) {
            return ResponseEntity.notFound().build();
        }
        anime.setId(id);
        return ResponseEntity.ok(serviceAnime.sauvegarderAnime(anime));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerAnime(@PathVariable Long id) {
        if (!serviceAnime.existeAnime(id)) {
            return ResponseEntity.notFound().build();
        }
        serviceAnime.supprimerAnime(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/recherche")
    public List<Anime> rechercherAnimes(@RequestParam String titre) {
        return serviceAnime.rechercherAnimesParTitre(titre);
    }
    
    @GetMapping("/annee/{annee}")
    public List<Anime> obtenirAnimesParAnnee(@PathVariable Integer annee) {
        return serviceAnime.obtenirAnimesParAnnee(annee);
    }
    
    @GetMapping("/statut/{statut}")
    public List<Anime> obtenirAnimesParStatut(@PathVariable String statut) {
        return serviceAnime.obtenirAnimesParStatut(statut);
    }
    
    @GetMapping("/type/{type}")
    public List<Anime> obtenirAnimesParType(@PathVariable String type) {
        return serviceAnime.obtenirAnimesParType(type);
    }
    
    @GetMapping("/genre/{genre}")
    public List<Anime> obtenirAnimesParGenre(@PathVariable String genre) {
        return serviceAnime.obtenirAnimesParGenre(genre);
    }
    
    @GetMapping("/studio/{studioId}")
    public List<Anime> obtenirAnimesParStudioId(@PathVariable Long studioId) {
        return serviceAnime.obtenirAnimesParStudioId(studioId);
    }
    
    @GetMapping("/studio-nom/{studioNom}")
    public List<Anime> obtenirAnimesParStudioNom(@PathVariable String studioNom) {
        return serviceAnime.obtenirAnimesParStudioNom(studioNom);
    }
    
    @GetMapping("/seiyuu/{seiyuuId}")
    public List<Anime> obtenirAnimesParSeiyuuId(@PathVariable Long seiyuuId) {
        return serviceAnime.obtenirAnimesParSeiyuuId(seiyuuId);
    }
    
    @GetMapping("/seiyuu-nom")
    public List<Anime> obtenirAnimesParSeiyuuNomPrenom(@RequestParam String nom, @RequestParam String prenom) {
        return serviceAnime.obtenirAnimesParSeiyuuNomPrenom(nom, prenom);
    }
    
    @GetMapping("/collaboration")
    public List<Anime> obtenirAnimesAvecMultiplesSeiyuus(@RequestParam Long seiyuu1Id, @RequestParam Long seiyuu2Id) {
        return serviceAnime.obtenirAnimesAvecMultiplesSeiyuus(seiyuu1Id, seiyuu2Id);
    }
    
    @GetMapping("/top-notes")
    public List<Anime> obtenirAnimesMieuxNotes() {
        return serviceAnime.obtenirAnimesMieuxNotes();
    }
    
    @GetMapping("/populaires")
    public List<Anime> obtenirAnimesPlusPopulaires() {
        return serviceAnime.obtenirAnimesPlusPopulaires();
    }
} 