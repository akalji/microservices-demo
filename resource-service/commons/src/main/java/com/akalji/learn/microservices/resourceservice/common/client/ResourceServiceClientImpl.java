package com.akalji.learn.microservices.resourceservice.common.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.akalji.learn.microservices.resourceservice.common.Endpoints.GET_RESOURCE;

/**
 * @author Nikolai_Tikhonov
 */
public class ResourceServiceClientImpl implements ResourceServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public byte[] getResourceById(Integer id) {
        var uriBuilder = UriComponentsBuilder.newInstance().path(GET_RESOURCE);
        return restTemplate.getForObject("http://resource-service"+uriBuilder.build(id).getPath(), byte[].class);
    }
}
