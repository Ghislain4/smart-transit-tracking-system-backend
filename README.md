# Smart Transit Tracking System (Backend)

- **Student:** Sugira Ghislain
- **Student ID:** 27776
- **Project:** Smart Transit Tracking System
- **Course:** INSY 8322 Web Technology and Internet (AUCA), Mid-Term Project

A Spring Boot REST API that gives commuters visibility on the buses of a city.
Entities: Commuter, Driver, Route, Bus, Trip and StopReport.

## Technologies
- Java 17 or later
- Spring Boot, Spring Web, Spring Data JPA
- PostgreSQL

## Steps to run the API
1. Install Java, Maven (or use the included `mvnw`) and PostgreSQL.
2. In PostgreSQL (pgAdmin), create an empty database named `smart_transit`.
3. Check `src/main/resources/application.properties`: the database is `smart_transit`,
   username `postgres`, password `1234` (change them if yours are different).
4. Clone the repository:
   ```
   git clone https://github.com/Ghislain4/smart-transit-tracking-system-backend.git
   cd smart-transit-tracking-system-backend
   ```
5. Run the application:
   ```
   mvnw.cmd spring-boot:run        (Windows)
   ./mvnw spring-boot:run          (Linux / Mac)
   ```
6. The API starts on `http://localhost:8080`. The tables are created automatically
   (`spring.jpa.hibernate.ddl-auto=update`).
7. Import `smart-transit-tracking-system-midterm.postman_collection.json` in Postman.
   Enter the seed data first (folders  `seed stop report`), then test
   each requirement (A2, B1, C4, D2, ...).
