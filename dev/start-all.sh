#!/bin/sh
# Start PostgreSQL
docker run -d -it --rm --name postgres-harrypotter-api -v ~/volumes/postgres-data/harrypotter-api:/var/lib/postgresql/data -p 5433:5432 postgres:harrypotter-api

# Start Redis
docker run -d -it --rm --name redis-harrypotter-api -p 6380:6379 redis:harrypotter-api

# Show status
docker ps
