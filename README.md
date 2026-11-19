# ForensicTrack

## Digital Forensics Case and Evidence Management System

ForensicTrack is a web-based application developed using Advanced Java Technologies to manage digital forensic investigations, cases, evidence records, investigator notes, and chain-of-custody activities.

### Project Information

- **Project Name:** ForensicTrack
- **Project Type:** Digital Forensics Case Management Web Application
- **Domain:** Digital Forensics and Investigation Management
- **Purpose:** To provide a centralized platform for managing investigation cases and maintaining digital evidence records.
- **Main Objective:** To organize investigation information and maintain traceability of evidence-handling activities.
- **Database:** forensictrack_db

### Features

- User registration and login
- Role-based access control
- Investigation case management
- Digital evidence management
- Case notes and investigation updates
- Chain-of-custody record management
- Investigation overview

### Technologies Used

- Java
- Java Servlets
- JSP (JavaServer Pages)
- JSTL and Expression Language (EL)
- Hibernate ORM
- Spring Framework and Dependency Injection
- MySQL / MariaDB
- Apache Tomcat 10.1
- Maven
- NetBeans IDE
- HTML and CSS

### Project Structure

```text
ForensicTrackHibernate/
├── src/
│   └── main/
│       ├── java/
│       └── webapp/
├── nb-configuration.xml
└── pom.xml
```

### Database Setup

1. Install and start XAMPP.
2. Start MySQL.
3. Open phpMyAdmin.
4. Create or import the database named `forensictrack_db`.
5. Configure the database connection settings in the project.
6. Ensure the database credentials match your local MySQL configuration.


### Build and Run

1. Open the project in NetBeans IDE.
2. Allow Maven to resolve the project dependencies.
3. Configure Apache Tomcat 10.1 as the server.
4. Configure the local database connection.
5. Build the project using Maven.
6. Deploy and run the application on Tomcat.

### Author

**Tanushree Lingaraj Patil**

### Academic Project

Developed as part of the Advanced Java Technology course.
