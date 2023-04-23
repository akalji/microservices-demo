package com.akalji.learn.microservices.resourceservice.service;

import com.akalji.learn.microservices.resourceservice.autoconfigure.ResourceServiceProperties;
import com.akalji.learn.microservices.resourceservice.domain.Resource;
import com.akalji.learn.microservices.resourceservice.dao.ResourceDao;
import com.akalji.learn.microservices.storageservice.common.domain.StorageType;
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
    private StorageService storageService;

    @Autowired
    private ResourceServiceProperties properties;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Resource saveResource(InputStream binaryData) {
        var newUuid = UUID.randomUUID();
        var objectMetadata = new ObjectMetadata();
        amazonS3Client.putObject(storageService.getAnyStorageOfType(StorageType.STAGING).getBucket(), newUuid.toString(), binaryData, objectMetadata);
        var resource = new Resource();
        resource.setUri(newUuid.toString());
        resource.setStorageType(StorageType.STAGING);
        resource = resourceDao.save(resource);
        rabbitTemplate.convertAndSend(properties.getNewResourceUploadedQueueName(), resource.getId());
        return resource;
    }

    @Override
    public Resource getResourceById(Integer id) {
        Validate.notNull(id);
        var resource = resourceDao.getById(id);
        var bucket = storageService.getAnyStorageOfType(resource.getStorageType()).getBucket();
        var s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, resource.getUri()));
        resource.setContent(s3Object.getObjectContent());
        return resource;
    }

    @Override
    public void moveToStorage(StorageType to, Integer id) {
        Validate.notNull(to);
        Validate.notNull(id);

        var resource = resourceDao.getById(id);
        var newBucket = storageService.getAnyStorageOfType(to).getBucket();
        var originBucket = storageService.getAnyStorageOfType(resource.getStorageType()).getBucket();

        var s3Object = amazonS3Client.getObject(new GetObjectRequest(originBucket, resource.getUri()));
        amazonS3Client.putObject(newBucket, resource.getUri(), s3Object.getObjectContent(), s3Object.getObjectMetadata());
        // good place to validate putted
        resource.setStorageType(to);
        resourceDao.save(resource);
        amazonS3Client.deleteObject(originBucket, resource.getUri());
    }

    @Override
    public void deleteResourcesByIds(Set<Integer> ids) {
        Validate.notEmpty(ids);
        var resources = resourceDao.selectResourcesByIds(ids);
        for (var resource : resources) {
            var bucket = storageService.getAnyStorageOfType(resource.getStorageType()).getBucket();
            amazonS3Client.deleteObject(bucket, resource.getUri());
        }
        resourceDao.deleteAllByIds(ids);
    }
}
