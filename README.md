# Hospital Management System - Microservices Architecture

## 🏥 Project Overview
A microservices-based Hospital Management System built with **Spring Boot** and **Spring Cloud Gateway**, using **MongoDB** as the database. The system consists of 4 independent microservices and an API Gateway that routes all requests through a single port.

---

## 📁 Project Folder Structure

```
hospital-microservices/
│
├── api-gateway/                    ← API Gateway (Port 8080)
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/hospital/
│       │   └── ApiGatewayApplication.java
│       └── resources/
│           └── application.yml      ← Route configuration
│
├── patient-service/                ← Member 1 (Port 8081)
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/hospital/
│       │   ├── PatientServiceApplication.java
│       │   ├── model/Patient.java
│       │   ├── repository/PatientRepository.java
│       │   ├── service/PatientService.java
│       │   ├── controller/PatientController.java
│       │   └── config/SwaggerConfig.java
│       └── resources/
│           └── application.properties
│
├── doctor-service/                 ← Member 2 (Port 8082)
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/hospital/
│       │   ├── DoctorServiceApplication.java
│       │   ├── model/Doctor.java
│       │   ├── repository/DoctorRepository.java
│       │   ├── service/DoctorService.java
│       │   ├── controller/DoctorController.java
│       │   └── config/SwaggerConfig.java
│       └── resources/
│           └── application.properties
│
├── appointment-service/            ← Member 3 (Port 8083)
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/hospital/
│       │   ├── AppointmentServiceApplication.java
│       │   ├── model/Appointment.java
│       │   ├── repository/AppointmentRepository.java
│       │   ├── service/AppointmentService.java
│       │   ├── controller/AppointmentController.java
│       │   └── config/SwaggerConfig.java
│       └── resources/
│           └── application.properties
│
└── pharmacy-service/               ← Member 4 (Port 8084)
    ├── pom.xml
    └── src/main/
        ├── java/com/hospital/
        │   ├── PharmacyServiceApplication.java
        │   ├── model/Medicine.java
        │   ├── repository/MedicineRepository.java
        │   ├── service/PharmacyService.java
        │   ├── controller/PharmacyController.java
        │   └── config/SwaggerConfig.java
        └── resources/
            └── application.properties
```

---

## 👥 Team Member Contributions

| Member   | Microservice         | Port | Responsibility                                    |
|----------|---------------------|------|---------------------------------------------------|
| Member 1 | Patient Service     | 8081 | Patient registration, records, medical history     |
| Member 2 | Doctor Service      | 8082 | Doctor profiles, specializations, schedules        |
| Member 3 | Appointment Service | 8083 | Book, cancel, reschedule appointments              |
| Member 4 | Pharmacy Service    | 8084 | Medicines inventory, stock, prescriptions          |
| All      | API Gateway         | 8080 | Single entry point routing to all services         |

---

## 🛠️ Prerequisites

Before running the project, make sure you have:

1. **Java 17** or higher → https://adoptium.net/
2. **Maven 3.8+** → https://maven.apache.org/download.cgi
3. **MongoDB** (locally installed) → https://www.mongodb.com/try/download/community
4. **IDE** → IntelliJ IDEA (recommended) or VS Code with Java extensions

### Install MongoDB Locally (Windows)

1. Download MongoDB Community Server from: https://www.mongodb.com/try/download/community
2. Install with default settings (choose "Complete" installation)
3. During installation, check "Install MongoDB as a Service" — this will auto-start MongoDB
4. MongoDB will run on `localhost:27017` by default
5. Optionally install MongoDB Compass (GUI tool) to view your data

### Install MongoDB Locally (Mac)

```bash
brew tap mongodb/brew
brew install mongodb-community@7.0
brew services start mongodb-community@7.0
```

### Verify MongoDB is Running

```bash
mongosh
# If it connects successfully, MongoDB is running!
# Type 'exit' to quit
```

---

## 🚀 How to Run the Project

### Step 1: Make sure MongoDB is running

```bash
# Windows: MongoDB runs as a service automatically
# Mac/Linux:
sudo systemctl start mongod
# or
brew services start mongodb-community@7.0
```

### Step 2: Open each service in a SEPARATE terminal

**Terminal 1 — Patient Service:**
```bash
cd hospital-microservices/patient-service
mvn spring-boot:run
```

**Terminal 2 — Doctor Service:**
```bash
cd hospital-microservices/doctor-service
mvn spring-boot:run
```

**Terminal 3 — Appointment Service:**
```bash
cd hospital-microservices/appointment-service
mvn spring-boot:run
```

**Terminal 4 — Pharmacy Service:**
```bash
cd hospital-microservices/pharmacy-service
mvn spring-boot:run
```

**Terminal 5 — API Gateway (start this LAST):**
```bash
cd hospital-microservices/api-gateway
mvn spring-boot:run
```

### Step 3: Verify all services are running

