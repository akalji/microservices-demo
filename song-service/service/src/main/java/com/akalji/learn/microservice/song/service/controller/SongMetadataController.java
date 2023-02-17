package com.akalji.learn.microservice.song.service.controller;

import com.akalji.learn.microservice.song.service.common.dto.SongDto;
import com.akalji.learn.microservice.song.service.mapper.SongMapper;
import com.akalji.learn.microservice.song.service.service.SongMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;

import static com.akalji.learn.microservice.song.service.common.Endpoints.DELETE_SONGS;
import static com.akalji.learn.microservice.song.service.common.Endpoints.GET_SONG;
import static com.akalji.learn.microservice.song.service.common.Endpoints.SAVE_SONG;

/**
 * @author Nikolai_Tikhonov
 */
@RestController
public class SongMetadataController {

    @Autowired
    private SongMetadataService songMetadataService;

    @GetMapping(GET_SONG)
    public SongDto getSong(@PathVariable("id") Integer id) {
        var song = songMetadataService.getSong(id);
        return SongMapper.toDto(song);
    }

    @PostMapping(SAVE_SONG)
    public SongDto postSong(@RequestBody SongDto songDto) {
        var song = SongMapper.toEntity(songDto);
        song = songMetadataService.saveSong(song);
        return SongMapper.toDto(song);
    }

    @DeleteMapping(DELETE_SONGS)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSongs(@RequestParam("ids") Collection<Integer> ids) {
        songMetadataService.deleteSongsByIds(new HashSet<>(ids));
    }
}
