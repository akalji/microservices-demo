package com.akalji.learn.microservices.resourceservice.autoconfigure;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nikolai_Tikhonov
 */
@Configuration
@EnableConfigurationProperties(ResourceServiceProperties.class)
public class ResourceServiceAutoconfiguration {
    @Autowired
    private ResourceServiceProperties properties;

    @Bean
    public AmazonS3 amazonS3client() {
        final var endpoint = new AwsClientBuilder.EndpointConfiguration(properties.getS3Endpoint(), "default");
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpoint)
                .build();
    }
}
