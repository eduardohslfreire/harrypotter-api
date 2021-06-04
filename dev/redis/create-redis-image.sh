#!/bin/sh
docker build --pull --rm -f "redis.dev" -t redis:harrypotter-api "."