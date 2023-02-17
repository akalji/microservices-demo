package com.akalji.learn.microservices.resourceservice.service;

import com.akalji.learn.microservices.resourceservice.autoconfigure.ResourceServiceProperties;
import com.akalji.learn.microservices.resourceservice.domain.Resource;
import com.akalji.learn.microservices.resourceservice.dao.ResourceDao;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.apache.commons.lang3.Validate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Set;
import java.util.UUID;

/**
 * @author Nikolai_Tikhonov
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private AmazonS3 amazonS3Client;

    @Autowired
    private ResourceServiceProperties properties;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Resource saveResource(InputStream binaryData) {
        var newUuid = UUID.randomUUID();
        var objectMetadata = new ObjectMetadata();
        amazonS3Client.putObject(properties.getS3Bucket(), newUuid.toString(), binaryData, objectMetadata);
        var resource = new Resource();
        resource.setUri(newUuid.toString());
        resource = resourceDao.save(resource);
        rabbitTemplate.convertAndSend(properties.getNewResourceUploadedQueueName(), resource.getId());
        return resource;
    }

    @Override
    public Resource getResourceById(Integer id) {
        Validate.notNull(id);
        var resource = resourceDao.getById(id);
        var s3Object = amazonS3Client.getObject(new GetObjectRequest(properties.getS3Bucket(), resource.getUri()));
        resource.setContent(s3Object.getObjectContent());
        return resource;
    }

    @Override
    public void deleteResourcesByIds(Set<Integer> ids) {
        Validate.notEmpty(ids);
        var resources = resourceDao.selectResourcesByIds(ids);
        for (var resource : resources) {
            amazonS3Client.deleteObject(properties.getS3Bucket(), resource.getUri());
        }
        resourceDao.deleteAllByIds(ids);
    }
}
