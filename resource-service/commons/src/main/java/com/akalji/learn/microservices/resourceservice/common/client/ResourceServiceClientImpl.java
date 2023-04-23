package com.akalji.learn.microservices.resourceservice.common.client;

import com.akalji.learn.microservices.resourceservice.common.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Nikolai_Tikhonov
 */
public class ResourceServiceClientImpl implements ResourceServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public byte[] getResourceById(Integer id) {
        var uriBuilder = UriComponentsBuilder.newInstance().path(Endpoints.GET_RESOURCE);
        return restTemplate.getForObject("http://resource-service"+uriBuilder.build(id).getPath(), byte[].class);
    }
}
