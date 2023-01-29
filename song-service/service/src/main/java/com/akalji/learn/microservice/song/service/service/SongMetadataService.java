package com.akalji.learn.microservice.song.service.service;

import com.akalji.learn.microservice.song.service.common.domain.Song;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikolai_Tikhonov
 */
public interface SongMetadataService {
    Song getSong(Integer songId);

    void deleteSongsByIds(HashSet<Integer> ids);

    Song saveSong(Song song);
}
