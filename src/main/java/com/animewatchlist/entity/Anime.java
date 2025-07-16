package com.animewatchlist.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "animes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anime {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titre;
    
    @Column(length = 1000)
    private String synopsis;
    
    private String genre;
    
    private Integer annee;
    
    private String saison; // Saison
    
    private Integer nombreEpisodes;
    
    private String statut; // Statut
    
    private String type; // Type
    
    private String source; // Source
    
    private String classification; // Classification
    
    private String duree; // Durée
    
    private Boolean wishlist = false; // true si dans la wishlist

    public enum EtatVisionnage {
        WISHLIST, // à regarder plus tard
        WATCHING, // en cours
        COMPLETED // terminé
    }

    @Enumerated(EnumType.STRING)
    private EtatVisionnage etatVisionnage = EtatVisionnage.WISHLIST;

    @Column(length = 2000)
    private String avis; // Avis laissé par l'utilisateur
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "anime_seiyuu",
        joinColumns = @JoinColumn(name = "anime_id"),
        inverseJoinColumns = @JoinColumn(name = "seiyuu_id")
    )
    private List<Seiyuu> seiyuus;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "anime_studio",
        joinColumns = @JoinColumn(name = "anime_id"),
        inverseJoinColumns = @JoinColumn(name = "studio_id")
    )
    private List<Studio> studios;
    
    @ElementCollection
    @CollectionTable(name = "anime_genres", joinColumns = @JoinColumn(name = "anime_id"))
    @Column(name = "genre")
    private List<String> genres;
    
    private String urlImage;
    
    private Double noteMoyenne;
    
    private Integer classement;
    
    private Integer popularite;
    
    private Integer nombreMembres;
    
    private Integer nombreFavoris;

    public void setGenre(String genre) { this.genre = genre; }
    public void setSaison(String saison) { this.saison = saison; }
    public void setAnnee(Integer annee) { this.annee = annee; }
    public void setGenres(List<String> genres) { this.genres = genres; }
    public void setUrlImage(String urlImage) { this.urlImage = urlImage; }
    public void setClassement(Integer classement) { this.classement = classement; }
    public void setNombreMembres(Integer nombreMembres) { this.nombreMembres = nombreMembres; }
    public void setNombreFavoris(Integer nombreFavoris) { this.nombreFavoris = nombreFavoris; }
    public void setSeiyuus(List<Seiyuu> seiyuus) { this.seiyuus = seiyuus; }
    public void setStudios(List<Studio> studios) { this.studios = studios; }
    public void setId(Long id) { this.id = id; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public void setNombreEpisodes(Integer nombreEpisodes) { this.nombreEpisodes = nombreEpisodes; }
    public void setStatut(String statut) { this.statut = statut; }
    public void setType(String type) { this.type = type; }
    public void setSource(String source) { this.source = source; }
    public void setClassification(String classification) { this.classification = classification; }
    public void setDuree(String duree) { this.duree = duree; }
    public void setNoteMoyenne(Double noteMoyenne) { this.noteMoyenne = noteMoyenne; }
    public void setPopularite(Integer popularite) { this.popularite = popularite; }
    public void setWishlist(Boolean wishlist) { this.wishlist = wishlist; }
    public void setEtatVisionnage(EtatVisionnage etatVisionnage) { this.etatVisionnage = etatVisionnage; }
    public void setAvis(String avis) { this.avis = avis; }
} 