Open each URL in browser:
- Patient Service:     http://localhost:8081/swagger-ui.html
- Doctor Service:      http://localhost:8082/swagger-ui.html
- Appointment Service: http://localhost:8083/swagger-ui.html
- Pharmacy Service:    http://localhost:8084/swagger-ui.html
- API Gateway Health:  http://localhost:8080/actuator/health

---

## 🔗 API Endpoints Summary

### Patient Service (Port 8081)
| Method | Direct URL                                      | Via Gateway (Port 8080)                        |
|--------|------------------------------------------------|------------------------------------------------|
| GET    | http://localhost:8081/api/patients              | http://localhost:8080/api/patients              |
| GET    | http://localhost:8081/api/patients/{id}         | http://localhost:8080/api/patients/{id}         |
| POST   | http://localhost:8081/api/patients              | http://localhost:8080/api/patients              |
| PUT    | http://localhost:8081/api/patients/{id}         | http://localhost:8080/api/patients/{id}         |
| DELETE | http://localhost:8081/api/patients/{id}         | http://localhost:8080/api/patients/{id}         |
| GET    | http://localhost:8081/api/patients/blood-group/{bg} | http://localhost:8080/api/patients/blood-group/{bg} |

### Doctor Service (Port 8082)
| Method | Direct URL                                          | Via Gateway (Port 8080)                             |
|--------|-----------------------------------------------------|-----------------------------------------------------|
| GET    | http://localhost:8082/api/doctors                    | http://localhost:8080/api/doctors                    |
| GET    | http://localhost:8082/api/doctors/{id}               | http://localhost:8080/api/doctors/{id}               |
| POST   | http://localhost:8082/api/doctors                    | http://localhost:8080/api/doctors                    |
| PUT    | http://localhost:8082/api/doctors/{id}               | http://localhost:8080/api/doctors/{id}               |
| DELETE | http://localhost:8082/api/doctors/{id}               | http://localhost:8080/api/doctors/{id}               |
| GET    | http://localhost:8082/api/doctors/specialization/{s} | http://localhost:8080/api/doctors/specialization/{s} |
| GET    | http://localhost:8082/api/doctors/available           | http://localhost:8080/api/doctors/available           |

### Appointment Service (Port 8083)
| Method | Direct URL                                             | Via Gateway (Port 8080)                                |
|--------|-------------------------------------------------------|-------------------------------------------------------|
| GET    | http://localhost:8083/api/appointments                 | http://localhost:8080/api/appointments                 |
| GET    | http://localhost:8083/api/appointments/{id}            | http://localhost:8080/api/appointments/{id}            |
| POST   | http://localhost:8083/api/appointments                 | http://localhost:8080/api/appointments                 |
| PUT    | http://localhost:8083/api/appointments/{id}            | http://localhost:8080/api/appointments/{id}            |
| PUT    | http://localhost:8083/api/appointments/{id}/cancel     | http://localhost:8080/api/appointments/{id}/cancel     |
| PUT    | http://localhost:8083/api/appointments/{id}/reschedule | http://localhost:8080/api/appointments/{id}/reschedule |
| DELETE | http://localhost:8083/api/appointments/{id}            | http://localhost:8080/api/appointments/{id}            |
| GET    | http://localhost:8083/api/appointments/patient/{pid}   | http://localhost:8080/api/appointments/patient/{pid}   |
| GET    | http://localhost:8083/api/appointments/doctor/{did}    | http://localhost:8080/api/appointments/doctor/{did}    |

### Pharmacy Service (Port 8084)
| Method | Direct URL                                          | Via Gateway (Port 8080)                             |
|--------|-----------------------------------------------------|-----------------------------------------------------|
| GET    | http://localhost:8084/api/medicines                  | http://localhost:8080/api/medicines                  |
| GET    | http://localhost:8084/api/medicines/{id}             | http://localhost:8080/api/medicines/{id}             |
| POST   | http://localhost:8084/api/medicines                  | http://localhost:8080/api/medicines                  |
| PUT    | http://localhost:8084/api/medicines/{id}             | http://localhost:8080/api/medicines/{id}             |
| DELETE | http://localhost:8084/api/medicines/{id}             | http://localhost:8080/api/medicines/{id}             |
| GET    | http://localhost:8084/api/medicines/category/{cat}   | http://localhost:8080/api/medicines/category/{cat}   |
| GET    | http://localhost:8084/api/medicines/search?name=xyz  | http://localhost:8080/api/medicines/search?name=xyz  |
| GET    | http://localhost:8084/api/medicines/low-stock        | http://localhost:8080/api/medicines/low-stock        |
| PATCH  | http://localhost:8084/api/medicines/{id}/stock?quantity=10 | http://localhost:8080/api/medicines/{id}/stock?quantity=10 |

---

## 📝 Sample JSON for Testing (use in Swagger UI or Postman)

