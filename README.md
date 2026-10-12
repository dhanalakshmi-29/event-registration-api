# Event Registration REST API

A Spring Boot REST API for event registration with validation for future dates and valid email addresses.

## Features

- ✅ Validates email addresses using `@Email` annotation
- ✅ Ensures event dates are in the future using `@Future` annotation
- ✅ Global exception handling for validation errors
- ✅ RESTful API design
- ✅ JSON request/response format

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Dependencies

- Spring Boot 3.3.3
- Spring Web
- Spring Validation
- Lombok

## Project Structure

```
src/main/java/com/example/eventregistration/
├── EventRegistrationApplication.java
├── controller/
│   └── EventRegistrationController.java
├── dto/
│   └── EventRegistrationRequest.java
└── exception/
    ├── ApiError.java
    └── GlobalExceptionHandler.java

src/main/resources/
└── application.properties
```

## Building and Running

### Build the project
```bash
./mvnw clean install
```

### Run the application
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### Register Event

**Endpoint:** `POST /api/events/register`

**Request Body:**
```json
{
  "fullName": "John Doe",
  "email": "john.doe@example.com",
  "eventDate": "2030-12-31"
}
```

**Success Response (201 Created):**
```json
{
  "message": "Event registration successful",
  "fullName": "John Doe",
  "email": "john.doe@example.com",
  "eventDate": "2030-12-31",
  "registeredAt": "2026-10-12T12:00:00"
}
```

**Error Response (400 Bad Request):**
```json
{
  "timestamp": "2026-10-12T12:00:00",
  "status": 400,
  "error": "Validation failed",
  "details": {
    "fullName": "Full name is required",
    "email": "Please provide a valid email address",
    "eventDate": "Event date must be in the future"
  }
}
```

## Validation Rules

| Field | Validation | Error Message |
|-------|-----------|---------------|
| fullName | Not blank | Full name is required |
| email | Valid email format | Please provide a valid email address |
| eventDate | Must be in the future | Event date must be in the future |

## Testing with cURL

### Valid Request
```bash
curl -X POST http://localhost:8080/api/events/register \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "John Doe",
    "email": "john.doe@example.com",
    "eventDate": "2030-12-31"
  }'
```

### Invalid Request (Past Date)
```bash
curl -X POST http://localhost:8080/api/events/register \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "John Doe",
    "email": "john.doe@example.com",
    "eventDate": "2020-01-01"
  }'
```

### Invalid Request (Bad Email)
```bash
curl -X POST http://localhost:8080/api/events/register \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "John Doe",
    "email": "invalid-email",
    "eventDate": "2030-12-31"
  }'
```

## License

MIT License