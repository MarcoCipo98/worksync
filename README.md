# WorkSync (Backend API)

WorkSync ist eine produktionsnahe REST-API zur Verwaltung von Aufgaben (Work Items) pro Benutzer.
Der Fokus liegt auf sauberer Architektur, Tests, Security (JWT), Docker und CI.

## Tech Stack
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security + JWT
- PostgreSQL
- OpenAPI / Swagger UI
- Docker & Docker Compose
- GitHub Actions (CI)
- JUnit 5, Mockito (und optional Testcontainers)

## Features
- User Registrierung & Login
- JWT Authentication
- Rollen: USER / ADMIN
- Work Items (CRUD)
- Status: OPEN / DONE
- Validierung & zentrales Error Handling
- Unit- & Integration-Tests
- Docker Setup + CI Pipeline

## Architektur (geplant)
- `controller` (REST, DTOs)
- `service` (Business Logic)
- `repository` (Persistence)
- `domain` (Entities, Enums)
- `security` (JWT, Roles)
- `config` (App-Konfiguration)
- globaler Exception Handler

## API Dokumentation
- Swagger UI: `http://localhost:8080/swagger-ui.html` (nach dem Start)

## Getting Started (lokal)
> folgt nach dem initialen Setup (Spring Boot + DB)

## Motivation
Ich baue dieses Projekt, um typische Anforderungen aus dem Backend-Alltag praxisnah umzusetzen:
Datenmodellierung, saubere REST-APIs, Security, Tests und produktionsnahes Deployment.

## Status
🚧 In Entwicklung
