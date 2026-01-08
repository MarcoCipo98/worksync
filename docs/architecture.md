# Architektur

## Ziel
Saubere, wartbare Backend-Architektur mit klaren Verantwortlichkeiten.

## Layer
- Controller: REST Endpoints, DTO Mapping, Validation
- Service: Business Logik, Use-Cases
- Repository: DB Zugriff (JPA)
- Domain: Entities, Enums
- Security: JWT Auth, Rollen, Endpoint-Schutz

## Prinzipien
- DTOs statt Entities über die API
- Zentrales Error Handling
- Tests für Services (Unit) + Endpoints (Integration)
