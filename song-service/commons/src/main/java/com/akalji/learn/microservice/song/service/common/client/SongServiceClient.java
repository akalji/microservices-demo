package com.akalji.learn.microservice.song.service.common.client;

import com.akalji.learn.microservice.song.service.common.dto.SongDto;

/**
 * @author Nikolai_Tikhonov
 */
public interface SongServiceClient {
    SongDto createSong(SongDto songDto);
    SongDto getSong(Integer id);

}
