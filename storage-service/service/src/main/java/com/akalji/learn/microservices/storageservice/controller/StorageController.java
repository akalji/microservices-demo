package com.akalji.learn.microservices.storageservice.controller;

import com.akalji.learn.microservices.commons.dto.IdDto;
import com.akalji.learn.microservices.storageservice.common.dto.StorageCreateDto;
import com.akalji.learn.microservices.storageservice.common.dto.StorageDto;
import com.akalji.learn.microservices.storageservice.mapper.StorageMapper;
import com.akalji.learn.microservices.storageservice.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author Nikolai_Tikhonov
 */
@RestController
@RequestMapping("/storages")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping()
    public IdDto createStorage(StorageCreateDto storageDto) {
        var storage = storageService.saveStorage(StorageMapper.toEntity(storageDto));
        return new IdDto(storage.getId());
    }

    @GetMapping("/{id}")
    public StorageDto getStorage(@PathVariable("id") Integer id) {
        var storage = storageService.getStorageById(id);
        return StorageMapper.toDto(storage);
    }

    @GetMapping()
    public List<StorageDto> getStorages() {
        var storages= storageService.getAllStorages();
        return StorageMapper.toDto(storages);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResources(@RequestParam("ids") Collection<Integer> ids) {
        storageService.deleteStorageByIds(new HashSet<>(ids));
    }
}
