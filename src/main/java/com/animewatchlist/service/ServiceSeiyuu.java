package com.animewatchlist.service;

import com.animewatchlist.entity.Seiyuu;
import com.animewatchlist.repository.SeiyuuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSeiyuu {
    
    private final SeiyuuRepository seiyuuRepository;
    
    public ServiceSeiyuu(SeiyuuRepository seiyuuRepository) {
        this.seiyuuRepository = seiyuuRepository;
    }
    
    public List<Seiyuu> obtenirTousLesSeiyuus() {
        return seiyuuRepository.findAll();
    }
    
    public Optional<Seiyuu> obtenirSeiyuuParId(Long id) {
        return seiyuuRepository.findById(id);
    }
    
    public List<Seiyuu> rechercherSeiyuusParNom(String nom) {
        return seiyuuRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(nom, nom);
    }
    
    public List<Seiyuu> rechercherSeiyuusParNomJaponais(String nomJaponais) {
        return seiyuuRepository.findByNomJaponaisContainingIgnoreCase(nomJaponais);
    }
    
    public List<Seiyuu> obtenirSeiyuusParAgence(String agence) {
        return seiyuuRepository.findByAgenceContainingIgnoreCase(agence);
    }
    
    public List<Seiyuu> obtenirSeiyuusParAnime(Long animeId) {
        return seiyuuRepository.findByAnimeId(animeId);
    }
    
    public List<Seiyuu> obtenirCollaborateurs(Long seiyuuId) {
        return seiyuuRepository.findCollaborateurs(seiyuuId);
    }
    
    public Long compterCollaborations(Long seiyuu1Id, Long seiyuu2Id) {
        return seiyuuRepository.countCollaborations(seiyuu1Id, seiyuu2Id);
    }
    
    public List<Seiyuu> obtenirSeiyuusLesPlusActifs() {
        return seiyuuRepository.findMostActive();
    }
    
    public Seiyuu sauvegarderSeiyuu(Seiyuu seiyuu) {
        return seiyuuRepository.save(seiyuu);
    }
    
    public void supprimerSeiyuu(Long id) {
        seiyuuRepository.deleteById(id);
    }
    
    public boolean existeSeiyuu(Long id) {
        return seiyuuRepository.existsById(id);
    }
} 