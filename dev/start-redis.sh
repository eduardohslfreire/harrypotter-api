#!/bin/sh
docker run -d -it --rm --name redis-harrypotter-api -p 6380:6379 redis:harrypotter-api

# Show status
docker ps
