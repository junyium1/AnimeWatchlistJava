# Anime Watchlist – Démarrage rapide avec Docker

## Prérequis

- **Docker** et **Docker Compose** installés sur votre machine.

---

## Lancer l’application avec Docker

1. **Cloner le dépôt** (si ce n’est pas déjà fait) :
   ```bash
   git clone <url-du-repo>
   cd AnimeWatchlistJava
   ```

2. **Construire et démarrer les conteneurs** :
   ```bash
   docker-compose up --build
   ```
   Cette commande va :
   - Construire l’image de l’application Java (Spring Boot)
   - Lancer l’API et la base de données (si configurée dans le docker-compose)
   - Exposer l’API sur le port défini (par défaut : `http://localhost:26550`)

3. **Vérifier que l’API est en ligne** :
   - Accédez à [http://localhost:26550/api/animes](http://localhost:26550/api/animes) dans votre navigateur ou via Postman/curl.
   - Vous devriez obtenir une liste (éventuellement vide) d’animes au format JSON.

---

## Commandes utiles

- **Arrêter les conteneurs** :
  ```bash
  docker-compose down
  ```
- **Rebuild complet (si vous modifiez le code)** :
  ```bash
  docker-compose up --build
  ```

---

## Structure du projet

- `Dockerfile` : instructions pour construire l’image de l’application Java.
- `docker-compose.yml` : orchestre l’application et la base de données.
- `src/` : code source Java (Spring Boot, MVC, JPA, etc.)
- `README.md` : ce fichier.

---

## Tester l’API

Exemples de requêtes :

- **Lister les animes** :
  ```bash
  curl http://localhost:26550/api/animes
  ```
- **Ajouter un anime** :
  ```bash
  curl -X POST http://localhost:26550/api/animes \
    -H "Content-Type: application/json" \
    -d '{"titre":"One Piece","annee":1999,"statut":"En cours","seiyuuIds":[1,2],"studioIds":[1]}'
  ```

---

## Dépannage

- Si le port est déjà utilisé, modifiez le port dans `docker-compose.yml`.
- Pour voir les logs :  
  ```bash
  docker-compose logs -f
  ```
- Si vous modifiez le code Java, relancez avec `--build`.

---

## Aller plus loin

- Les endpoints sont documentés dans les contrôleurs Java (`src/main/java/com/animewatchlist/controller/`).
- Pour la configuration avancée (CORS, sécurité), ajoutez des classes dans `src/main/java/com/animewatchlist/config/`.
