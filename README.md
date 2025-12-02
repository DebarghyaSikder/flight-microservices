# Flight Booking Microservices System  
A complete microservices-based flight search & booking platform built using Spring Boot Microservices.

---

## Overview

This project is a fully distributed flight booking system built using Spring Boot 3.3.x and Spring Cloud 2023. The application is broken into multiple independently deployable microservices. Communication happens through REST, RabbitMQ, Eureka, Config Server, and API Gateway.

---

## Microservices Included

| Service | Description |
|--------|-------------|
| `config-server` | Centralized configuration server storing YAML files from Git repo |
| `eureka-server` | Service registry for all microservices |
| `api-gateway` | Single entry point; handles routing and load balancing |
| `flight-service` | Manages flights, inventory, search, seat availability |
| `booking-service` | Handles booking, calling flight-service, saving data, RabbitMQ messaging |
| `flight-config-repo` | Git repository storing configuration for all services |

---

# Service Details

---

## Config Server (`config-server`)

Stores externalized configuration for all services.


## Eureka Server (`eureka-server`)

All services register here automatically:
- API-GATEWAY
- FLIGHT-SERVICE
- BOOKING-SERVICE

---

## API Gateway (`api-gateway`)

Routes external requests to internal microservices.

### Example routes:
- `spring.cloud.gateway.routes[0].id=flight-service`
- `spring.cloud.gateway.routes[0].uri=lb://FLIGHT-SERVICE`
- `spring.cloud.gateway.routes[0].predicates[0]=Path=/flight/**`


## Flight Service (`flight-service`)

A fully reactive WebFlux service.

### Responsibilities
- Add flights  
- Search flights  
- Check availability  
- Update seats  
- Consume RabbitMQ messages  

### Sample Endpoints
- `POST /flight`
- `GET /flight/search`
- `GET /flight/{id}`

### Mongo Database
`flightdb`


## Booking Service (`booking-service`)

A blocking MVC service that interacts with flight service.

### Responsibilities
- Create bookings  
- Validate seat availability using Feign  
- Save booking  
- Publish booking events to RabbitMQ  
- Handle seat reduction via message queue  

### Mongo Database
`bookingdb`

### Sample Endpoints
- `POST /bookings`
- `GET /bookings/{id}`

# RabbitMQ Integration

### Flow
`Booking-Service → RabbitMQ → Flight-Service`

### Exchange / Queue / Routing Key
- booking.exchange
- booking.queue
- booking.key

RabbitMQ Dashboard:
`http://localhost:15672`

# flight-config-repo (Git Repository)

Stores all config files for:

- flight-service.yml
- booking-service.yml
- api-gateway.yml
- eureka-server.yml


# How to Run the Entire System

### Starting in this exact order:

---

### 1. Start MongoDB  
Databases needed:
- `flightdb`
- `bookingdb`


### 2. Start RabbitMQ
rabbitmq-server


UI:
- `http://localhost:15672`


### 3. Start Config Server  
- `localhost:8888`


### 4. Start Eureka Server  
- `localhost:8761`

### 5️. Start Flight Service  
Registers with Eureka → Loads config → Connects to MongoDB.



### 6️. Start Booking Service  
Uses Feign → RabbitMQ → MongoDB.

---

### 7️. Start API Gateway  
System now fully functional.

---

#  Testing (Postman)

### Create Flight
- `POST` `http://localhost:8080/flight`

### Search
- `GET` `http://localhost:8080/flight/search?from=Kolkata&to=Delhi&date=2025-12-25`

### Book Flight
- `POST` `http://localhost:8080/bookings`


#  Features Implemented

- Centralized config  
- Service discovery  
- API Gateway routing  
- Reactive + MVC mixed architecture  
- RabbitMQ event communication  
- OpenFeign inter-service communication  
- Circuit breaker (Resilience4j)  
- MongoDB persistence  
- Input validation  
- Clean microservices separation 