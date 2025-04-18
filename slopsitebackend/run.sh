#!/bin/bash

# Step 1: Shut down any running containers and remove volumes
echo "🔻 Stopping and removing containers and volumes..."
docker compose down -v

# Step 2: Rebuild and restart the containers in detached mode
echo "🔧 Building and starting containers..."
docker compose up --build -d

# Step 3: Run Maven tests inside a container
echo "🧪 Running tests..."
docker run --rm \
  --network="slopsite_default" \
  -v "$(pwd)":/app \
  -w /app \
  maven:3.9.6-eclipse-temurin-17 \
  mvn test
