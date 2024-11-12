# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file generated by your build tool (ensure it's in target after build)
COPY target/customerManagement-0.0.1-SNAPSHOT.jar app.jar

# Expose the application’s port (e.g., 8080)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]