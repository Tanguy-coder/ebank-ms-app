CREATE DATABASE customer_db;
CREATE DATABASE account_db;
    ```

**Relance le tout :**
    ```bash
    docker compose up -d
    ```

### Comment vérifier que les bases sont créées ?

Une fois que Postgres affiche "database system is ready", lance cette commande pour lister les bases de données réellement présentes dans le conteneur :

```bash
docker exec -it postgres-server psql -U postgres -c "\l"