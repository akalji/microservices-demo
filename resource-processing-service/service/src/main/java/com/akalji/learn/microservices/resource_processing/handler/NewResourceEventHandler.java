package com.akalji.learn.microservices.resource_processing.handler;


import com.akalji.learn.microservices.resource_processing.service.SongProcessingService;
import org.apache.commons.lang3.Validate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Nikolai_Tikhonov
 */
@Component
public class NewResourceEventHandler {
    @Autowired
    private SongProcessingService songProcessingService;

    @RabbitListener(queues = "resourceUploaded")
    public void handle(String message) {
        Validate.notBlank(message);

        songProcessingService.retrieveAndSaveSongMetadata(Integer.parseInt(message));
    }
}
