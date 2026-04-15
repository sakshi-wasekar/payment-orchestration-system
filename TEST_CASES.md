# Test Cases

## ✅ Sanity Tests

* Create payment with valid data
* Get payment by ID
* Routing works correctly (CARD → A, UPI → B)

---

## 🔁 Regression Tests

* Idempotency returns same response
* Retry logic executes up to 3 attempts
* Payment status stored correctly

---

## 🔗 Integration Tests

* Controller → Service → Repository flow
* Routing to provider works
* Data persists in DB

---

## ❌ Negative Tests

### 1. Invalid Payment Type

Input: "bitcoin"
Expected: 400 Bad Request

---

### 2. Negative Amount

Input: -500
Expected: Validation error

---

### 3. Missing Fields

Input: null values
Expected: Validation error

---

### 4. Payment Not Found

GET /payments/999
Expected: 404

---

### 5. Duplicate Idempotency Key

Same request twice
Expected: Same response, no new record

---

## 🎯 Functional Requirements

* Payment creation
* Payment retrieval
* Routing logic
* Retry mechanism
* Idempotency

---

## ⚙️ Non-Functional Requirements

* Performance: Fast response
* Reliability: Retry mechanism
* Scalability: Provider abstraction
* Maintainability: Layered architecture
