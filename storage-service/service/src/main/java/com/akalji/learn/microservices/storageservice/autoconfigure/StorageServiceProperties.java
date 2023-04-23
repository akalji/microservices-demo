package com.akalji.learn.microservices.storageservice.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Nikolai_Tikhonov
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "storage-service")
public class StorageServiceProperties {
}
