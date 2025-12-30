# 📚 Library API

RESTful API for library management developed with Spring Boot. The system allows managing books, clients, authors, and loans.

## 🚀 Technologies

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **H2 Database** (in-memory database)
- **Maven**

## 📋 Features

- ✅ Book Management (CRUD)
- ✅ Client Management (CRUD)
- ✅ Loan Management (CRUD)
- ✅ Author Relationships
- ✅ H2 database with web console

## 🏗️ Architecture

The project follows the MVC pattern with well-defined layers:

api-biblioteca/
├── controller/       # Presentation layer (REST Controllers)
├── model/
│   ├── entity/       # JPA Entities
│   ├── repository/   # Data access interfaces
│   └── service/      # Business logic
└── resources/        # Application configuration

## 📦 Installation & Run

### Requirements

- Java 21 or higher
- Maven 3.6+ (or use the included wrapper)

### Run the project

#### Using Maven Wrapper (recommended):

# macOS/Linux
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run

#### Or with Maven installed:

mvn spring-boot:run

# The application will be available at:
http://localhost:8080

## 🗄️ Database

# This project uses an in-memory H2 Database.
# To access the H2 console:

# URL:
http://localhost:8080/h2-console

# JDBC URL:
jdbc:h2:mem:testdb

# Username:
sa

# Password:
# (leave blank)

## 🔌 API Endpoints

### 📖 Books

# Method   Endpoint                   Description
# POST     /books/create              Create a new book
# GET      /books                     Get all books
# GET      /books/title/{title}       Get book by title
# PUT      /books/update/{id}         Update book
# DELETE   /books/delete/{id}         Delete book

### 👥 Clients

# POST     /clients/create            Create a new client
# GET      /clients                   Get all clients
# GET      /clients/{id}              Get client by ID
# PUT      /clients/update/{id}       Update client
# DELETE   /clients/delete/{id}       Delete client

### 📋 Loans

# POST     /loans/create              Create a new loan
# GET      /loans                     Get all loans
# GET      /loans/{id}                Get loan by ID
# PUT      /loans/update/{id}         Update loan
# DELETE   /loans/delete/{id}         Delete loan

## 📝 Usage Examples

# Create a Book
curl -X POST http://localhost:8080/books/create \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Clean Code",
    "isbn": "978-0132350884",
    "publishedYear": 2008
  }'

# List All Books
curl http://localhost:8080/books

# Search Book by Title
curl http://localhost:8080/books/title/Clean%20Code

## 🧪 Tests

# Run the tests
./mvnw test

## 📁 Project Structure

src/
├── main/
│   ├── java/com/api/biblioteca/
│   │   ├── BibliotecaApplication.java
│   │   ├── controller/
│   │   │   ├── BookController.java
│   │   │   ├── ClientController.java
│   │   │   └── LoanController.java
│   │   └── model/
│   │       ├── entity/
│   │       │   ├── Author.java
│   │       │   ├── Book.java
│   │       │   ├── Client.java
│   │       │   └── Loan.java
│   │       ├── repository/
│   │       │   ├── BookRepository.java
│   │       │   ├── ClientRepository.java
│   │       │   └── LoanRepository.java
│   │       └── service/
│   │           ├── BookService.java
│   │           ├── ClientService.java
│   │           └── LoanService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/api/biblioteca/
        └── BibliotecaApplicationTests.java

## ⚙️ Configuration

# The application settings are located in:
# src/main/resources/application.properties

# Includes:
# - Server port (default: 8080)
# - H2 database configuration
# - JPA/Hibernate settings

## 🤝 Contributing

# 1. Fork this repository
# 2. Create a feature branch:
git checkout -b feature/MyFeature
# 3. Commit your changes:
git commit -m "Add MyFeature"
# 4. Push the branch:
git push origin feature/MyFeature
# 5. Open a Pull Request

