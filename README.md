# 🛒 E-Commerce Microservices System

A distributed e-commerce system built with Spring Boot microservices architecture, featuring product management and order processing capabilities with service discovery, external API integration, and comprehensive exception handling.

## 🏗️ Architecture Overview

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Eureka Server │    │ Product Service │    │  Order Service  │
│   (Discovery)   │◄───┤   (Port: 8080)  │◄───┤  (Port: 8081)   │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                              │                        │
                              ▼                        ▼
                       ┌─────────────┐         ┌─────────────┐
                       │   MySQL     │         │   MySQL     │
                       │ ecommerce_db│         │  order_db   │
                       └─────────────┘         └─────────────┘
                              │
                              ▼
                       ┌─────────────┐
                       │ FakeStore   │
                       │   API       │
                       └─────────────┘
```

## 🚀 Features

### Product Management Service
- **RESTful APIs** for product and category CRUD operations
- **External API Integration** with FakeStore API using Retrofit
- **Database Migration** management with Flyway
- **Global Exception Handling** with custom exceptions
- **Service Discovery** integration with Netflix Eureka

### Order Management Service
- **Order Processing** with automatic price calculation
- **Inter-service Communication** using RestTemplate
- **Order Status Management** with enum-based status tracking
- **Complex Entity Relationships** between orders and order items
- **Service-to-Service** product validation

## 🛠️ Tech Stack

- **Backend:** Java 17, Spring Boot 3.5.3
- **Database:** MySQL 8.0
- **ORM:** Spring Data JPA, Hibernate
- **Migration:** Flyway
- **Service Discovery:** Netflix Eureka
- **HTTP Client:** Retrofit, RestTemplate
- **Build Tool:** Gradle
- **Code Generation:** Lombok
- **Configuration:** Dotenv

## 📋 Prerequisites

- Java 17 or higher
- MySQL 8.0
- Gradle 7.0+
- Git

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd ecommerce-microservices
```

### 2. Database Setup
Create two MySQL databases:
```sql
CREATE DATABASE ecommerce_db;
CREATE DATABASE order_db;
```

### 3. Environment Configuration
Create `.env` files in both service directories:

**Product Service (.env):**
```env
PORT=8080
DB_URL=jdbc:mysql://localhost:3306/ecommerce_db
DB_USERNAME=root
DB_PASSWORD=your_password
```

**Order Service (.env):**
```env
PORT=8081
DB_URL=jdbc:mysql://localhost:3306/order_db
DB_USERNAME=root
DB_PASSWORD=your_password
```

### 4. Start Eureka Server (Optional)
```bash
# If you have Eureka server running
# Default URL: http://localhost:8761
```

### 5. Run the Services

**Product Service:**
```bash
cd product-service
./gradlew bootRun
```

**Order Service:**
```bash
cd order-service
./gradlew bootRun
```

## 📚 API Documentation

### Product Service Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products/{id}` | Get product by ID |
| POST | `/api/products` | Create new product |
| GET | `/api/products/{id}/details` | Get product with category details |
| GET | `/api/categories` | Get all categories |
| GET | `/api/categories/{id}/products` | Get products by category |

### Order Service Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/orders` | Create new order |

### Sample API Requests

**Create Product:**
```json
POST /api/products
{
  "title": "iPhone 15",
  "price": 999,
  "description": "Latest iPhone model",
  "categoryId": 1,
  "brand": "Apple",
  "model": "iPhone 15",
  "color": "Space Black"
}
```

**Create Order:**
```json
POST /api/orders
{
  "userId": 123,
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}
```

## 🗄️ Database Schema

### Product Service (ecommerce_db)
- **category** - Product categories
- **product** - Product information with category relationship

### Order Service (order_db)
- **Orders** - Order information with status tracking
- **OrderItem** - Individual items within orders

## 🔧 Configuration

### Application Properties
Both services support the following configurations:

```properties
# Server Configuration
server.port=${PORT}

# Database Configuration
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Flyway Configuration (Product Service)
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true

# Eureka Configuration
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

## 🏛️ Architecture Patterns

- **Microservices Architecture** - Decoupled services with independent databases
- **Service Discovery** - Netflix Eureka for service registration and discovery
- **Clean Architecture** - Layered design with separation of concerns
- **Repository Pattern** - Data access abstraction
- **DTO Pattern** - Data transfer objects for API communication
- **Global Exception Handling** - Centralized error management

## 🧪 Testing

Run tests for both services:
```bash
# Product Service
cd product-service
./gradlew test

# Order Service
cd order-service
./gradlew test
```

## 📦 Build & Deploy

### Build JAR files:
```bash
# Product Service
cd product-service
./gradlew build

# Order Service
cd order-service
./gradlew build
```

### Run JAR files:
```bash
java -jar product-service/build/libs/product-service-0.0.1-SNAPSHOT.jar
java -jar order-service/build/libs/order-service-0.0.1-SNAPSHOT.jar
```

## 🔍 Monitoring & Logging

- **Service Discovery Dashboard:** http://localhost:8761 (Eureka)
- **Application Logs:** Configured with Spring Boot default logging
- **Database Queries:** SQL queries logged to console (development mode)

## 🚨 Exception Handling

The system implements comprehensive exception handling:

- **ProductNotFoundException** - When product is not found
- **CategoryNotFoundException** - When category is not found
- **GlobalExceptionHandler** - Centralized exception handling with @RestControllerAdvice
- **Standardized Error Responses** - Consistent error response format

## 🔄 Database Migrations

Product service uses Flyway for database versioning:
- `V1__create_category_table.sql`
- `V2__create_product_table.sql`
- `V3__add_rating_column_to_product.sql`
- `V4__add_star_column_to_product.sql`
- `V5__undo_star_column_from_product.sql`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Sanket Walunj**
- GitHub: [@sanketwalunj](https://github.com/sanketwalunj)
- LinkedIn: [Sanket Walunj](https://linkedin.com/in/sanketwalunj)

## 🙏 Acknowledgments

- Spring Boot community for excellent documentation
- Netflix for Eureka service discovery
- MySQL team for robust database support
- FakeStore API for providing test data

---

⭐ **Star this repository if you found it helpful!**
