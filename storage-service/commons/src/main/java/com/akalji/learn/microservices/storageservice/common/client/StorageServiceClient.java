package com.akalji.learn.microservices.storageservice.common.client;

import com.akalji.learn.microservices.storageservice.common.dto.StorageDto;

import java.util.List;

/**
 * @author Nikolai_Tikhonov
 */
public interface StorageServiceClient {
    List<StorageDto> getAllStorages();
    StorageDto getStorageById(Integer id);
}
