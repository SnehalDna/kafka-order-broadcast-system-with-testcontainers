# kafka-order-broadcast-system-with-testcontainers
High performance order delivery system using Spring Boot &amp; Apache Kafka. Features Broadcaster pattern, Docker containerisation, and industry-standard E2E testing with Testcontainers, RestAssured, and Awaitility.  

---

# Kafka Order Broadcast System 

A production-grade, event-driven order delivery system built with **Java**, **Spring Boot**, and **Apache Kafka**. This project demonstrates how to build a scalable broadcaster pattern where a single order event is processed by multiple independent services (Shipping & Notifications).

## Tech Stack

* **Backend:** Java 17, Spring Boot 3.x, Spring Kafka
* **Messaging:** Apache Kafka (Broker)
* **Testing:** RestAssured (API), Testcontainers (Infrastructure), Awaitility (Async)
* **DevOps:** Docker, Docker Compose, GitHub Actions (CI)

---

## Key Features

* **Broadcaster Pattern:** Uses distinct Consumer Groups to ensure every order is processed by both the Shipping and Notification services.
* **Hybrid Environment Support:** Seamlessly switches between `localhost` and `Docker` environments using Spring Dynamic Properties.
* **Singleton Testcontainers:** High-performance integration tests that share a single Kafka/Postgres instance across the entire suite.
* **E2E Testing:** Full "Black-Box" testing using RestAssured to trigger events and Awaitility to verify asynchronous state changes.

---

##  Getting Started

### Prerequisites

* Docker & Docker Compose
* Java 17+
* Maven

### 1. Run Locally (Docker Compose)

To spin up the entire ecosystem (Kafka + Zookeeper + Order Service):

```bash
mvn clean package -DskipTests
docker-compose up --build

```

The API will be available at `http://localhost:8080`.

### 2. Run Tests

This project uses **Testcontainers**. You do **not** need Kafka running on your machine to run tests; Docker will handle it automatically.

```bash
mvn test

```

---

## Testing Strategy

I have implemented a "Sandwich" testing strategy to handle the asynchronous nature of Kafka:

1. **Trigger:** `RestAssured` sends a POST request to `/api/orders/place`.
2. **Process:** Kafka moves the message from the Producer to the Consumers.
3. **Verify:** `Awaitility` polls the system for up to 10 seconds to verify that the Shipping and Notification services successfully received the broadcast.

---

## API Documentation

### Place an Order

**POST** `/api/orders/place`

```json
{
    "orderId": "ORD-12345",
    "product": "Sony WH-1000XM5",
    "price": 349.99
}

```

---

##  Project Structure

* `src/main/java`: Core logic (Producer, Consumers, Models).
* `src/test/java`: Advanced testing suite (Integration & E2E).
* `docker-compose.yml`: Infrastructure orchestration.
* `Dockerfile`: Container definition for the Spring Boot app.

---

