package com.akalji.learn.microservices.resourceservice.service;

import com.akalji.learn.microservices.storageservice.common.domain.StorageType;
import com.akalji.learn.microservices.storageservice.common.dto.StorageDto;

/**
 * @author Nikolai_Tikhonov
 */
public interface StorageService {
    StorageDto getAnyStorageOfType(StorageType storageType);
}
