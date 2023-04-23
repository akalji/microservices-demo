package com.akalji.learn.microservices.resourceservice.handler;


import com.akalji.learn.microservices.resourceservice.service.ResourceService;
import com.akalji.learn.microservices.storageservice.common.domain.StorageType;
import org.apache.commons.lang3.Validate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Nikolai_Tikhonov
 */
@Component
public class ResourceProcessedEventHandler {

    @Autowired
    private ResourceService resourceService;

    @RabbitListener(queues = "resourceProcessed")
    public void handle(String message) {
        Validate.notBlank(message);

        resourceService.moveToStorage(StorageType.PERMANENT, Integer.parseInt(message));
    }
}
