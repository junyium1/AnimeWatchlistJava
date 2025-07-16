# Diagrammes Mermaid complets et valides

## Diagramme de classes (ultra détaillé)
```mermaid
classDiagram
    class Anime {
        +Long id
        +String titre
        +String synopsis
        +String genre
        +Integer annee
        +String saison
        +Integer nombreEpisodes
        +String statut
        +String type
        +String source
        +String classification
        +String duree
        +Boolean wishlist
        +EtatVisionnage etatVisionnage
        +String avis
        +List~Seiyuu~ seiyuus
        +List~Studio~ studios
        +List~String~ genres
        +String urlImage
        +Double noteMoyenne
        +Integer classement
        +Integer popularite
        +Integer nombreMembres
        +Integer nombreFavoris
    }
    class Seiyuu {
        +Long id
        +String nom
        +String prenom
        +String nomJaponais
        +LocalDate dateNaissance
        +String lieuNaissance
        +String biographie
        +String urlImage
        +String agence
        +String site
        +List~Anime~ animes
    }
    class Studio {
        +Long id
        +String nom
        +LocalDate dateFondation
        +String fondateur
        +String siege
        +String description
        +String urlImage
        +String siteWeb
        +String statut
        +List~Anime~ animes
    }
    Anime "*" --o "*" Seiyuu : "joué par"
    Anime "*" --o "*" Studio : "produit par"
```

## Diagramme de séquence (exhaustif)
```mermaid
sequenceDiagram
    participant U as Utilisateur
    participant F as Frontend (dashboard.html)
    participant C as ControleurAnime
    participant S as ServiceAnime
    participant R as AnimeRepository
    participant DB as Database
    U->>F: Remplit le formulaire (tous champs)
    F->>C: POST /api/animes (AnimeDTO)
    C->>S: sauvegarderAnime(Anime)
    S->>R: save(Anime)
    R->>DB: INSERT Anime
    DB-->>R: Anime saved
    R-->>S: Anime
    S-->>C: Anime
    C-->>F: Anime créé (JSON)
    F-->>U: Affiche l'anime dans la bonne section (watchlist/wishlist)
    U->>F: Modifie état/avis/wishlist
    F->>C: PUT /api/animes/{id}
    C->>S: sauvegarderAnime(Anime)
    S->>R: save(Anime)
    R->>DB: UPDATE Anime
    DB-->>R: OK
    R-->>S: Anime
    S-->>C: Anime
    C-->>F: Anime modifié (JSON)
    F-->>U: Affiche la mise à jour
```

## Diagramme d'architecture / composants (ultra détaillé)
```mermaid
flowchart TD
    Frontend("dashboard.html / seiyuus.html / studios.html")
    JS("JavaScript (fetch, DOM, validation)")
    API("Spring Boot REST API")
    ControleurAnime("ControleurAnime")
    ControleurSeiyuu("ControleurSeiyuu")
    ControleurStudio("ControleurStudio")
    ServiceAnime("ServiceAnime")
    ServiceSeiyuu("ServiceSeiyuu")
    ServiceStudio("ServiceStudio")
    AnimeRepository("AnimeRepository")
    SeiyuuRepository("SeiyuuRepository")
    StudioRepository("StudioRepository")
    Entities("Anime, Seiyuu, Studio")
    DB("Base de donnees")
    Frontend -- HTML/JS --> JS
    JS -- fetch --> API
    API --> ControleurAnime
    API --> ControleurSeiyuu
    API --> ControleurStudio
    ControleurAnime --> ServiceAnime
    ControleurSeiyuu --> ServiceSeiyuu
    ControleurStudio --> ServiceStudio
    ServiceAnime --> AnimeRepository
    ServiceSeiyuu --> SeiyuuRepository
    ServiceStudio --> StudioRepository
    AnimeRepository --> Entities
    SeiyuuRepository --> Entities
    StudioRepository --> Entities
    AnimeRepository --> DB
    SeiyuuRepository --> DB
    StudioRepository --> DB
```

## Diagramme ERD (relationnel)
```mermaid
erDiagram
    ANIME {
        INT id
        VARCHAR titre
        BOOLEAN wishlist
        VARCHAR etatVisionnage
        VARCHAR avis
    }
    SEIYUU {
        INT id
        VARCHAR nom
    }
    STUDIO {
        INT id
        VARCHAR nom
    }
    ANIME ||--o{ ANIME_SEIYUU : ""
    SEIYUU ||--o{ ANIME_SEIYUU : ""
    ANIME ||--o{ ANIME_STUDIO : ""
    STUDIO ||--o{ ANIME_STUDIO : ""
```

