package com.akalji.learn.microservices.storageservice.service;

import com.akalji.learn.microservices.storageservice.domain.Storage;

import java.util.List;
import java.util.Set;

/**
 * @author Nikolai_Tikhonov
 */
public interface StorageService {
    Storage saveStorage(Storage storage);

    Storage getStorageById(Integer id);

    List<Storage> getAllStorages();

    void deleteStorageByIds(Set<Integer> integers);
}
