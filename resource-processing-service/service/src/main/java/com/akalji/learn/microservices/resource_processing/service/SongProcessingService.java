package com.akalji.learn.microservices.resource_processing.service;

import org.springframework.stereotype.Service;

/**
 * @author Nikolai_Tikhonov
 */
@Service
public interface SongProcessingService {
    void saveSongMetadata(Integer songId);
}
