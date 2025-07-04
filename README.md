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

## 🏗️ Tech Stack

- Java 17
- Spring Boot
- MongoDB
- Maven
- JUnit 5 & Mockito (testing)
- RestTemplate (caching)
- Lombok (code generation)

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

- **Spring Boot Starter Web**
- **Spring Boot Starter Data MongoDB**
- **Spring Data Starter Validation**
- **Spring Boot Starter Test**
- **Lombok**
- **JUnit 5**
- **Mockito**
- **Flapdoodle Embedded MongoDB**

---

# 🔧 LRU Cache Integration
This Task Management API integrates with my separate LRU Cache repository to provide high-performance caching:<br>
🔗 LRU Cache Repository: https://github.com/Moksha25-tech/LRUCache<br>
# 🔄 How to Use Both Systems Together
-Clone and Run the LRU Cache System:<br>
-LRU Cache API runs on: http://localhost:8080/api/cache<br>
-Run the Task Management API:<br>
-bashmvn spring-boot:run<br>
-Task Management API runs on: http://localhost:9090/api/tasks<br>

---

# 🔄 System Architecture
textTask Management API (Port 9090)<br>
        │<br>
        ▼<br>
   RestTemplate calls<br>
        │<br>
        ▼<br>
LRU Cache API (Port 8080) ──▶ In-Memory LRU Cache<br>
        │<br>
        ▼<br>
Task Data Caching & Retrieval<br>

---

# 🚀 Performance Benefits

Cache Hit: Tasks retrieved from LRU cache in ~2ms<br>
Cache Miss: Tasks fetched from MongoDB and cached for future requests<br>
TTL Support: Cached tasks automatically expire after configured time<br>
LRU Eviction: Least recently used tasks removed when cache reaches capacity<br>

---

## 👤 Author

**Moksha Choksi**  
📍 IIIT Hyderabad · UG2k24 Batch 
 
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
