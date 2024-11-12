FROM amazoncorretto:17-alpine
WORKDIR /app
COPY target/customerManagement-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]