# Security JWT V1 System Rest API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white) ![SpringSecurity](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)

I developed a Rest API to manage products, built by using **Spring Boot, Java, and MySQL as the Database**, providing CRUD (Create, Read, Update, Delete) operations with authentication control enabled through **Spring Security and JWT tokens**. This API allows storing product information, such as: product code, name, price, country, and creation date. Storing user information such as username and password is also required to enable security.

I used some libraries for this Rest API such **Spring Web, Spring Data JPA, Validation, ModelMapper, MySQL Driver, Spring Security and java-jwt**. 

## Authentication
The API uses Spring Security for authentication control. The following roles are available:

```
USER -> Standard user role only for basic product operations (get available products).
ADMIN -> Admin role for advanced product operations (registration, updates, or deletions). In addition to product operations, this role allows other actions, such as getting and registering users.
```
Users can have multiple roles simultaneously, enabling them to perform both aa and advanced product operations. To access protected endpoints, the appropriate authentication credentials must be provided in the request header.

## Database Initialization with Default Data
For this project, default users and products have been created for quick testing using the scheme.sql and data.sql files. Additionally, endpoints are available to registering new users, logging in, and subsequently adding new products. The default users were created with the following credentials:

Username: marcos, Password: 123 (USER role).
Username: maria, Password: 123 (ADMIN role).

## API Endpoints
The API provides the following endpoints:
```
POST	/auth/users-register
POST	/auth/users-login
DELETE	/auth/users/{id}
GET	/auth/users
GET	/auth/users-by-id/{id}

GET	/api/roles
GET	/api/roles-by-id/{id}

GET	/api/products
GET	/api/ products-by-id/{id}
POST	/api/products
PUT	/api/products/{id}
DELETE	/api/products/{id}
```

## Database Config
For this API, the MySQL Database was used with the following configuration properties: 

- Database name: security_jwt_v1_db
- Username: root
- Password:

## Database Initialization Configuration
To run the application correctly for the first time, make sure the database already exists and set the following configuration in the application.properties file:

spring.sql.init.mode=always

For subsequent runs (once the database and its tables are already created), change the setting to:

spring.sql.init.mode=embedded

## Development Tools
This Rest API was built with:

- Spring Boot version: 3.3.4
- Java version: 17

## System Class Diagram
![SecurityJwtV1Diagram](https://github.com/user-attachments/assets/b2764789-35bc-4ee7-9933-b6cf7d8e375b)