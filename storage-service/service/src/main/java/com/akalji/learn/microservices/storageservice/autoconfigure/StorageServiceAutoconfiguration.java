package com.akalji.learn.microservices.storageservice.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nikolai_Tikhonov
 */
@Configuration
@EnableConfigurationProperties(StorageServiceProperties.class)
public class StorageServiceAutoconfiguration {
    @Autowired
    private StorageServiceProperties properties;

}
