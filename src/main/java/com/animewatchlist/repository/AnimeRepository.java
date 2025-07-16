package com.animewatchlist.repository;

import com.animewatchlist.entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    
    List<Anime> findByTitreContainingIgnoreCase(String titre);
    
    List<Anime> findByAnnee(Integer annee);
    
    List<Anime> findByStatut(String statut);
    
    List<Anime> findByType(String type);
    
    @Query("SELECT a FROM Anime a JOIN a.genres g WHERE g = :genre")
    List<Anime> findByGenre(@Param("genre") String genre);
    
    @Query("SELECT a FROM Anime a JOIN a.studios s WHERE s.id = :studioId")
    List<Anime> findByStudioId(@Param("studioId") Long studioId);
    
    @Query("SELECT a FROM Anime a JOIN a.studios s WHERE s.nom = :studioNom")
    List<Anime> findByStudioNom(@Param("studioNom") String studioNom);
    
    @Query("SELECT a FROM Anime a JOIN a.seiyuus s WHERE s.id = :seiyuuId")
    List<Anime> findBySeiyuuId(@Param("seiyuuId") Long seiyuuId);
    
    @Query("SELECT a FROM Anime a JOIN a.seiyuus s WHERE s.nom = :nom AND s.prenom = :prenom")
    List<Anime> findBySeiyuuNomPrenom(@Param("nom") String nom, @Param("prenom") String prenom);
    
    List<Anime> findByOrderByNoteMoyenneDesc();
    
    List<Anime> findByOrderByPopulariteDesc();
    
    // Animes avec plusieurs seiyuus
    @Query("SELECT a FROM Anime a JOIN a.seiyuus s1 JOIN a.seiyuus s2 WHERE s1.id = :seiyuu1Id AND s2.id = :seiyuu2Id")
    List<Anime> findByMultipleSeiyuus(@Param("seiyuu1Id") Long seiyuu1Id, @Param("seiyuu2Id") Long seiyuu2Id);
} 