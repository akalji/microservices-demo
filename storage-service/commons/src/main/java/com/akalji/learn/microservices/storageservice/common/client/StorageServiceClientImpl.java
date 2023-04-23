package com.akalji.learn.microservices.storageservice.common.client;

import com.akalji.learn.microservices.storageservice.common.Endpoints;
import com.akalji.learn.microservices.storageservice.common.dto.StorageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @author Nikolai_Tikhonov
 */
public class StorageServiceClientImpl implements StorageServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<StorageDto> getAllStorages() {
        var uriBuilder = UriComponentsBuilder.newInstance().path(Endpoints.GET_ALL_STORAGES);
        return restTemplate.exchange(
                "http://storage-service" + uriBuilder.build().getPath(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StorageDto>>() {})
                .getBody();
    }

    @Override
    public StorageDto getStorageById(Integer id) {
        var uriBuilder = UriComponentsBuilder.newInstance().path(Endpoints.GET_STORAGE);
        return restTemplate.getForObject("http://storage-service" + uriBuilder.build(id).getPath(), StorageDto.class);
    }
}
