package com.akalji.learn.microservices.resourceservice.dao;

import com.akalji.learn.microservices.resourceservice.domain.Resource;
import com.akalji.learn.microservices.resourceservice.repository.ResourceRepository;
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
public class ResourceDaoSpringDataImpl implements ResourceDao {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public void deleteAllByIds(Set<Integer> ids) {
        resourceRepository.deleteAllById(ids);
    }

    @Override
    public Resource getById(Integer id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "The resource with the specified id does not exist"));
    }

    @Override
    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public List<Resource> selectResourcesByIds(Set<Integer> ids) {
        return resourceRepository.findAllById(ids);
    }
}
