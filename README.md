# Monster Train API

## 1. Project Overview
The Monster Train API is a Spring Boot application that exposes curated, machine-readable data from Shiny Shoe's *Monster Train*. The service aggregates cards, artifacts, clans, mutators, enemies, and related metadata through a consistent REST interface, making it easy to build custom tooling, dashboards, or companion apps around Monster Train content.

## 2. Features
- **Comprehensive data coverage** – REST endpoints for cards (creature and spell), artifacts, clans, buffs, debuffs, mutators, sins, triggers, and enemies under a shared `/api/v1` namespace.
- **Built-in documentation** – Auto-generated Swagger 2 documentation for exploring and testing endpoints in the browser.
- **Fast repeated lookups** – Caffeine-backed caching configured for all primary resources to minimize database calls for frequent requests.
- **Production-ready configuration** – Externalized database credentials via environment variables and support for Spring Boot actuator endpoints for monitoring.
- **Static reference UI** – A bundled HTML/CSS/JS site for browsing Monster Train data from the same service.

## 3. Tech Stack
- Java 8+
- Spring Boot 2.5.x
- Maven
- MySQL
- Caffeine Cache
- Swagger 2 (Springfox)
- HTML/CSS/JavaScript (Thymeleaf templates & static assets)

## 4. Getting Started
### Prerequisites
- Java Development Kit (JDK) 8 or newer
- Maven 3.6+
- Access to a MySQL database with Monster Train data

### Installation
Clone the repository and install dependencies:
```bash
git clone https://github.com/your-org/mt-api.git
cd mt-api
mvn clean install
```

### Configuration
Set the required environment variables before running the service. These are consumed by `application.properties`.
```bash
export DBHOSTNAME=<mysql-host>:<port>
export DBSCHEMA=<schema>
export DBUSERNAME=<user>
export DBPASSWORD=<password>
# Optional: set MTAPI.ENV or TOKEN as needed
```

### Run Locally
Start the Spring Boot application:
```bash
mvn spring-boot:run
```
The API defaults to port `8080`. Static documentation and UI assets are served from `/`.

### Run Tests
Execute the unit test suite:
```bash
mvn test
```

## 5. API Documentation
Swagger UI is available when the application is running at:
```
http://localhost:8080/swagger-ui.html
```
This interface lists all controllers under `com.zakpruitt.mtapi.controller`, including endpoints for artifacts, cards, clans, enemies, mutators, sins, triggers, and status effects.

## 6. Project Structure
```
src/
  main/
    java/com/zakpruitt/mtapi/
      config/         # Spring configuration (Swagger, caching, resource wiring)
      controller/     # REST controllers for Monster Train resources
      domain/         # Entity and domain models
      repository/     # Spring Data repositories for MySQL persistence
      service/        # Business logic and aggregation services
      utility/        # Helpers for data formatting and serialization
    resources/
      static/         # Front-end assets served by Spring Boot
      templates/      # Thymeleaf templates for the landing pages
      application.properties
  test/java/          # Placeholder for automated tests
```

## 7. Contributing
1. Fork the repository and create a feature branch.
2. Make your changes with accompanying tests or documentation updates.
3. Run `mvn test` and ensure all checks pass.
4. Submit a pull request describing your changes and testing results.

## 8. License
This project is distributed under the terms specified by the repository owner. Update this section with explicit licensing details if available.

## 9. Contact
For questions, feature requests, or data corrections, please open an issue or reach out to the maintainer via the contact information published on [mt-api.tech](https://mt-api.tech).

<!-- portfolio
section: more
name: MT API
year: 2022
summary: Spring Boot REST API with Monster Train game data, serving 400+ calls a day to community tools.
-->
