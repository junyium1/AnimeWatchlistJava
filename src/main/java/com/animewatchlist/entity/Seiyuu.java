package com.animewatchlist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "seiyuus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seiyuu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;
    
    private String nomJaponais;
    
    private LocalDate dateNaissance;
    
    private String lieuNaissance;
    
    @Column(length = 1000)
    private String biographie;
    
    private String urlImage;
    
    private String agence; // Agence
    
    private String site; // Site web
    
    @ManyToMany(mappedBy = "seiyuus", fetch = FetchType.LAZY)
    private List<Anime> animes;
    
    // Méthode utilitaire pour obtenir le nom complet
    public String getNomComplet() {
        return prenom + " " + nom;
    }
} 