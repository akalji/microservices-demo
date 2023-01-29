package com.akalji.learn.microservice.song.service.service;

import com.akalji.learn.microservice.song.service.common.domain.Song;
import com.akalji.learn.microservice.song.service.dao.SongMetadataDao;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @author Nikolai_Tikhonov
 */
@Service
public class SongMetadataServiceImpl implements SongMetadataService {

    @Autowired
    private SongMetadataDao songMetadataDao;

    @Override
    public Song getSong(Integer songId) {
        Validate.notNull(songId);
        return songMetadataDao.getById(songId);
    }

    @Override
    public void deleteSongsByIds(HashSet<Integer> ids) {
        Validate.notEmpty(ids);
        songMetadataDao.deleteAllByIds(ids);
    }

    @Override
    public Song saveSong(Song song) {
        Validate.notNull(song);
        return songMetadataDao.save(song);
    }
}
