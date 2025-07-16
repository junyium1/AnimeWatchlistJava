# Anime Watchlist - Documentation complète

## Table des matières
- [Vue d'ensemble](#vue-densemble)
- [Structure des dossiers](#structure-des-dossiers)
- [Explication détaillée des fichiers](#explication-détaillée-des-fichiers)
  - [controller/](#controller)
  - [entity/](#entity)
  - [repository/](#repository)
  - [service/](#service)
  - [dto/](#dto)
  - [config/](#config)
  - [templates/](#templates)
  - [AnimeWatchlistApplication.java](#animewatchlistapplicationjava)
- [Exemples de flux complets](#exemples-de-flux-complets)
- [Conseils pour contribuer et tester](#conseils-pour-contribuer-et-tester)

---

## Vue d'ensemble

Ce projet est une application de gestion de watchlist d'animes développée avec **Spring Boot** (architecture MVC), **Spring Data JPA** (accès base H2 ou autre), et un front simple en HTML/CSS/JS (Thymeleaf ou statique). L'API est RESTful et le front communique avec elle via fetch/ajax.

---

## Structure des dossiers

- **controller/** : Contrôleurs REST et web (routes API et pages HTML)
- **entity/** : Entités JPA (tables de la base)
- **repository/** : Interfaces d'accès aux données (Spring Data JPA)
- **service/** : Logique métier (traitement, validation, orchestration)
- **dto/** : Objets de transfert de données (payloads API)
- **config/** : (À compléter) Pour la configuration avancée Spring Boot
- **templates/** : Fichiers HTML pour le front (dashboard, seiyuus, studios)

---

## Explication détaillée des fichiers

### controller/

#### ControleurAnime.java
- **Rôle** : Gère toutes les routes `/api/animes` (CRUD, recherche, filtrage par seiyuu/studio, etc.).
- **Principales méthodes** :
  - `GET /api/animes` : liste tous les animes
  - `POST /api/animes` : ajoute un anime (via AnimeDTO)
  - `PUT /api/animes/{id}` : modifie un anime
  - `DELETE /api/animes/{id}` : supprime un anime
  - Recherche par titre, année, statut, type, genre, studio, seiyuu, etc.
- **Exemple** :
```java
@PostMapping
public Anime creerAnime(@RequestBody AnimeDTO animeDTO) {
    // ... création de l'anime à partir du DTO ...
    return serviceAnime.sauvegarderAnime(anime);
}
```

#### ControleurSeiyuu.java
- **Rôle** : Gère `/api/seiyuus` (CRUD, recherche par nom, agence, etc.)
- **Endpoints** :
  - `GET /api/seiyuus` : liste tous les seiyuus
  - `POST /api/seiyuus` : ajoute un seiyuu
  - `PUT /api/seiyuus/{id}` : modifie un seiyuu
  - `DELETE /api/seiyuus/{id}` : supprime un seiyuu
  - Recherche par nom, agence, anime, etc.

#### ControleurStudio.java
- **Rôle** : Gère `/api/studios` (CRUD, recherche par nom, statut, etc.)
- **Endpoints** :
  - `GET /api/studios` : liste tous les studios
  - `POST /api/studios` : ajoute un studio
  - `PUT /api/studios/{id}` : modifie un studio
  - `DELETE /api/studios/{id}` : supprime un studio
  - Recherche par nom, statut, siège, fondateur, anime, etc.

#### ControleurWeb.java
- **Rôle** : Sert les pages HTML (`/dashboard`, `/seiyuus`, `/studios`)
- **Exemple** :
```java
@GetMapping("/dashboard")
public String dashboard() {
    return "dashboard";
}
```

---

### entity/

#### Anime.java
- **Rôle** : Représente un anime (table principale)
- **Champs principaux** :
  - `id`, `titre`, `synopsis`, `annee`, `saison`, `statut`, `type`, `genres`, etc.
  - Relations :
    - `List<Seiyuu> seiyuus` (many-to-many)
    - `List<Studio> studios` (many-to-many)
- **Exemple de JSON** :
```json
{
  "id": 1,
  "titre": "Naruto",
  "annee": 2002,
  "statut": "Terminé",
  "seiyuus": [ { "id": 1, "nom": "Takeuchi", "prenom": "Junko" } ],
  "studios": [ { "id": 1, "nom": "Pierrot" } ]
}
```

#### Seiyuu.java
- **Rôle** : Représente un doubleur
- **Champs** : `id`, `nom`, `prenom`, `biographie`, `agence`, `site`, etc.
- **Relation** : `List<Anime> animes` (many-to-many)

#### Studio.java
- **Rôle** : Représente un studio d'animation
- **Champs** : `id`, `nom`, `fondateur`, `siege`, `statut`, etc.
- **Relation** : `List<Anime> animes` (many-to-many)

---

### repository/

#### AnimeRepository.java
- **Rôle** : Accès aux animes en base
- **Méthodes personnalisées** :
  - `findByTitreContainingIgnoreCase(String titre)` : recherche par titre
  - `findByAnnee(Integer annee)` : recherche par année
  - `findByStatut(String statut)` : recherche par statut
  - `findByType(String type)` : recherche par type
  - `findByGenre(String genre)` : recherche par genre (via @Query)
  - `findByStudioId(Long studioId)` : recherche par studio
  - `findBySeiyuuId(Long seiyuuId)` : recherche par seiyuu
  - `findByOrderByNoteMoyenneDesc()` : top animes par note
  - `findByOrderByPopulariteDesc()` : top animes par popularité

#### SeiyuuRepository.java
- **Rôle** : Accès aux seiyuus en base
- **Méthodes personnalisées** :
  - `findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom)`
  - `findByNomJaponaisContainingIgnoreCase(String nomJaponais)`
  - `findByAgenceContainingIgnoreCase(String agence)`
  - `findByAnimeId(Long animeId)`
  - `findCollaborateurs(Long seiyuuId)` : seiyuus ayant collaboré avec un autre
  - `countCollaborations(Long seiyuu1Id, Long seiyuu2Id)`
  - `findMostActive()` : seiyuus les plus actifs

#### StudioRepository.java
- **Rôle** : Accès aux studios en base
- **Méthodes personnalisées** :
  - `findByNomContainingIgnoreCase(String nom)`
  - `findByStatut(String statut)`
  - `findBySiegeContainingIgnoreCase(String siege)`
  - `findByFondateurContainingIgnoreCase(String fondateur)`
  - `findByAnimeId(Long animeId)`
  - `findMostProductive()` : studios ayant produit le plus d'animes
  - `findActiveStudios()` : studios ayant au moins un anime

---

### service/

#### ServiceAnime.java
- **Rôle** : Logique métier pour les animes (CRUD, recherche, filtrage, etc.)
- **Exemple de scénario** :
  - Lorsqu'on ajoute un anime, le service vérifie les relations (seiyuus, studios) et sauvegarde l'anime en base.

#### ServiceSeiyuu.java
- **Rôle** : Logique métier pour les seiyuus (CRUD, recherche, etc.)
- **Exemple** :
  - Permet de rechercher un seiyuu par nom ou agence, de lister tous les seiyuus, etc.

#### ServiceStudio.java
- **Rôle** : Logique métier pour les studios (CRUD, recherche, etc.)
- **Exemple** :
  - Permet de rechercher un studio par nom, statut, siège, etc.

---

### dto/

#### AnimeDTO.java
- **Rôle** : Sert à transférer les données lors de la création ou modification d'un anime.
- **Champs** :
  - `titre`, `synopsis`, `annee`, `saison`, `statut`, `type`, `genres`, etc.
  - `seiyuuIds` : liste d'IDs de seiyuus à associer
  - `studioIds` : liste d'IDs de studios à associer
- **Exemple de payload** :
```json
{
  "titre": "One Piece",
  "annee": 1999,
  "statut": "En cours",
  "seiyuuIds": [1, 2],
  "studioIds": [1]
}
```

---

### config/
- (Vide ou à compléter pour la configuration avancée, ex : CORS, sécurité, etc.)

---

### templates/

#### dashboard.html
- **Rôle** : Page principale pour gérer les animes (affichage, ajout, modification, suppression)
- **Structure** :
  - Liste des animes (cartes)
  - Modal d'ajout/modification
  - Formulaire dynamique (JS)
- **Interaction** : Utilise fetch pour communiquer avec l'API `/api/animes`

#### seiyuus.html
- **Rôle** : Page de gestion des seiyuus (affichage, ajout, modification, suppression)
- **Structure** :
  - Liste des seiyuus
  - Modal d'ajout/modification
  - Recherche par nom

#### studios.html
- **Rôle** : Page de gestion des studios (affichage, ajout, modification, suppression)
- **Structure** :
  - Liste des studios
  - Modal d'ajout/modification
  - Recherche par nom/statut

---

### AnimeWatchlistApplication.java
- **Rôle** : Point d'entrée de l'application Spring Boot (bootstrap)
- **Exemple** :
```java
@SpringBootApplication
public class AnimeWatchlistApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnimeWatchlistApplication.class, args);
    }
}
```

---

## Exemples de flux complets

### Ajout d'un anime (du front à la base)
1. L'utilisateur clique sur "Ajouter un anime" dans le dashboard.
2. Le formulaire JS envoie un POST `/api/animes` avec un JSON (voir DTO).
3. Le contrôleur reçoit le DTO, crée l'entité Anime, gère les relations, et appelle le service.
4. Le service sauvegarde l'anime via le repository.
5. L'anime est stocké en base et renvoyé au front.

### Récupération de la liste des animes
1. Le front fait un GET `/api/animes`.
2. Le contrôleur appelle le service, qui interroge le repository.
3. Le repository retourne la liste des animes.
4. Le contrôleur renvoie la liste au front (JSON).

---

## Conseils pour contribuer et tester
- Utilise `docker-compose up --build` pour lancer l'appli (API + front).
- Les endpoints API sont documentés dans les contrôleurs.
- Pour tester l'API, utilise Postman ou curl (ex : `GET http://localhost:26550/api/animes`).
- Pour ajouter des fonctionnalités, crée un service puis expose-le via un contrôleur.
- Les fichiers HTML peuvent être modifiés pour améliorer l'UX.
- Pour la configuration avancée (CORS, sécurité), ajoute des classes dans `config/`.

---

**Ce projet suit l'architecture MVC Spring Boot, avec séparation claire des responsabilités.** 