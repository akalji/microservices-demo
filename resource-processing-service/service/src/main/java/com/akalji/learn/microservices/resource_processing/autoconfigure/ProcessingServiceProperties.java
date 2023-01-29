package com.akalji.learn.microservices.resource_processing.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Nikolai_Tikhonov
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "com.akalji.learn.microservices.processing-service")
public class ProcessingServiceProperties {

}
