package com.akalji.learn.microservices.resource_processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author Nikolai_Tikhonov
 */
@SpringBootApplication
@EnableRetry
public class ResourceProcessingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceProcessingServiceApplication.class, args);
    }

}
