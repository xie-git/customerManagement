# Customer Management System

This project is a Customer Management System incorporating a RESTful API with Spring Boot, database interactions via JPA and PostgreSQL, containerization with Docker, and integration with AWS services. The AWS deployment uses EC2 and RDS for backend hosting, with an AWS Lambda function for serverless capabilities to handle specialized tasks, such as ETL processing.

---

### Technologies Used
- **Java 17** with Spring Boot for API development
- **JPA** for ORM and PostgreSQL for persistent storage
- **Docker** for containerization, allowing for consistent deployment environments
- **AWS EC2** and **RDS** for cloud hosting and managed database services
- **AWS Lambda** (Python) to perform data transformations and ETL tasks using PySpark