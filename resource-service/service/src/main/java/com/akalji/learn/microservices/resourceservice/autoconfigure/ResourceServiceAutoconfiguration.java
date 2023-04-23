package com.akalji.learn.microservices.resourceservice.autoconfigure;

import com.akalji.learn.microservices.storageservice.common.client.StorageServiceClient;
import com.akalji.learn.microservices.storageservice.common.client.StorageServiceClientImpl;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Nikolai_Tikhonov
 */
@Configuration
@EnableConfigurationProperties(ResourceServiceProperties.class)
public class ResourceServiceAutoconfiguration {
    @Autowired
    private ResourceServiceProperties properties;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public StorageServiceClient resourceServiceClient() {
        return new StorageServiceClientImpl();
    }

    @Bean
    public AmazonS3 amazonS3client() {
        final var endpoint = new AwsClientBuilder.EndpointConfiguration(properties.getS3Endpoint(), "us-east-1");
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpoint)
                .withPathStyleAccessEnabled(true)
                .build();
    }

    @Bean
    public Queue newResourceUploadedQueue() {
        return new Queue(properties.getNewResourceUploadedQueueName(), false, false, false, null);
    }

    @Bean
    public Queue resourceProcessedQueue() {
        return new Queue(properties.getResourceProcessedQueueName(), false, false, false, null);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
