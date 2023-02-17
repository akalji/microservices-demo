package com.akalji.learn.microservices.resourceservice.common.client;

import org.apache.commons.lang3.Validate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.akalji.learn.microservices.resourceservice.common.Endpoints.GET_RESOURCE;

/**
 * @author Nikolai_Tikhonov
 */
public class ResourceServiceClientImpl implements ResourceServiceClient {

    private RestTemplate restTemplate;

    public ResourceServiceClientImpl(RestTemplate restTemplate) {
        Validate.notNull(restTemplate, "Webclient is null");
        this.restTemplate = restTemplate;
    }

    @Override
    public byte[] getResourceById(Integer id) {
        var uriBuilder = UriComponentsBuilder.newInstance().path(GET_RESOURCE);
        return restTemplate.getForObject(uriBuilder.build(id).getPath(), byte[].class);
    }
}
