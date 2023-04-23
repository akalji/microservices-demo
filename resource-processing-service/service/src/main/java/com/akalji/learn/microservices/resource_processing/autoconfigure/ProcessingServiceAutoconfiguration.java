package com.akalji.learn.microservices.resource_processing.autoconfigure;

import com.akalji.learn.microservice.song.service.common.client.SongServiceClient;
import com.akalji.learn.microservice.song.service.common.client.SongServiceClientImpl;
import com.akalji.learn.microservices.resourceservice.common.client.ResourceServiceClient;
import com.akalji.learn.microservices.resourceservice.common.client.ResourceServiceClientImpl;
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
@EnableConfigurationProperties(ProcessingServiceProperties.class)
public class ProcessingServiceAutoconfiguration {

    @Autowired
    private ProcessingServiceProperties properties;

    @Bean
    public SongServiceClient songServiceClient() {
        return new SongServiceClientImpl();
    }

    @Bean
    public ResourceServiceClient resourceServiceClient() {
        return new ResourceServiceClientImpl();
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
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
