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
http://localhost:9090/api/cache
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

## 🛠️ Work in Progress

Currently resolving an issue where `RestTemplate` is **not hitting the cache** as expected during `GET` requests to the `/api/cache` endpoint.

> The caching logic in the `TaskService` is being debugged and improved.  
> A fix will be implemented shortly in the upcoming update.

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
- Implemented REST-based caching using `RestTemplate`
- Wrote unit and integration tests using JUnit and Mockito
- Applied clean architecture and exception handling best practices

---

