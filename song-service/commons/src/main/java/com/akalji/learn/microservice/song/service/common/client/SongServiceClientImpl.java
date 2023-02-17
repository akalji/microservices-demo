package com.akalji.learn.microservice.song.service.common.client;

import com.akalji.learn.microservice.song.service.common.dto.SongDto;
import org.apache.commons.lang3.Validate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.akalji.learn.microservice.song.service.common.Endpoints.SAVE_SONG;

/**
 * @author Nikolai_Tikhonov
 */
public class SongServiceClientImpl implements SongServiceClient {

    private final RestTemplate restTemplate;

    public SongServiceClientImpl(RestTemplate template) {
        Validate.notNull(template);
        restTemplate = template;
    }

    @Override
    public SongDto createSong(SongDto songDto) {
        ResponseEntity<SongDto> songDtoResponseEntity = restTemplate.postForEntity(SAVE_SONG, songDto, SongDto.class);
        return songDtoResponseEntity.getBody();
    }

    @Override
    public SongDto getSong(Integer id) {
        return null;
    }
}
