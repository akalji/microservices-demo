package com.akalji.learn.microservices.storageservice.dao;

import com.akalji.learn.microservices.storageservice.domain.Storage;

import java.util.List;
import java.util.Set;

/**
 * @author Nikolai_Tikhonov
 */
public interface StorageDao {
    void deleteAllByIds(Set<Integer> ids);

    Storage getById(Integer id);

    Storage save(Storage storage);

    List<Storage> selectStoragesByIds(Set<Integer> ids);

    List<Storage> getAll();
}
