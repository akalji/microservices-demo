package com.akalji.learn.microservices.resourceservice.service;

import com.akalji.learn.microservices.resourceservice.common.domain.Resource;

import java.io.InputStream;
import java.util.Set;

/**
 * @author Nikolai_Tikhonov
 */
public interface ResourceService {
    Resource saveResource(InputStream binaryData);

    Resource getResourceById(Integer id);

    void deleteResourcesByIds(Set<Integer> integers);
}
