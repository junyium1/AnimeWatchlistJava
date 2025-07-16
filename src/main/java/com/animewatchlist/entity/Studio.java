package com.animewatchlist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "studios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Studio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String nom;
    
    private LocalDate dateFondation;
    
    private String fondateur;
    
    private String siege; // Siège
    
    @Column(length = 1000)
    private String description;
    
    private String urlImage;
    
    private String siteWeb;
    
    private String statut; // Statut
    
    @ManyToMany(mappedBy = "studios", fetch = FetchType.LAZY)
    private List<Anime> animes;
} 