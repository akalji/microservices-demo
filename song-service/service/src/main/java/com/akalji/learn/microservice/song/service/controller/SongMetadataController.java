package com.akalji.learn.microservice.song.service.controller;

import com.akalji.learn.microservice.song.service.common.dto.SongDto;
import com.akalji.learn.microservice.song.service.service.SongMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Nikolai_Tikhonov
 */
@RestController
@RequestMapping("/songs")
public class SongMetadataController {

    @Autowired
    private SongMetadataService songMetadataService;

    @GetMapping("/{id}")
    public SongDto getSong(@PathVariable("id") Integer id) {
        var song = songMetadataService.getSong(id);
        return SongDto.from(song);
    }

    @PostMapping("/")
    public SongDto postSong(@RequestBody SongDto songDto) {
        var song = songDto.toSong();
        song = songMetadataService.saveSong(song);
        return SongDto.from(song);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSongs(@RequestParam("ids") Collection<Integer> ids) {
        songMetadataService.deleteSongsByIds(new HashSet<>(ids));
    }
}
