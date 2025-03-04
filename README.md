# 🎉 Event Management System 🚀  

## 📌 Overview  
The **Event Management System** is a robust and scalable **Spring Boot** backend with **React** frontend that helps users organize, manage, and track events efficiently. This project includes secure **JWT authentication**, **role-based access control**, and follows REST API best practices.  

---

## 🏗️ Tech Stack  

### ⚙️ **Backend (Spring Boot)**
- 🏗️ Spring Boot 3 + Maven
- 🔐 Spring Security with JWT Authentication
- 🛢️ Spring Data JPA + MySQL
- 🎯 RESTful API with Swagger Documentation  

### 🛠️ **Dev Tools**
- 🖥️ IntelliJ IDEA  
- 📡 Postman for API Testing  
- 🐬 MySQL Workbench  

---

## 🚀 Features  
✅ **User Management** – Register, Login, JWT Authentication 🔑  
✅ **Role-Based Access** – Admin, Event Organizers, Users 🎭  
✅ **Event CRUD Operations** – Create, Read, Update, Delete 📅  
✅ **Ticket Booking System** – Book, Cancel, View Tickets 🎟️  
✅ **Secure API Endpoints** – JWT + Spring Security 🛡️  
✅ **MySQL Database Integration** – Persistent Data Storage 💾  
✅ **Swagger API Documentation** – API testing made easy 📜  

---

## 🔥 API Endpoints  

### 🛠️ **Authentication**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Authenticate & get JWT token |

### 🎟️ **Events Management**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/api/events` | Fetch all events |
| `POST` | `/api/events` | Create a new event (Admin/Organizer) |
| `PUT` | `/api/events/{id}` | Update an event |
| `DELETE` | `/api/events/{id}` | Delete an event |

### 📜 **Ticket Booking**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/api/tickets/book` | Book a ticket |
| `GET` | `/api/tickets/user/{userId}` | View booked tickets |
| `DELETE` | `/api/tickets/cancel/{ticketId}` | Cancel a ticket |

---
