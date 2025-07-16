package com.animewatchlist.dto;

import com.animewatchlist.entity.Seiyuu;
import com.animewatchlist.entity.Studio;
import java.util.List;

public class AnimeDTO {
    private String titre;
    private String synopsis;
    private String genre;
    private Integer annee;
    private String saison;
    private Integer nombreEpisodes;
    private String statut;
    private String type;
    private String source;
    private String classification;
    private String duree;
    private List<Long> seiyuuIds; // Pour sélectionner des seiyuus existants
    private List<Seiyuu> nouveauxSeiyuus; // Pour créer de nouveaux seiyuus
    private List<Long> studioIds;
    private List<Studio> nouveauxStudios; // Pour créer de nouveaux studios
    private List<String> genres;
    private String urlImage;
    private Double noteMoyenne;
    private Integer classement;
    private Integer popularite;
    private Integer nombreMembres;
    private Integer nombreFavoris;
    private Boolean wishlist;
    private String etatVisionnage;
    private String avis;

    // Getters et setters
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public Integer getAnnee() { return annee; }
    public void setAnnee(Integer annee) { this.annee = annee; }
    public String getSaison() { return saison; }
    public void setSaison(String saison) { this.saison = saison; }
    public Integer getNombreEpisodes() { return nombreEpisodes; }
    public void setNombreEpisodes(Integer nombreEpisodes) { this.nombreEpisodes = nombreEpisodes; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getClassification() { return classification; }
    public void setClassification(String classification) { this.classification = classification; }
    public String getDuree() { return duree; }
    public void setDuree(String duree) { this.duree = duree; }
    public List<Long> getSeiyuuIds() { return seiyuuIds; }
    public void setSeiyuuIds(List<Long> seiyuuIds) { this.seiyuuIds = seiyuuIds; }
    public List<Seiyuu> getNouveauxSeiyuus() { return nouveauxSeiyuus; }
    public void setNouveauxSeiyuus(List<Seiyuu> nouveauxSeiyuus) { this.nouveauxSeiyuus = nouveauxSeiyuus; }
    public List<Long> getStudioIds() { return studioIds; }
    public void setStudioIds(List<Long> studioIds) { this.studioIds = studioIds; }
    public List<Studio> getNouveauxStudios() { return nouveauxStudios; }
    public void setNouveauxStudios(List<Studio> nouveauxStudios) { this.nouveauxStudios = nouveauxStudios; }
    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }
    public String getUrlImage() { return urlImage; }
    public void setUrlImage(String urlImage) { this.urlImage = urlImage; }
    public Double getNoteMoyenne() { return noteMoyenne; }
    public void setNoteMoyenne(Double noteMoyenne) { this.noteMoyenne = noteMoyenne; }
    public Integer getClassement() { return classement; }
    public void setClassement(Integer classement) { this.classement = classement; }
    public Integer getPopularite() { return popularite; }
    public void setPopularite(Integer popularite) { this.popularite = popularite; }
    public Integer getNombreMembres() { return nombreMembres; }
    public void setNombreMembres(Integer nombreMembres) { this.nombreMembres = nombreMembres; }
    public Integer getNombreFavoris() { return nombreFavoris; }
    public void setNombreFavoris(Integer nombreFavoris) { this.nombreFavoris = nombreFavoris; }
    public Boolean getWishlist() { return wishlist; }
    public void setWishlist(Boolean wishlist) { this.wishlist = wishlist; }
    public String getEtatVisionnage() { return etatVisionnage; }
    public void setEtatVisionnage(String etatVisionnage) { this.etatVisionnage = etatVisionnage; }
    public String getAvis() { return avis; }
    public void setAvis(String avis) { this.avis = avis; }
} 