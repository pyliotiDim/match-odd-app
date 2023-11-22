# Technical Assessment: Match Management Web API

## Overview

The purpose of this technical assessment is to create a functional REST Web API using Java, Spring Boot, JPA, PostgreSQL, and Maven. The goal is to manage matches and their odds.

## Entities

### Match

- **id**
- **description**
- **match_date**
- **match_time**
- **team_a**
- **team_b**
- **sport (enum with 2 values: 1. Football, 2. Basketball)**

### MatchOdds

- **id**
- **match_id**
- **specifier**
- **odd**

## Project Structure

- `src/`: Source code directory
- `pom.xml`: Maven project configuration
- `Dockerfile`: Docker configuration file
- `db.changelog/db.changelog-master.xml`: Liquibase changelog for database schema

## Building and Running the Web Service

1. Build the project using Maven:

    ```bash
    mvn clean install
    ```

2. Build and run the Docker container:

    ```bash
    docker build -t match-api .
    docker-compose up
    ```

3. Access the API from Swagger

Access the API endpoints at [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

## API Endpoints

MatchController

- **GET /match/{id}**: Retrieve a match by ID
- **POST /match/create**: Create a new match
- **PUT /match/update**: Update a match
- **DELETE /match/delete/{id}**: Delete a match by ID
- **GET /match/**: Retrieve all matches

MatchOddsController

- **GET /match/odds/{id}**: Retrieve match odd by ID
- **POST /match/odds/create**: Create new match odd
- **PUT /match/odds/update**: Update match odd
- **DELETE /match/odds/delete/{id}**: Delete match odd by ID

