package com.animewatchlist.service;

import com.animewatchlist.entity.Anime;
import com.animewatchlist.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAnime {
    
    private final AnimeRepository animeRepository;
    
    public ServiceAnime(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }
    
    public List<Anime> obtenirTousLesAnimes() {
        return animeRepository.findAll();
    }
    
    public Optional<Anime> obtenirAnimeParId(Long id) {
        return animeRepository.findById(id);
    }
    
    public List<Anime> rechercherAnimesParTitre(String titre) {
        return animeRepository.findByTitreContainingIgnoreCase(titre);
    }
    
    public List<Anime> obtenirAnimesParAnnee(Integer annee) {
        return animeRepository.findByAnnee(annee);
    }
    
    public List<Anime> obtenirAnimesParStatut(String statut) {
        return animeRepository.findByStatut(statut);
    }
    
    public List<Anime> obtenirAnimesParType(String type) {
        return animeRepository.findByType(type);
    }
    
    public List<Anime> obtenirAnimesParGenre(String genre) {
        return animeRepository.findByGenre(genre);
    }
    
    public List<Anime> obtenirAnimesParStudioId(Long studioId) {
        return animeRepository.findByStudioId(studioId);
    }
    
    public List<Anime> obtenirAnimesParStudioNom(String studioNom) {
        return animeRepository.findByStudioNom(studioNom);
    }
    
    public List<Anime> obtenirAnimesParSeiyuuId(Long seiyuuId) {
        return animeRepository.findBySeiyuuId(seiyuuId);
    }
    
    public List<Anime> obtenirAnimesParSeiyuuNomPrenom(String nom, String prenom) {
        return animeRepository.findBySeiyuuNomPrenom(nom, prenom);
    }
    
    public List<Anime> obtenirAnimesAvecMultiplesSeiyuus(Long seiyuu1Id, Long seiyuu2Id) {
        return animeRepository.findByMultipleSeiyuus(seiyuu1Id, seiyuu2Id);
    }
    
    public List<Anime> obtenirAnimesMieuxNotes() {
        return animeRepository.findByOrderByNoteMoyenneDesc();
    }
    
    public List<Anime> obtenirAnimesPlusPopulaires() {
        return animeRepository.findByOrderByPopulariteDesc();
    }
    
    public Anime sauvegarderAnime(Anime anime) {
        return animeRepository.save(anime);
    }
    
    public void supprimerAnime(Long id) {
        animeRepository.deleteById(id);
    }
    
    public boolean existeAnime(Long id) {
        return animeRepository.existsById(id);
    }
} 