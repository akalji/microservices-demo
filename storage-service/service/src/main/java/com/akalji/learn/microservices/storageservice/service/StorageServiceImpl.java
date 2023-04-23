package com.akalji.learn.microservices.storageservice.service;

import com.akalji.learn.microservices.storageservice.dao.StorageDao;
import com.akalji.learn.microservices.storageservice.domain.Storage;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author Nikolai_Tikhonov
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Override
    public Storage saveStorage(Storage storage) {
        Validate.notNull(storage);
        return storageDao.save(storage);
    }

    @Override
    public Storage getStorageById(Integer id) {
        Validate.notNull(id);
        return storageDao.getById(id);
    }

    @Override
    public List<Storage> getAllStorages() {
        return storageDao.getAll();
    }

    @Override
    public void deleteStorageByIds(Set<Integer> ids) {
        Validate.notEmpty(ids);
        storageDao.deleteAllByIds(ids);
    }
}
