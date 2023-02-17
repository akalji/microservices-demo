package com.akalji.learn.microservices.resource_processing.autoconfigure;

import com.akalji.learn.microservice.song.service.common.client.SongServiceClient;
import com.akalji.learn.microservice.song.service.common.client.SongServiceClientImpl;
import com.akalji.learn.microservices.resourceservice.common.client.ResourceServiceClient;
import com.akalji.learn.microservices.resourceservice.common.client.ResourceServiceClientImpl;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nikolai_Tikhonov
 */
@Configuration
@EnableConfigurationProperties(ProcessingServiceProperties.class)
public class ProcessingServiceAutoconfiguration {

    @Autowired
    private ProcessingServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public SongServiceClient songServiceClient() {
        var template = new RestTemplateBuilder().rootUri(properties.getSongServiceRootUri()).build();
        return new SongServiceClientImpl(template);
    }

    @Bean
    @ConditionalOnMissingBean
    public ResourceServiceClient resourceServiceClient() {
        var template = new RestTemplateBuilder().rootUri(properties.getResourceServiceRootUri()).build();
        return new ResourceServiceClientImpl(template);
    }

    @Bean
    public Queue newResourceUploadedQueue() {
        return new Queue(properties.getNewResourceUploadedQueueName(), false, false, false, null);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
