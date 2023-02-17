package com.akalji.learn.microservices.resource_processing.service;

import com.akalji.learn.microservice.song.service.common.client.SongServiceClient;
import com.akalji.learn.microservice.song.service.common.dto.SongDto;
import com.akalji.learn.microservices.resource_processing.autoconfigure.ProcessingServiceProperties;
import com.akalji.learn.microservices.resource_processing.exception.ResourceProcessingException;
import com.akalji.learn.microservices.resourceservice.common.client.ResourceServiceClient;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
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
import java.time.Duration;
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
            var mp3File = new Mp3File(file);
            var songDto = new SongDto();

            String name = null;
            String artist = null;
            String album = null;
            String length = formatSongLength(mp3File.getLengthInMilliseconds());

            Integer year = null;

            if (mp3File.hasId3v2Tag()) {
                var id3v2Tag = mp3File.getId3v2Tag();
                if (id3v2Tag.getTitle() != null) {
                    name = id3v2Tag.getTitle();
                }
                if (id3v2Tag.getArtist() != null) {
                    artist = id3v2Tag.getArtist();
                }
                if (id3v2Tag.getAlbum() != null) {
                    album = id3v2Tag.getAlbum();
                }
                if (id3v2Tag.getYear() != null && !id3v2Tag.getYear().isBlank()) {
                    year = Integer.parseInt(id3v2Tag.getYear());
                }
            } else if (mp3File.hasId3v1Tag()) {
                var id3v1Tag = mp3File.getId3v1Tag();
                if (id3v1Tag.getTitle() != null) {
                    name = id3v1Tag.getTrack();
                }
                if (id3v1Tag.getArtist() != null) {
                    artist = id3v1Tag.getArtist();
                }
                if (id3v1Tag.getAlbum() != null) {
                    album = id3v1Tag.getAlbum();
                }
                if (id3v1Tag.getYear() != null && !id3v1Tag.getYear().isBlank()) {
                    year = Integer.parseInt(id3v1Tag.getYear());
                }
            }

            songDto.setName(name);
            songDto.setArtist(artist);
            songDto.setAlbum(album);
            songDto.setLength(length);
            songDto.setResourceId(resourceId);
            songDto.setYear(year);

            var song = songServiceClient.createSong(songDto);
            if (song.getId() == null) {
                throw new ResourceProcessingException("empty song");
            }
        } catch (IOException | InvalidDataException | UnsupportedTagException e) {
            returnResourceToQueue(null, resourceId);
            throw new RuntimeException(e);
        }
    }

    private String formatSongLength(Long millis) {
        var duration = Duration.ofMillis(millis);
        return String.format("%d:%02d", duration.toMinutes(), duration.toSecondsPart());
    }
}
