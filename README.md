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
* Supports idempotent transactions to prevent duplicates
---

## 📥 Installation

1. Clone repository:
   git clone <your-repo-url>
   
2. Open the project in Spring Tool Suite (STS)
3. Import as Maven Project
4. Let Maven download dependencies
5. Build the project
6. Run as Spring Boot Application

---

## ▶️ Execution Guide
 
1. Run the application:
   Right-click project in STS → Run As → Spring Boot App
 
2. Once started, application will run on:
   http://localhost:8080
 
3. Test APIs using Postman:
 
### Create Payment
POST http://localhost:8080/payments
 
### Get Payment
GET http://localhost:8080/payments/{id}
 
4. Verify flow:
- Payment is created
- Routing happens (A/B provider)
- Retry logic works if failure occurs
- Idempotency prevents duplicate payments
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

Response:
{
"payemtnId": 1,
"status": "SUCCESS",
"provider": "A"
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

## 🧪 Test Strategy
 
Test cases are documented in: TEST_CASES.md
 
Test coverage includes:
- Sanity Tests
- Regression Tests
- Integration Tests
- Negative Tests

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