### Create Patient (POST /api/patients)
```json
{
  "firstName": "Kamal",
  "lastName": "Perera",
  "email": "kamal@email.com",
  "phone": "+94771234567",
  "address": "123 Galle Road, Colombo",
  "dateOfBirth": "1990-05-15",
  "gender": "Male",
  "bloodGroup": "O+",
  "medicalHistory": "No known allergies"
}
```

### Create Doctor (POST /api/doctors)
```json
{
  "firstName": "Dr. Nimal",
  "lastName": "Silva",
  "email": "nimal.silva@hospital.com",
  "phone": "+94779876543",
  "specialization": "Cardiology",
  "qualification": "MBBS, MD (Cardiology)",
  "experienceYears": 15,
  "department": "Cardiology",
  "available": true,
  "availableDays": ["Monday", "Wednesday", "Friday"],
  "consultationTime": "09:00 - 13:00",
  "consultationFee": 2500.00
}
```

### Create Appointment (POST /api/appointments)
```json
{
  "patientId": "PASTE_PATIENT_ID_HERE",
  "patientName": "Kamal Perera",
  "doctorId": "PASTE_DOCTOR_ID_HERE",
  "doctorName": "Dr. Nimal Silva",
  "appointmentDate": "2026-04-01",
  "appointmentTime": "10:30:00",
  "department": "Cardiology",
  "reason": "Routine heart checkup",
  "notes": "Patient has family history of heart disease"
}
```

### Create Medicine (POST /api/medicines)
```json
{
  "name": "Paracetamol 500mg",
  "genericName": "Acetaminophen",
  "manufacturer": "State Pharmaceuticals",
  "category": "TABLET",
  "description": "Pain reliever and fever reducer",
  "price": 2.50,
  "stockQuantity": 500,
  "reorderLevel": 50,
  "expiryDate": "2027-12-31",
  "manufacturedDate": "2025-06-01",
  "prescriptionRequired": false,
  "dosageInstructions": "1-2 tablets every 4-6 hours. Max 8 tablets per day."
}
```

---

## 🌐 How API Gateway Avoids Multiple Ports

### The Problem (Without Gateway):
- Patient Service runs on port 8081
- Doctor Service runs on port 8082
- Appointment Service runs on port 8083
- Pharmacy Service runs on port 8084
- Client must know ALL 4 different ports!

### The Solution (With API Gateway):
- API Gateway runs on port **8080** (single entry point)
- Client only needs to know **ONE port: 8080**
- Gateway automatically routes requests based on URL path:
  - `/api/patients/**`      → forwards to port 8081
  - `/api/doctors/**`       → forwards to port 8082
  - `/api/appointments/**`  → forwards to port 8083
  - `/api/medicines/**`     → forwards to port 8084

### Benefits:
1. **Single entry point** — Client only communicates with port 8080
2. **URL-based routing** — Gateway reads the path and forwards to correct service
3. **Decoupled services** — Services can change ports without affecting clients
4. **Cross-cutting concerns** — CORS, logging, rate limiting handled in one place
5. **Swagger aggregation** — API docs accessible through gateway routes

---

## 📸 Screenshots Needed for Slide Deck

For each service, take these screenshots:

### 1. Native Swagger UI (direct port)
- http://localhost:8081/swagger-ui.html (Patient)
- http://localhost:8082/swagger-ui.html (Doctor)
- http://localhost:8083/swagger-ui.html (Appointment)
- http://localhost:8084/swagger-ui.html (Pharmacy)

### 2. API calls via direct port (using Swagger UI or Postman)
- Show POST request creating a record
- Show GET request retrieving records
- Show the response with data

### 3. Same API calls via Gateway port 8080 (using Postman)
- Show POST to http://localhost:8080/api/patients (same request, different port)
- Show GET to http://localhost:8080/api/doctors
- Show the response is identical

### 4. Gateway health check
- http://localhost:8080/actuator/health
- http://localhost:8080/actuator/gateway/routes

---

## 🔧 Troubleshooting

### MongoDB connection error
```
Make sure MongoDB is running:
  Windows: Check Services → MongoDB Server → Running
  Mac: brew services list | grep mongodb
  Linux: sudo systemctl status mongod
```

### Port already in use
```bash
# Find and kill process on a port (e.g., 8081):
# Windows:
netstat -ano | findstr :8081
taskkill /PID <PID> /F

# Mac/Linux:
lsof -i :8081
kill -9 <PID>
```

### Maven build fails
```bash
# Clean and rebuild
mvn clean install -DskipTests
```

---

## 💡 Technology Stack
- **Java 17**
- **Spring Boot 3.2.3**
- **Spring Cloud Gateway 2023.0.0**
- **MongoDB** (NoSQL Database)
- **SpringDoc OpenAPI** (Swagger UI)
- **Lombok** (Reduce boilerplate code)
- **Maven** (Build tool)
