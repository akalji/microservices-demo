package com.akalji.learn.microservices.resourceservice.dao;

import com.akalji.learn.microservices.resourceservice.common.domain.Resource;

import java.util.List;
import java.util.Set;

/**
 * @author Nikolai_Tikhonov
 */
public interface ResourceDao {
    void deleteAllByIds(Set<Integer> ids);

    Resource getById(Integer id);

    Resource save(Resource resource);

    List<Resource> selectResourcesByIds(Set<Integer> ids);
}
