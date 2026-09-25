# E-Commerce API Testing Project

Java 21 + Spring Boot + MySQL REST API designed for Postman and REST Assured practice.

## Stack

- Java 21
- Spring Boot 3.5.5
- Spring Web
- Spring Validation
- Spring Data JPA / Hibernate
- MySQL 8.4
- Maven
- Docker Compose

## 1. Start MySQL with Docker

Requirements:
- JDK 21
- Maven
- Docker Desktop

Run:

```bash
docker compose up -d
```

This creates:

- Database: `ecommerce_api`
- User: `ecommerce_user`
- Password: `change-me`
- Port: `3306`

For a real environment, change these credentials.

## 2. Run Spring Boot

```bash
mvn spring-boot:run
```

API:

```text
http://localhost:8080
```

## 3. Test in Postman

### Login

```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "email": "test@example.com",
  "password": "Password@123"
}
```

### Products

```http
GET    http://localhost:8080/api/products
GET    http://localhost:8080/api/products/1
POST   http://localhost:8080/api/products
PUT    http://localhost:8080/api/products/1
PATCH  http://localhost:8080/api/products/1
DELETE http://localhost:8080/api/products/1
```

### Users

```http
GET    http://localhost:8080/api/users
GET    http://localhost:8080/api/users/1
POST   http://localhost:8080/api/users
PUT    http://localhost:8080/api/users/1
DELETE http://localhost:8080/api/users/1
```

## Test users

```text
test@example.com / Password@123
admin@example.com / Admin@123
```

These are practice credentials only. Do not use them in production.

## Database

The database is persistent through the Docker volume:

```text
ecommerce_mysql_data
```

Spring Boot uses environment variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

Never commit real production credentials to GitHub.

## Deployment

For Railway or another host, set:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

and make sure the deployment environment has access to the MySQL server.

## Deliberate learning target

The project currently contains Authentication, Users, and Products. Categories, Cart, Orders, and Payments should be implemented progressively during the API-testing training so each new module becomes a testing exercise.
