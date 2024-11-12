FROM 509554853164.dkr.ecr.us-east-1.amazonaws.com/customermanagement:amazoncorretto-17
WORKDIR /app
COPY target/customerManagement-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]