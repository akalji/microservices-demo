package com.akalji.learn.microservices.resource_processing.handler;

import com.akalji.learn.microservices.commons.event.Event;
import com.akalji.learn.microservices.resource_processing.service.SongProcessingService;
import com.akalji.learn.microservices.resourceservice.common.event.ResourceUploadedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Nikolai_Tikhonov
 */
@Component
public class NewResourceEventHandler {
    @Autowired
    private SongProcessingService songProcessingService;

    public void handle(ResourceUploadedEvent event) {

    }
}
