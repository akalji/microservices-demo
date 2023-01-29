package com.akalji.learn.microservice.song.service.common.dto;

import com.akalji.learn.microservice.song.service.common.domain.Song;
import com.akalji.learn.microservices.commons.domain.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Nikolai_Tikhonov
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongDto implements Dto {
    private Integer id;
    private String name;
    private String artist;
    private String album;
    private String length;
    private Integer resourceId;
    private Integer year;

    public static SongDto from(Song song) {
        return SongDto.builder()
                .id(song.getId())
                .name(song.getName())
                .artist(song.getArtist())
                .album(song.getAlbum())
                .length(song.getLength())
                .resourceId(song.getId())
                .year(song.getYear())
                .build();
    }

    public Song toSong() {
        return Song.builder()
                .id(resourceId)
                .name(name)
                .artist(artist)
                .album(album)
                .length(length)
                .year(year)
                .build();
    }
}
