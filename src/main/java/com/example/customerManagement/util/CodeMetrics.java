package com.example.customerManagement.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CodeMetrics {

    public static int countLinesOfCode(File directory) throws IOException {
        int totalLines = 0;

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    totalLines += countLinesOfCode(file);  // Recursively count in subdirectories
                } else if (file.getName().endsWith(".java")) {
                    totalLines += countLinesInFile(file);
                }
            }
        }
        return totalLines;
    }

    private static int countLinesInFile(File file) throws IOException {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lines++;
            }
        }
        return lines;
    }
}