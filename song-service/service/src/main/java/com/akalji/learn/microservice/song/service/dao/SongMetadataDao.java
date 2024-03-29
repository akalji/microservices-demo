package com.akalji.learn.microservice.song.service.dao;

import com.akalji.learn.microservice.song.service.domain.Song;

import java.util.Set;

/**
 * @author Nikolai_Tikhonov
 */
public interface SongMetadataDao {
    Song getById(Integer songId);

    Song save(Song song);

    void delete(Song song);

    void deleteAllByIds(Set<Integer> ids);
}
