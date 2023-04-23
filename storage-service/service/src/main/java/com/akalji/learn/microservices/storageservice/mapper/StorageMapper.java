package com.akalji.learn.microservices.storageservice.mapper;

import com.akalji.learn.microservices.storageservice.common.dto.StorageCreateDto;
import com.akalji.learn.microservices.storageservice.common.dto.StorageDto;
import com.akalji.learn.microservices.storageservice.domain.Storage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nikolai_Tikhonov
 */
public final class StorageMapper {
    public static StorageDto toDto(Storage storage) {
        var storageDto = new StorageDto();
        storageDto.setId(storage.getId());
        storageDto.setStorageType(storage.getStorageType());
        storageDto.setBucket(storage.getBucket());
        storageDto.setPath(storage.getPath());

        return storageDto;
    }

    public static List<StorageDto> toDto(List<Storage> storages) {
        if (storages == null) {
            return null;
        }

        if (storages.isEmpty()) {
            return Collections.emptyList();
        }

        return storages.stream()
                .map(StorageMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Storage toEntity(StorageCreateDto storageDto) {
        var storage = new Storage();
        storage.setStorageType(storageDto.getStorageType());
        storage.setBucket(storageDto.getBucket());
        storage.setPath(storageDto.getPath());

        return storage;
    }
}
