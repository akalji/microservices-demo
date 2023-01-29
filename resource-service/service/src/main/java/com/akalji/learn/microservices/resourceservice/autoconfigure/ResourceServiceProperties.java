package com.akalji.learn.microservices.resourceservice.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Nikolai_Tikhonov
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "resource-service")
public class ResourceServiceProperties {
    private String s3Endpoint;
    private String s3Bucket;
}
