package com.akalji.learn.microservices.resource_processing.service;

import com.akalji.learn.microservice.song.service.common.client.SongServiceClient;
import com.akalji.learn.microservice.song.service.common.dto.SongDto;
import com.akalji.learn.microservices.resource_processing.autoconfigure.ProcessingServiceProperties;
import com.akalji.learn.microservices.resource_processing.exception.ResourceProcessingException;
import com.akalji.learn.microservices.resource_processing.util.Mp3Utils;
import com.akalji.learn.microservices.resourceservice.common.client.ResourceServiceClient;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.apache.commons.io.FileUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Nikolai_Tikhonov
 */
@Service
public class SongProcessingServiceImpl implements SongProcessingService {

    @Autowired
    private ProcessingServiceProperties properties;

    @Autowired
    private SongServiceClient songServiceClient;

    @Autowired
    private ResourceServiceClient resourceServiceClient;

    @Autowired
    private ProcessingServiceProperties serviceProperties;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    @Retryable(value = ResourceProcessingException.class, maxAttempts = 2, backoff = @Backoff(delay = 200))
    public void retrieveAndSaveSongMetadata(Integer resourceId) {
        try {
            final var tempFile = File.createTempFile("resource", UUID.randomUUID().toString());

            FileUtils.writeByteArrayToFile(tempFile, resourceServiceClient.getResourceById(resourceId));
            saveSongMetadata(tempFile, resourceId);
            rabbitTemplate.convertAndSend(properties.getResourceProcessedQueueName(), resourceId);
        } catch (IOException e) {
            throw new ResourceProcessingException(e);
        }
    }

    @Recover
    private void returnResourceToQueue(ResourceProcessingException e, Integer resourceId) {
        rabbitTemplate.convertAndSend(properties.getNewResourceUploadedQueueName(), resourceId);
    }

    public void saveSongMetadata(File file, Integer resourceId) {
        try {
            SongDto songDto = Mp3Utils.getSongMetadata(file);
            songDto.setResourceId(resourceId);

            var song = songServiceClient.createSong(songDto);
            if (song.getId() == null) {
                throw new ResourceProcessingException("empty song");
            }
        } catch (IOException | InvalidDataException | UnsupportedTagException e) {
            returnResourceToQueue(null, resourceId);
            throw new RuntimeException(e);
        }
    }
}
