#!/bin/sh
docker build --pull --rm -f "postgres.dev" -t postgres:harrypotter-api "."