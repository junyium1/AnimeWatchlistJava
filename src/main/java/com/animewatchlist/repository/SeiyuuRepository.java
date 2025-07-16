package com.animewatchlist.repository;

import com.animewatchlist.entity.Seiyuu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeiyuuRepository extends JpaRepository<Seiyuu, Long> {
    
    List<Seiyuu> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);
    
    List<Seiyuu> findByNomJaponaisContainingIgnoreCase(String nomJaponais);
    
    List<Seiyuu> findByAgenceContainingIgnoreCase(String agence);
    
    @Query("SELECT s FROM Seiyuu s JOIN s.animes a WHERE a.id = :animeId")
    List<Seiyuu> findByAnimeId(@Param("animeId") Long animeId);
    
    // Collaborateurs
    @Query("SELECT s FROM Seiyuu s WHERE s.id != :seiyuuId AND EXISTS " +
           "(SELECT a FROM Anime a JOIN a.seiyuus s1 JOIN a.seiyuus s2 " +
           "WHERE s1.id = :seiyuuId AND s2.id = s.id)")
    List<Seiyuu> findCollaborateurs(@Param("seiyuuId") Long seiyuuId);
    
    // Nombre de collaborations
    @Query("SELECT COUNT(a) FROM Anime a JOIN a.seiyuus s1 JOIN a.seiyuus s2 " +
           "WHERE s1.id = :seiyuu1Id AND s2.id = :seiyuu2Id")
    Long countCollaborations(@Param("seiyuu1Id") Long seiyuu1Id, @Param("seiyuu2Id") Long seiyuu2Id);
    
    // Seiyuus les plus actifs
    @Query("SELECT s FROM Seiyuu s LEFT JOIN s.animes a GROUP BY s ORDER BY COUNT(a) DESC")
    List<Seiyuu> findMostActive();
} 