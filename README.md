# E-Commerce API Testing Practice

Spring Boot REST API created for a 45-day Postman + Java API testing course.

## Run

Requirements:
- Java 17+
- Maven 3.9+

```bash
mvn spring-boot:run
```

Base URL:
`http://localhost:8080`

H2 console:
`http://localhost:8080/h2-console`

JDBC URL:
`jdbc:h2:mem:ecommerce`

## Current endpoints

### Authentication
- POST `/api/auth/login`
- POST `/api/auth/logout`
- POST `/api/auth/refresh`

### Users
- GET `/api/users`
- GET `/api/users/{id}`
- POST `/api/users`
- PUT `/api/users/{id}`
- DELETE `/api/users/{id}`

### Products
- GET `/api/products`
- GET `/api/products/{id}`
- POST `/api/products`
- PUT `/api/products/{id}`
- PATCH `/api/products/{id}`
- DELETE `/api/products/{id}`

Seed login:
- test@example.com / Password@123
- admin@example.com / Admin@123

Seed products:
1 Wireless Mouse
2 Mechanical Keyboard
3 USB-C Cable

## Important

This is intentionally a training API, not a production payment/authentication system. More modules (categories, cart, orders, payments) will be added during the course.
