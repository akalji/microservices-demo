package com.akalji.learn.microservices.storageservice.dao;

import com.akalji.learn.microservices.storageservice.domain.Storage;
import com.akalji.learn.microservices.storageservice.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Nikolai_Tikhonov
 */
@Service
public class StorageDaoSpringDataImpl implements StorageDao {

    @Autowired
    private StorageRepository storageRepository;

    @Override
    public void deleteAllByIds(Set<Integer> ids) {
        storageRepository.deleteAllById(ids);
    }

    @Override
    public Storage getById(Integer id) {
        return storageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "The resource with the specified id does not exist"));
    }

    @Override
    public Storage save(Storage storage) {
        return storageRepository.save(storage);
    }

    @Override
    public List<Storage> selectStoragesByIds(Set<Integer> ids) {
        return storageRepository.findAllById(ids);
    }

    @Override
    public List<Storage> getAll() {
        return storageRepository.findAll();
    }
}
