# Use the Amazon Corretto 17 image stored in ECR as the base image for Java 17 runtime
FROM 509554853164.dkr.ecr.us-east-1.amazonaws.com/customermanagement:amazoncorretto-17

# Set the working directory inside the container to /app
WORKDIR /app

# Copy the JAR file created during the Maven build from the local target directory to the /app directory in the container
COPY target/customerManagement-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port 8080 so that the application can be accessed on this port
EXPOSE 8080

# Specify the command to run the application: use Java to run the JAR file
CMD ["java", "-jar", "/app/app.jar"]