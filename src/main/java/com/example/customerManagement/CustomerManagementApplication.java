package com.example.customerManagement;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class CustomerManagementApplication {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis(); // Capture start time
		ZonedDateTime startDateTime = ZonedDateTime.now(ZoneId.of("America/Chicago"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
		System.out.println("Application starting at " + startDateTime.format(formatter) + " Central Time");

		SpringApplication.run(CustomerManagementApplication.class, args);

		long endTime = System.currentTimeMillis(); // Capture end time
		double startupTimeSeconds = (endTime - startTime) / 1000.0;
		ZonedDateTime endDateTime = ZonedDateTime.now(ZoneId.of("America/Chicago"));
		System.out.println("Application started successfully at " + endDateTime.format(formatter));
		System.out.println("Startup time: " + startupTimeSeconds + " seconds"); // Calculate and display startup duration
	}

	@PostConstruct
	public void init() {
		System.out.println("Application started successfully! (in post construct)");
	}

}
