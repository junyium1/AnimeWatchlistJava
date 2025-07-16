package com.animewatchlist.service;

import com.animewatchlist.entity.Studio;
import com.animewatchlist.repository.StudioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceStudio {
    
    private final StudioRepository studioRepository;
    
    public ServiceStudio(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }
    
    public List<Studio> obtenirTousLesStudios() {
        return studioRepository.findAll();
    }
    
    public Optional<Studio> obtenirStudioParId(Long id) {
        return studioRepository.findById(id);
    }
    
    public List<Studio> rechercherStudiosParNom(String nom) {
        return studioRepository.findByNomContainingIgnoreCase(nom);
    }
    
    public List<Studio> obtenirStudiosParStatut(String statut) {
        return studioRepository.findByStatut(statut);
    }
    
    public List<Studio> obtenirStudiosParSiege(String siege) {
        return studioRepository.findBySiegeContainingIgnoreCase(siege);
    }
    
    public List<Studio> obtenirStudiosParFondateur(String fondateur) {
        return studioRepository.findByFondateurContainingIgnoreCase(fondateur);
    }
    
    public List<Studio> obtenirStudiosParAnime(Long animeId) {
        return studioRepository.findByAnimeId(animeId);
    }
    
    public List<Studio> obtenirStudiosLesPlusProductifs() {
        return studioRepository.findMostProductive();
    }
    
    public List<Studio> obtenirStudiosActifs() {
        return studioRepository.findActiveStudios();
    }
    
    public Studio sauvegarderStudio(Studio studio) {
        return studioRepository.save(studio);
    }
    
    public void supprimerStudio(Long id) {
        studioRepository.deleteById(id);
    }
    
    public boolean existeStudio(Long id) {
        return studioRepository.existsById(id);
    }
} 