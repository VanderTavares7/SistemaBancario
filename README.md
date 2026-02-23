# 🏦 Banking System API - Spring Boot

- A RESTful banking system API built with Spring Boot, designed to simulate real-world banking operations while applying clean architecture principles, business rules, and security best practices.
- This project was developed to strengthen backend engineering skills and demonstrate practical knowledge of authentication, authorization, and financial domain logic.

# 📌 Overview

- This application simulates a simplified banking environment, including:
- User management
- Credit and debit operations
- Installment purchase logic
- Credit limit handling
- Role-based access control
- Secure authentication flow
- All endpoints are tested using Postman, ensuring reliability and correct workflow behavior.

# 🎯 Project Objectives

- Strengthen backend development skills with Java and Spring Boot
- Apply real-world business rules in a financial context
- Implement secure authentication and authorization
- Design a scalable and well-structured REST API
- Build a production-like project for professional portfolio

# 🚀 Features
# 👤 User Management

- User registration
- Email validation (prevent duplicate accounts)
- Account balance management
- Credit limit management

# 💳 Credit Card Rules

- Users under 18 cannot request a credit card
- Credit limit request and limit increase logic
# 💰 Financial Operations
- Deposit and debit transactions
- Debit purchases allowed only with sufficient balance
- Credit purchases with installment plans

# 📦 Installment Business Rules

-Products priced up to R$ 1,000 → up to 6 installments
- Products priced above R$ 1,000 → up to 12 installments

# 🔐 Authentication & Authorization

- Spring Security integration
- Stateless authentication
- Role-based access control (USER, ADMIN)
- Protected endpoints

# 🧠 Business Logic Highlights

- Clear separation between Controller, Service, and Repository layers
- Use of DTOs to prevent exposure of sensitive data
- BigDecimal for financial precision
- Custom business rule validation
- Exception handling for robust API behavior

# 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JPA / Hibernate
- Relational Database
- Postman (API testing)

# 🧪 API Testing

- All endpoints are validated using Postman.
- A Postman collection can be included for easy testing and replication.

# 🏗️ Architecture Principles

- Layered architecture (Controller → Service → Repository)
- Stateless security configuration
- Business rule isolation in Service layer
- Clean and readable code organization

# 📈 Future Improvements

- Implement transaction management (@Transactional)
- Add global exception handler
- Implement unit and integration tests
- Add Swagger/OpenAPI documentation
- Docker containerization
- Implement caching for performance optimization

# 👨‍💻 Developer
- Developed as a backend practice project focused on building real-world API design skills and financial system logic.
