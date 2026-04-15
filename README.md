# Payment Orchestration System

## 📌 Overview

This project is a simplified Payment Orchestration System inspired by real-world platforms. It demonstrates routing, retry logic, idempotency, and payment processing.

---

## 🏗 System Architecture

Client
↓
Controller Layer
↓
Service Layer (Orchestration Engine)
↓
Routing Engine
↓
Provider Connectors (A/B)
↓
Database (H2)

---

## ⚙️ Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven

---

## 🚀 Features

* Create Payment API
* Get Payment API
* Routing (CARD → A, UPI → B)
* Retry mechanism (3 attempts)
* Idempotency handling
* Payment status tracking

---

## 📥 Installation

1. Clone repository
2. Open in STS / IntelliJ
3. Run as Spring Boot Application

---

## ▶️ API Endpoints

### Create Payment

POST /payments

Request:
{
"amount": 1000,
"currency": "INR",
"paymentType": "CARD",
"idempotencyKey": "txn-123"
}

---

### Get Payment

GET /payments/{id}

---

## 🔄 Flow

1. Validate request
2. Check idempotency
3. Route to provider
4. Retry if failure (max 3)
5. Save payment
6. Return response

---

## 🔗 Integration Points

* Routing Service → decides provider
* Provider A/B → simulate processing
* Database → stores payments

---

## ⚡ Performance Considerations

* Retry limited to 3 attempts
* Idempotency avoids duplicate processing
* In-memory DB ensures fast response

---

## 🤖 AI Usage

AI tools were used to assist with:

* Architecture design
* Code structuring
* Test case generation

All logic was understood and implemented accordingly.

---

## 📌 Notes

* Security is disabled for testing
* H2 console may not work in some versions, APIs confirm DB operations
