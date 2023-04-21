package com.akalji.learn.microservice.song.service.common.client;

import com.akalji.learn.microservice.song.service.common.dto.SongDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static com.akalji.learn.microservice.song.service.common.Endpoints.SAVE_SONG;

/**
 * @author Nikolai_Tikhonov
 */
public class SongServiceClientImpl implements SongServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public SongDto createSong(SongDto songDto) {
        var songDtoResponseEntity = restTemplate.postForEntity("http://song-service" + SAVE_SONG, songDto, SongDto.class);
        return songDtoResponseEntity.getBody();
    }

    @Override
    public SongDto getSong(Integer id) {
        return null;
    }
}
