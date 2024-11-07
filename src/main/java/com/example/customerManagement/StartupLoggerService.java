package com.example.customerManagement;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StartupLoggerService {

    private static final Logger logger = LoggerFactory.getLogger(StartupLoggerService.class);
    private final DynamoDbClient dynamoDbClient;
    private static final String TABLE_NAME = "ApplicationStartupLogs";

    @Value("${app.version}")
    private String appVersion;

    public StartupLoggerService(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void logStartup(long startupDurationMillis) {
        String runId = UUID.randomUUID().toString();  // Unique ID for each startup
        LocalDateTime timestamp = LocalDateTime.now(); // Human-readable timestamp
        long epochSeconds = Instant.now().getEpochSecond(); // Epoch time in seconds
        double startupDurationSeconds = (double) startupDurationMillis / 1000; // Convert to seconds

        int linesOfCode = 0;
        try {
            linesOfCode = CodeMetrics.countLinesOfCode(new File("src/main/java"));
        } catch (IOException e) {
            logger.error("Error counting lines of code", e);
        }

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("RunID", AttributeValue.builder().s(runId).build());
        item.put("Timestamp", AttributeValue.builder().s(timestamp.toString()).build()); // Human-readable timestamp as a string
        item.put("EpochTimestampSeconds", AttributeValue.builder().n(Long.toString(epochSeconds)).build()); // Epoch time as a number
        item.put("StartupDurationSeconds", AttributeValue.builder().n(Double.toString(startupDurationSeconds)).build()); // Duration as a number in seconds
        item.put("AppVersion", AttributeValue.builder().s(appVersion).build());
        item.put("LinesOfCode", AttributeValue.builder().n(Integer.toString(linesOfCode)).build()); // Log LOC

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);

        // Log a success message with details
        logger.info("Successfully logged startup entry with RunID: {}, Timestamp: {}, Duration: {} seconds, AppVersion: {}, LOC: {}",
                runId, timestamp, startupDurationSeconds, appVersion, linesOfCode);
    }
}