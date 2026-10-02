# Logistics API

REST API for managing a simple logistics system: clients, warehouses, vehicles, and shipments.

Built with Spring Boot as a portfolio backend project.

## Features

- CRUD for clients, warehouses, vehicles, and shipments
- Shipment status workflow: `CREATED` → `IN_TRANSIT` → `DELIVERED` / `CANCELLED`
- Business rules (cannot update/delete delivered or cancelled shipments, cannot delete related entities)
- JWT authentication (register / login)
- Pagination and filtering shipments by status
- Validation and global exception handling
- Swagger / OpenAPI documentation
- Docker + PostgreSQL
- Unit tests for authentication service
- Test data seeding (Datafaker)

## Tech stack

- Java 21
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- SpringDoc OpenAPI (Swagger)
- Docker / Docker Compose
- Maven
- JUnit 5 + Mockito

## Getting started

### Option 1: Docker (recommended)

    docker compose up --build

API: http://localhost:8080  
Swagger: http://localhost:8080/swagger-ui/index.html

### Option 2: Local run

1. Start PostgreSQL and create database `logistics`
2. Configure `src/main/resources/application.properties`
3. Run:

   mvn spring-boot:run

## Authentication

1. Register: `POST /api/auth/register` with JSON `{ "email": "user@example.com", "password": "password123" }`
2. Login: `POST /api/auth/login` with the same JSON → get `token`
3. For protected endpoints send header: `Authorization: Bearer <token>`

Public endpoints: `/api/auth/**`, Swagger.

## Main endpoints

- `POST /api/auth/register` — register
- `POST /api/auth/login` — login, returns JWT
- `GET /POST /api/clients` — list / create clients
- `GET /PUT /DELETE /api/clients/{id}` — get / update / delete client
- `GET /POST /api/warehouses` — list / create warehouses
- `GET /POST /api/vehicles` — list / create vehicles
- `GET /POST /api/shipments` — list (pagination + status filter) / create
- `PATCH /api/shipments/{id}/status` — update status
- `GET /api/shipments/tracking/{trackingNumber}` — find by tracking number

## Tests

    mvn test -Dtest=AuthServiceTest

## Project structure

    controller  → REST endpoints
    service     → business logic
    repository  → data access
    model       → JPA entities
    dto         → request/response objects
    security    → JWT filter and token service
    config      → security, OpenAPI, data init
    exception   → global error handling

## Live demo

Coming soon after deployment.

## Author

Backend portfolio project.
