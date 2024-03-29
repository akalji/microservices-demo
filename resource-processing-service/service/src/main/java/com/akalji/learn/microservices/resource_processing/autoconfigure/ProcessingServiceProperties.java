package com.akalji.learn.microservices.resource_processing.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Nikolai_Tikhonov
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "processing-service")
public class ProcessingServiceProperties {
    private String newResourceUploadedQueueName;
    private String songServiceRootUri;
    private String resourceServiceRootUri;
    private String resourceProcessedQueueName;
}
