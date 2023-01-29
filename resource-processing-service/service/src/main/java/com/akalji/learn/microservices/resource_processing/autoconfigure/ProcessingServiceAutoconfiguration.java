package com.akalji.learn.microservices.resource_processing.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nikolai_Tikhonov
 */
@Configuration
@EnableConfigurationProperties(ProcessingServiceProperties.class)
public class ProcessingServiceAutoconfiguration {

}
