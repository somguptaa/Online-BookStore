# Online-BookStore

A production-ready **Spring Boot Online Bookstore Application** featuring:

- **JWT Authentication & Authorization**
- **Spring Cloud Config Server (Externalized Configuration)**
- **MySQL + MongoDB Dual Database Integration**
- **Spring Security With Custom JWT Filter**
- **Book, Cart, Order, Rating, Customer Modules**
- **Excel File Upload (Apache POI)**
- **File Upload (Single & Multi)**
- **Caching**
- **Resilience4j Circuit Breaker**
- **Swagger OpenAPI Documentation**

---

## Features Overview

### Authentication Module
- User registration stored in **MySQL + MongoDB**
- BCrypt password encryption
- JWT generation using `JwtUtilService`
- Custom `AppFilter` validates all protected requests
- Spring Security authorization rules configured in `SecurityConfig`

---

### Books Module
- Add new books
- Get all books (**with caching**)
- Get book by ID
- Excel file upload using Apache POI
- Entity: `BooksModule`

---

### Cart Module
- Add books to cart
- Auto-increment quantity for duplicate items
- Remove items from cart
- Calculates total price automatically  
- Entity: `CartModule`

---

### Order Module

Includes **Prime vs Non-Prime User Restrictions**:

#### Non-Prime Users:
- Can order **only 1 book at a time**
- Can place **only 1 order per week**

#### Prime Users:
- No restrictions  
- Unlimited orders  

Validates books before placing orders.

---

### Rating & Review Module
- Rate a book
- Add review text
- Fetch all ratings  
- Entity: `Ratings`

---

### Customer Module
- Add customer
- Update customer
- Fetch customer by ID
- Fetch all customers
- Pagination + sorting support
- Demonstrates **Resilience4j Circuit Breaker**

---

### File Upload Module
- Upload single file
- Upload multiple files  
Files saved as BLOBs in MySQL.

---

### Excel Upload Module
- Validates uploaded Excel file
- Parses with Apache POI
- Inserts only unique product records

---

### Spring Cloud Config Server Integration

Project uses Config Server:
```
spring.config.import=optional:configserver:http://localhost:8888
```

---

## Application Configuration

### `application.properties`
```properties
spring.application.name=EC-OnlineBookStore
server.port=7071
spring.config.import=optional:configserver:http://localhost:8888
spring.jpa.hibernate.ddl-auto=update
spring.main.allow-circular-references=true
```

---

## Tech Stack

### Backend
- Java 21
- Spring Boot   
- Spring Security  
- JWT  
- Spring Data JPA  
- Spring Data MongoDB  
- Circut Breaker (Resilience4j)
- Apache POI (For Excel)
- Lombok  
- Swagger OpenAPI  

### Databases
- **MySQL** (Primary DB)
- **MongoDB** (Secondary DB)

---

## Project Structure
```
src/main/java/com/som/project
|
|-- controller/
| |- UserRegisterController
| |- BooksController
| |- CartModuleController
| |- OrderController
| |- RatingController
| |- CustmerController
| |- BooksExcelUploadController
| |- FileController
|
|-- service/
|-- serviceImpl/
|-- repository/
|-- repository/mongo/
|-- entity/
|-- entity/mongo/
|-- exceptions/
|-- filter/AppFilter.java
|-- utility/
| |- Constants.java
| |- Helper.java
| |- JwtUtilService.java
|
|-- SecurityConfig.java
|-- EcOnlineBookStoreApplication.java

```
## API Endpoints Summary
### User Authentication

| Method | Endpoint                | Description     |
| ------ | ----------------------- | --------------- |
| POST   | `/api/userregisters`    | Register user   |
| POST   | `/api/userlogin`        | Login + JWT     |
| GET    | `/api/getAllUsers`      | Fetch all users |
| GET    | `/api/userDetails/{id}` | Get user by ID  |

### User Authentication

| Method | Endpoint                |
| ------ | ----------------------- |
| POST   | `/api/savebooks`        |
| GET    | `/api/getAllBooks`      |
| GET    | `/api/getCustBook/{id}` |

### Excel Upload

| Endpoint           |
| ------------------ |
| `/uploadExcelFile` |

### Cart

| Method | Endpoint            |
| ------ | ------------------- |
| POST   | `/api/addcart`      |
| DELETE | `/api/addcart/{id}` |

### Orders

| Method | Endpoint           |
| ------ | ------------------ |
| POST   | `/api/orderplaced` |

### Ratings

| Method | Endpoint             |
| ------ | -------------------- |
| POST   | `/api/ratingbooks`   |
| GET    | `/api/getAllreviews` |

### Customers

| Method | Endpoint                            |
| ------ | ----------------------------------- |
| POST   | `/api/custmersave`                  |
| PUT    | `/api/custmerupadte`                |
| GET    | `/api/getByCustmerId/{id}`          |
| GET    | `/api/getAllCustmers`               |
| GET    | `/api/getAllCustmerswithpagination` |

### File Upload

| Method | Endpoint            |
| ------ | ------------------- |
| POST   | `/uploadfiles`      |
| POST   | `/uploadmultifiles` |

## Caching 
### Used on:
- Get all books
- Get book by ID
- Get all registered users
### 
### Annotations used:
```
@Cacheable(value="getAllBooks")
@Cacheable(value="booksmodule", key="#id")
@Cacheable(value="getAllUsers")
```
## Circuit Breaker
### Endpoint:
```
/api/getCustmer

```
### Fallback response:
`Payment service temporarily unavailable`

## Swagger API Docs
```
http://localhost:7071/swagger-ui.html
```
## How to Run the Application
- Start Config Server on Port 8888
### Ensure Config Server has your external YAML configs.
- Start MySQL & MongoDB
- Update DB credentials in Config Server config repo
### Run Application
- Run as -> Spring Boot Application
## Future Enhancements
- Payment Gateway Integration
- Redis Cache
- Docker Support
- Email Notifications
- Microservices Migration
- Admin Dashboard

## Licence
- Released Under MIT Licence
