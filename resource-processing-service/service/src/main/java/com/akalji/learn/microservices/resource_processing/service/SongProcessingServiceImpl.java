package com.akalji.learn.microservices.resource_processing.service;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * @author Nikolai_Tikhonov
 */
public class SongProcessingServiceImpl implements SongProcessingService {
    public void saveSongMetadata(Integer songId) {
        var tempDirectory = FileUtils.getTempDirectory();

    }
}
