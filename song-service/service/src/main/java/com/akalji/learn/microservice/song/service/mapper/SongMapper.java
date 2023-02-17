package com.akalji.learn.microservice.song.service.mapper;

import com.akalji.learn.microservice.song.service.common.dto.SongDto;
import com.akalji.learn.microservice.song.service.domain.Song;

/**
 * @author Nikolai_Tikhonov
 */
public final class SongMapper {

    public static SongDto toDto(Song song) {
        return SongDto.builder()
                .id(song.getId())
                .name(song.getName())
                .artist(song.getArtist())
                .album(song.getAlbum())
                .length(song.getLength())
                .resourceId(song.getResourceId())
                .year(song.getYear())
                .build();
    }

    public static Song toEntity(SongDto dto) {
        return Song.builder()
                .id(dto.getId())
                .name(dto.getName())
                .artist(dto.getArtist())
                .album(dto.getAlbum())
                .length(dto.getLength())
                .resourceId(dto.getResourceId())
                .year(dto.getYear())
                .build();
    }
    private SongMapper(){}
}
