package com.akalji.learn.microservice.song.service.dao;

import com.akalji.learn.microservice.song.service.domain.Song;
import com.akalji.learn.microservice.song.service.repository.SongMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Nikolai_Tikhonov
 */
@Service
public class SongMetadataDaoSpringDataImpl implements SongMetadataDao {

    @Autowired
    private SongMetadataRepository songMetadataRepository;

    @Override
    public Song getById(Integer songId) {
        return songMetadataRepository.findById(songId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, " The song metadata with the specified id does not exist"));
    }

    @Override
    public Song save(Song song) {
        return songMetadataRepository.save(song);
    }

    @Override
    public void delete(Song song) {
        songMetadataRepository.delete(song);
    }
    @Override
    public void deleteAllByIds(Set<Integer> ids) {
        songMetadataRepository.deleteAllById(ids);
    }
}
