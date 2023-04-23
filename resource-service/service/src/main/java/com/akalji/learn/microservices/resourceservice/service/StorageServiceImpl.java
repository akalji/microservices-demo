package com.akalji.learn.microservices.resourceservice.service;

import com.akalji.learn.microservices.storageservice.common.client.StorageServiceClient;
import com.akalji.learn.microservices.storageservice.common.domain.StorageType;
import com.akalji.learn.microservices.storageservice.common.dto.StorageDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nikolai_Tikhonov
 */
@Service
public class StorageServiceImpl implements StorageService {
    private static final int CACHE_LIFETIME = 1 * 60 * 1000; //minutes

    private List<StorageDto> storages;

    @Autowired
    private StorageServiceClient storageServiceClient;

    private Instant lastUpdated;


    @Override
    @CircuitBreaker(name = "getStorage", fallbackMethod = "getStorageFromCache")
    public StorageDto getAnyStorageOfType(StorageType storageType) {
        Validate.notNull(storageType);

        if (lastUpdated == null || Duration.between(lastUpdated, Instant.now()).toMillis() >= CACHE_LIFETIME) {
            updateCache();
        }
        return storageServiceClient.getAllStorages().stream()
                .filter(s -> s.getStorageType()==storageType)
                .findAny().orElseThrow();
    }

    private StorageDto getStorageFromCache(StorageType storageType, Exception e) {
        return storages.stream()
                .filter(s -> s.getStorageType()==storageType)
                .findAny().orElseThrow();
    }

    private void updateCache() {
        storages = storageServiceClient.getAllStorages();
        lastUpdated = Instant.now();
    }
}
