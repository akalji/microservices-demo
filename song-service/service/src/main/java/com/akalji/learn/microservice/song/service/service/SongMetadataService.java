package com.akalji.learn.microservice.song.service.service;

import com.akalji.learn.microservice.song.service.common.domain.Song;

import java.util.HashSet;

/**
 * @author Nikolai_Tikhonov
 */
public interface SongMetadataService {
    Song getSong(Integer songId);

    void deleteSongsByIds(HashSet<Integer> ids);

    Song saveSong(Song song);
}
