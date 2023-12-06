package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@SpringBootApplication
public class DemoApplication {

    @Value("${greeting.file.path}")
    private String greetingFilePath;

    @RequestMapping("/")
    String home() {
        // Read the greeting message from the specified file path
        String greetingMessage = readGreetingMessageFromFile(greetingFilePath);

        return greetingMessage;
    }

    private String readGreetingMessageFromFile(String filePath) {
        try {
            // Read the content of the file
            byte[] contentBytes = Files.readAllBytes(Paths.get(filePath));

            // Convert the bytes to a string
            return new String(contentBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading the greeting message from the file.";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
