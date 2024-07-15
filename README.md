# Employee Management System

## Demo

[![Watch the video](https://i.vimeocdn.com/video/983580502.jpg)](https://player.vimeo.com/video/983580502)

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Setup](#setup)
3. [Configuration](#configuration)
4. [Running the Application](#running-the-application)
5. [API Endpoints](#api-endpoints)
6. [Request and Response Formats](#request-and-response-formats)
7. [Data Validation Rules](#data-validation-rules)
8. [Conclusion](#conclusion)

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java 11 or higher installed
- Maven installed

## Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. **Build the project using Maven:**
   ```bash
   mvn clean install
   ```

## Configuration

Swagger is configured to provide API documentation. You can access the Swagger UI at `/swagger-ui.html` once the application is running.

### Swagger Configuration

The Swagger configuration is located in `SwaggerConfig.java`:
```java
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management System API")
                        .version("1.0")
                        .description("API documentation for the Employee Management System"));
    }
}
```

## Running the Application

To run the application, use the following command:
```bash
mvn spring-boot:run
```

The application will be accessible at `http://localhost:8080`.

## API Endpoints

### View Home Page
- **URL:** `/`
- **Method:** `GET`
- **Description:** View the home page with the list of employees.

### Show New Employee Form
- **URL:** `/showNewEmployeeForm`
- **Method:** `GET`
- **Description:** Display the form to add a new employee.

### Save Employee
- **URL:** `/saveEmployee`
- **Method:** `POST`
- **Description:** Save a new employee.
- **Request Body:** Employee object

### Show Form For Update
- **URL:** `/showFormForUpdate/{id}`
- **Method:** `GET`
- **Description:** Display the form to update an existing employee.
- **Path Variable:**
  - `id (long):` ID of the employee to be updated

### Delete Employee
- **URL:** `/deleteEmployee/{id}`
- **Method:** `GET`
- **Description:** Delete an existing employee by ID.
- **Path Variable:**
  - `id (long):` ID of the employee to be deleted

## Request and Response Formats

### Employee Model
```java
public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    // Getters and setters
}
```

### Example Request (Save Employee)
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
}
```

### Example Response (Employee Details)
```json
{
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
}
```

## Data Validation Rules

- `firstName`: Cannot be empty or null
- `lastName`: Cannot be empty or null
- `email`: Must be a valid email format

Ensure the `Employee` model class has appropriate validation annotations to enforce these rules:
```java
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Employee {

    private long id;

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    // Getters and setters
}
```

## JPA Entity Mapping

### Employee Entity
Ensure you have the correct JPA annotations in the `Employee` entity class to map it to the database table:
```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    // Getters and setters
}
```

## Conclusion

This documentation provides the necessary steps to set up, configure, and run the Employee Management System project. It also details the available endpoints, request and response formats, data validation rules, and JPA entity mapping.
