#!/bin/sh
docker run -d -it --rm --name postgres-harrypotter-api -v ~/volumes/postgres-data/harrypotter-api:/var/lib/postgresql/data -p 5433:5432 postgres:harrypotter-api

# Show status
docker ps