## Diagramme de flow (flux complet)
```mermaid
flowchart TD
    SaisieFormulaire["Saisie du formulaire (dashboard.html)"]
    ValidationJS["Validation JS côté client"]
    APICall["Appel API REST (POST/PUT/GET/DELETE /api/animes)"]
    ControleurAnime["ControleurAnime"]
    ServiceAnime["ServiceAnime"]
    AnimeRepository["AnimeRepository"]
    DB["Base de données"]
    ControleurSeiyuu["ControleurSeiyuu"]
    ServiceSeiyuu["ServiceSeiyuu"]
    SeiyuuRepository["SeiyuuRepository"]
    ControleurStudio["ControleurStudio"]
    ServiceStudio["ServiceStudio"]
    StudioRepository["StudioRepository"]
    ControleurWeb["ControleurWeb"]
    TemplateDashboard["dashboard.html"]
    TemplateSeiyuus["seiyuus.html"]
    TemplateStudios["studios.html"]
    SaisieFormulaire --> ValidationJS
    ValidationJS --> APICall
    APICall --> ControleurAnime
    APICall --> ControleurSeiyuu
    APICall --> ControleurStudio
    ControleurAnime --> ServiceAnime
    ControleurSeiyuu --> ServiceSeiyuu
    ControleurStudio --> ServiceStudio
    ServiceAnime --> AnimeRepository
    ServiceSeiyuu --> SeiyuuRepository
    ServiceStudio --> StudioRepository
    AnimeRepository --> DB
    SeiyuuRepository --> DB
    StudioRepository --> DB
    ControleurWeb --> TemplateDashboard
    ControleurWeb --> TemplateSeiyuus
    ControleurWeb --> TemplateStudios
    TemplateDashboard -.-> SaisieFormulaire
    TemplateSeiyuus -.-> SaisieFormulaire
    TemplateStudios -.-> SaisieFormulaire
    ControleurAnime -- CRUD Anime --> DB
    ControleurSeiyuu -- CRUD Seiyuu --> DB
    ControleurStudio -- CRUD Studio --> DB
```

## Diagramme d'état (états de visionnage)
```mermaid
stateDiagram-v2
    [*] --> Wishlist : Add
    Wishlist --> Watching : Start
    Watching --> Completed : Finish
    Completed --> Watching : Rewatch
    Watching --> Wishlist : Pause
```

## Diagramme de déploiement
```mermaid
flowchart TD
    browser["Utilisateur (navigateur)"]
    api["Spring Boot API (Docker)"]
    db["Base de donnees"]
    browser -->|HTTP| api
    api -->|JDBC/JPA| db
```

## Diagramme de cas d'utilisation (use case complet)
```mermaid
flowchart TD
    user[Utilisateur]
    addAnime[Ajouter un anime]
    editAnime[Modifier un anime]
    deleteAnime[Supprimer un anime]
    viewWatchlist[Voir la watchlist]
    viewWishlist[Voir la wishlist]
    moveToWishlist[Déplacer vers la wishlist]
    moveToWatchlist[Déplacer vers la watchlist]
    changeEtat[Changer etat de visionnage]
    leaveAvis[Laisser un avis]
    addSeiyuu[Ajouter un seiyuu]
    editSeiyuu[Modifier un seiyuu]
    deleteSeiyuu[Supprimer un seiyuu]
    viewSeiyuus[Voir les seiyuus]
    addStudio[Ajouter un studio]
    editStudio[Modifier un studio]
    deleteStudio[Supprimer un studio]
    viewStudios[Voir les studios]
    searchAnime[Rechercher un anime]
    searchSeiyuu[Rechercher un seiyuu]
    searchStudio[Rechercher un studio]
    user --> addAnime
    user --> editAnime
    user --> deleteAnime
    user --> viewWatchlist
    user --> viewWishlist
    user --> moveToWishlist
    user --> moveToWatchlist
    user --> changeEtat
    user --> leaveAvis
    user --> addSeiyuu
    user --> editSeiyuu
    user --> deleteSeiyuu
    user --> viewSeiyuus
    user --> addStudio
    user --> editStudio
    user --> deleteStudio
    user --> viewStudios
    user --> searchAnime
    user --> searchSeiyuu
    user --> searchStudio
    addAnime --> moveToWatchlist
    addAnime --> moveToWishlist
    moveToWishlist --> viewWishlist
    moveToWatchlist --> viewWatchlist
    changeEtat --> viewWatchlist
    leaveAvis --> viewWatchlist
``` 