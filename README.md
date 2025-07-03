# 📝 **Task Management REST API**

🛠️ This is the **backend** of a Task Management System built using **Spring Boot**, **MongoDB**, and **REST APIs**.  
It was developed as part of my **internship at Chubb** (June–July 2025), where I worked as a **Technology Intern**.


A Spring Boot application that provides a RESTful API to manage tasks with full **CRUD** support and **MongoDB** integration. It demonstrates:

- 🔁 REST-based caching using `RestTemplate`
- ❗ Centralized error handling using `@ControllerAdvice`
- 🧪 Unit testing with **JUnit 5** and **Mockito**
- 🧰 Embedded MongoDB for integration testing
- 🔄 Clean layered architecture (Controller → Service → Repository)

---

## 🚀 **Features**

- ✅ Create, Read, Update, Delete tasks
- ✅ MongoDB as NoSQL database
- ✅ In-memory or REST-based caching logic
- ✅ Layered architecture
- ✅ Global exception handling
- ✅ Full unit & integration test coverage

---

## 📁 **Project Structure**

- `TaskController.java` — Defines REST API endpoints  
- `TaskService.java` — Business logic layer  
- `TaskRepository.java` — Interface for MongoDB  
- `Task.java` — Task model and schema  
- `Configure.java` — Provides `RestTemplate` bean  
- `GlobalExceptionHandler.java` — Handles global exceptions  
- `ResourcesNotFoundException.java` — Custom not-found error  
- `TodofApplication.java` — Main Spring Boot application

---

## 📮 **API Endpoints**

| Method | Endpoint             | Description          |
|--------|----------------------|----------------------|
| GET    | `/api/tasks`         | Get all tasks        |
| GET    | `/api/tasks/{id}`    | Get task by ID       |
| POST   | `/api/tasks`         | Create new task      |
| PUT    | `/api/tasks/{id}`    | Update task          |
| DELETE | `/api/tasks/{id}`    | Delete task          |

---

## 🧾 **Sample Task JSON**

```json
{
  "title": "Build Spring Boot App",
  "description": "Complete the MongoDB CRUD API",
  "status": "InProgress"
}
```

---

## ⚙️ **Configuration**

Add this to your `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/taskdb
server.port=9090
```

📌 **Ensure MongoDB is running locally** (or update the URI accordingly).

---

## 🧪 **Testing**

Run all tests using:

```bash
mvn test
```

### ✔️ Tests Included:

- `TaskControllerTest` — Unit test with mocked service  
- `TaskServiceTest` — Unit test with mocked repo + `RestTemplate`  
- `TaskRepositoryTest` — Embedded MongoDB test (Flapdoodle)  
- `GlobalExceptionHandlerTest` — Custom exception handling  
- `TodofApplicationTest` — Spring Boot context + `CommandLineRunner`

---

## ▶️ **Run the App**

### 1️⃣ Start MongoDB (with Docker if needed):

```bash
docker run -d -p 27017:27017 --name mongo mongo:6.0
```

### 2️⃣ Run the Spring Boot App:

```bash
mvn spring-boot:run
```

### 3️⃣ Access the API:

```
http://localhost:9090/api/tasks
```

---

## ✅ **Dependencies**

- **Spring Boot**
- **Spring Web**
- **Spring Data MongoDB**
- **Jakarta Validation**
- **Lombok**
- **RestTemplate**
- **JUnit 5**
- **Mockito**
- **Flapdoodle Embedded MongoDB**

---

## 👤 Author

**Moksha Choksi**  
📍 IIIT Hyderabad · ECE Undergrad  
 
---

## 💼 Internship Experience

Built as part of my internship at **Chubb**  
🧑‍💻 **Role**: Technology Intern  
📅 **Duration**: June 2025 – July 2025  
🚀 **Contributions**:
- Developed the complete Spring Boot REST API for task management
- Integrated MongoDB as the backend database
- Implemented LRU (Least Recently Used) Caching system using Java and Spring Boot
- Integrated the LRU cache with REST API endpoints to improve response times
- Wrote unit and integration tests using JUnit and Mockito
- Applied clean architecture and exception handling best practices

---

# 🔮 Distributed Caching System (Future Work)

As a continuation of my internship project at **Chubb**, I will be developing a **Distributed Caching System** as a standalone backend microservice.

🧩 Problem Statement
- Performance Bottleneck: The existing backend service experiences latency and increased load due to repeated access to frequently requested data from the primary database.
- Scalability Requirement: As the system scales with more users and services, a centralized or single-node cache becomes insufficient and introduces reliability risks.
- Proposed Solution: Design and implement a Distributed Caching System as a dedicated microservice to improve data access performance, reduce backend load, and enable scalable, fault-tolerant caching across multiple service instances.

---

## 🚀 Overview

This microservice will act as a caching layer between internal backend services and the primary database. It will expose simple REST APIs to interact with the cache, and support eviction, TTL, and sync across nodes.

---

## 🎯 Goals

- Build a RESTful caching microservice in **Java Spring Boot**
- Implement core **LRU eviction** logic
- Ensure support for **scalability, concurrency**, and **fault tolerance**

---

## ⚙️ Planned Features

- 🔄 REST APIs for `GET`, `PUT`, `DELETE`, and `CLEAR` cache
- ⏱️ Configurable **TTL (Time-To-Live)** and **max size**
- 🧠 Built-in **LRU eviction policy**
- 📊 Metrics and logging support
- 🧪 Unit & integration tests using **JUnit 5** and **Mockito**

---

## 🏗️ Sample Architecture

```text
Client
  │
  ▼
Backend Services ──▶ Distributed Cache Service ──▶ Primary Database
                     ▲
                     └── Cache Hit / Miss, Eviction, Sync


