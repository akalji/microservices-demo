package com.akalji.learn.microservices.resource_processing.service;

import java.io.File;

/**
 * @author Nikolai_Tikhonov
 */
public interface SongProcessingService {
    void retrieveAndSaveSongMetadata(Integer resourceId);

    void saveSongMetadata(File file, Integer resourceId);

}
