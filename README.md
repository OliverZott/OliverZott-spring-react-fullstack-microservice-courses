-------------------------------------------------
# Run / check application

- `http://localhost:8001`
- `http://localhost:8001/service/instances`
- `http://localhost:8001/service/all`

# Project Structure

- This is a **consumer** service.
- We will create connection to other services with **OpenFeign**...
    - e.g. get user-names with ID-list from user-service

## Prerequisites

- MySQL
- MySQL Workbench
- Spring Boot Initializer (with dependencies below)

## Tech-Stack

- Spring Boot
- Spring Cloud
- Hibernate (ORM)
- Liquibase(db migrations/schemas)
- Lombok (clean code)
- MVC structure
    - model
    - repository
    - service
    - controller (using Spring-Rest)