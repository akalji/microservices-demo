package com.akalji.learn.microservices.resource_processing.util;

import com.akalji.learn.microservice.song.service.common.dto.SongDto;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

/**
 * @author Nikolai_Tikhonov
 */
public class Mp3Utils {
    public static SongDto getSongMetadata(File file) throws InvalidDataException, UnsupportedTagException, IOException {
        var mp3File = new Mp3File(file);
        var songDto = new SongDto();

        String name = null;
        String artist = null;
        String album = null;
        String length = formatSongLength(mp3File.getLengthInMilliseconds());

        Integer year = null;

        if (mp3File.hasId3v2Tag()) {
            var id3v2Tag = mp3File.getId3v2Tag();
            if (id3v2Tag.getTitle() != null) {
                name = id3v2Tag.getTitle();
            }
            if (id3v2Tag.getArtist() != null) {
                artist = id3v2Tag.getArtist();
            }
            if (id3v2Tag.getAlbum() != null) {
                album = id3v2Tag.getAlbum();
            }
            if (id3v2Tag.getYear() != null && !id3v2Tag.getYear().isBlank()) {
                year = Integer.parseInt(id3v2Tag.getYear());
            }
        } else if (mp3File.hasId3v1Tag()) {
            var id3v1Tag = mp3File.getId3v1Tag();
            if (id3v1Tag.getTitle() != null) {
                name = id3v1Tag.getTrack();
            }
            if (id3v1Tag.getArtist() != null) {
                artist = id3v1Tag.getArtist();
            }
            if (id3v1Tag.getAlbum() != null) {
                album = id3v1Tag.getAlbum();
            }
            if (id3v1Tag.getYear() != null && !id3v1Tag.getYear().isBlank()) {
                year = Integer.parseInt(id3v1Tag.getYear());
            }
        }

        songDto.setName(name);
        songDto.setArtist(artist);
        songDto.setAlbum(album);
        songDto.setLength(length);
        songDto.setYear(year);
        return songDto;
    }

    private static String formatSongLength(Long millis) {
        var duration = Duration.ofMillis(millis);
        return String.format("%d:%02d", duration.toMinutes(), duration.toSecondsPart());
    }

    private Mp3Utils() {

    }
}
