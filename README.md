Student Management System
Project Description

The Student Management System is a web-based application built with Java Spring Boot for the backend and HTML/CSS/JavaScript for the frontend. It allows administrators to manage student records efficiently, including adding, viewing, updating, deleting, and searching for students based on various criteria.

This project is designed for learning purposes and to demonstrate CRUD operations, pagination, and RESTful API integration.

Features

Add a new student record with details like name, gender, department, phone, and email.

View all students with pagination support.

Update existing student information.

Delete student records by ID.

Search for students by name, email, department, gender, phone number, and combinations of these fields.

Responsive and user-friendly frontend interface.

Technologies Used

Backend: Java, Spring Boot, Spring Data JPA

Frontend: HTML, CSS, JavaScript

Database: H2 / MySQL (can be configured)

Build Tool: Maven

Version Control: Git & GitHub

Installation

Clone the repository

git clone <your-repo-url>
cd student-management-system


Configure the database

Update application.properties with your database credentials.
Example for MySQL:

spring.datasource.url=jdbc:mysql://localhost:3306/student_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


Build and Run

mvn clean install
mvn spring-boot:run


Access the application
Open your browser and go to:

http://localhost:8080/

API Endpoints

GET /students – Retrieve all students (supports pagination)

GET /students/{id} – Retrieve a student by ID

POST /students – Add a new student

PUT /students/{id} – Update a student by ID

DELETE /students/{id} – Delete a student by ID

GET /students/search?name=&email=&department=&gender= – Search students by various criteria

Usage

Open the web application in a browser.

Use the navigation to add, view, update, or delete student records.

Use the search bar to filter students based on different fields.

Contributing

Feel free to fork this repository, make improvements, and submit pull requests. Any contributions to improve the functionality or UI are welcome.

License

This project is open-source and available under the MIT License.
