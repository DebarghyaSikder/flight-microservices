# Flight Booking Microservices System  
A complete microservices-based flight search & booking platform built using Spring Boot Microservices.

---

## Overview

This project is a fully distributed flight booking system built using **Spring Boot 3.3.x** and **Spring Cloud 2023**.  
It follows an enterprise-grade microservices architecture with:

- REST communication  
- Reactive + MVC hybrid architecture  
- Centralized config  
- Service discovery  
- API Gateway routing  
- JWT Authentication  
- RabbitMQ messaging  
- MongoDB persistence  
- Dockerized deployment (single `docker compose up`)  

---

# Microservices Included

| Service | Description |
|--------|-------------|
| **config-server** | Centralized configuration server pulling YAML files from Git repo |
| **eureka-server** | Service registry for all microservices |
| **api-gateway** | Single entry point; handles routing, load balancing & JWT authentication |
| **flight-service** | Manages flights, search, inventory updates, consumes events |
| **booking-service** | Validates availability, creates bookings, publishes RabbitMQ events |
| **notification-service** | Consumes RabbitMQ messages and logs booking notifications |
| **flight-config-repo** | Git repo storing external config for all services |

---

# JWT Authentication (NEW)

The API Gateway now secures all endpoints using **JWT tokens**.  
All requests must include a valid token in the `Authorization` header.

![alt text](https://github.com/DebarghyaSikder/flight-microservices/blob/main/images/JWT1.png)

### What Gateway Validates
- Token signature  
- Token expiration  
- HS256 algorithm  
- Using the shared secret:  


### How to Generate a JWT (Using jwt.io)

- Use payload:

```json
{
  "sub": "user123",
  "name": "Debarghya Sikder",
  "role": "USER",
  "iat": 1516239022
}
```

- Choose algorithm: HS256
- Secret
- We will get a JWT token.


# Dockerized Deployment

The entire Flight Booking Microservices System is now fully containerized using **Docker** and **Docker Compose**.  
You can run the *entire infrastructure + all microservices* using one single command:
```docker compose up -d```


This automatically starts:

- MongoDB  
- RabbitMQ  
- Config Server  
- Eureka Server  
- API Gateway  
- Flight Service  
- Booking Service  
- Notification Service  

---

## Docker Architecture Overview
** docker-compose.yml **
│
├── mongodb (Database)
├── rabbitmq-broker (Messaging Queue)
├── config-server (Centralized Configuration)
├── eureka-server (Service Registry)
├── api-gateway (Routing + JWT Security)
├── flight-service (Reactive WebFlux)
├── booking-service (Booking + RabbitMQ Publisher)
└── notification-service (RabbitMQ Consumer)

# Running the Entire System

###  1. Start All Containers

- From inside the **flight-microservices** folder:
```docker compose up -d```

![alt text](https://github.com/DebarghyaSikder/flight-microservices/blob/main/images/Docker.png)

![alt text](https://github.com/DebarghyaSikder/flight-microservices/blob/main/images/Docker2.png)



### 2. Stop All Containers
```docker compose down```

### 3. View Live Logs

```docker compose logs -f```

### 4. Rebuild Containers After Code Change

```docker compose build```
```docker compose up -d```

## **Features Implemented**

- Centralized configuration (Spring Cloud Config)

- Service discovery (Eureka)

- JWT security at gateway

- API Gateway routing & filtering

- Reactive + MVC microservices

- RabbitMQ asynchronous messaging

- MongoDB persistence (collections: flights, bookings)

- Feign Client communication

- Fully Dockerized deployment with ONE command

- Works entirely in container network (no localhost dependencies)



