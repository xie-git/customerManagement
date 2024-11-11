package com.example.customerManagement;

import com.example.customerManagement.service.StartupLoggerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class CustomerManagementApplication {

	public static void main(String[] args) {
		// Capture start time and start timestamp
		ZonedDateTime startDateTime = ZonedDateTime.now(ZoneId.of("America/Chicago"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
		long startTime = System.currentTimeMillis();

		System.out.println("Application starting at " + startDateTime.format(formatter) + " Central Time");

		// Run the Spring Boot application
		ApplicationContext context = SpringApplication.run(CustomerManagementApplication.class, args);

		// Capture end time and end timestamp
		long endTime = System.currentTimeMillis();
		ZonedDateTime endDateTime = ZonedDateTime.now(ZoneId.of("America/Chicago"));

		// Calculate startup duration in seconds
		double startupTimeSeconds = (endTime - startTime) / 1000.0;

		// Display startup time in console
		System.out.println("Application started successfully at " + endDateTime.format(formatter));
		System.out.println("Startup time: " + startupTimeSeconds + " seconds");

		// Log the startup to DynamoDB
		StartupLoggerService logger = context.getBean(StartupLoggerService.class);
		logger.logStartup((long) (startupTimeSeconds * 1000)); // Passing the duration in milliseconds
	}
}