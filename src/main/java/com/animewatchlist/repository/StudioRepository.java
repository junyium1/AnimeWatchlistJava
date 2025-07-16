package com.animewatchlist.repository;

import com.animewatchlist.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
    
    List<Studio> findByNomContainingIgnoreCase(String nom);
    
    List<Studio> findByStatut(String statut);
    
    List<Studio> findBySiegeContainingIgnoreCase(String siege);
    
    List<Studio> findByFondateurContainingIgnoreCase(String fondateur);
    
    @Query("SELECT s FROM Studio s JOIN s.animes a WHERE a.id = :animeId")
    List<Studio> findByAnimeId(@Param("animeId") Long animeId);
    
    // Studios les plus productifs
    @Query("SELECT s FROM Studio s LEFT JOIN s.animes a GROUP BY s ORDER BY COUNT(a) DESC")
    List<Studio> findMostProductive();
    
    // Studios actifs
    @Query("SELECT DISTINCT s FROM Studio s JOIN s.animes a")
    List<Studio> findActiveStudios();
} 