package com.akalji.learn.microservice.song.service.common.client;

import com.akalji.learn.microservice.song.service.common.dto.SongDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Collection;

import static com.akalji.learn.microservice.song.service.common.Endpoints.DELETE_SONGS;
import static com.akalji.learn.microservice.song.service.common.Endpoints.GET_SONG;
import static com.akalji.learn.microservice.song.service.common.Endpoints.SAVE_SONG;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * @author Nikolai_Tikhonov
 */
@Service
public class SongServiceClientImpl implements SongServiceClient {

    public Flux<SongDto> getSongWithRetry(Integer id) {

        return WebClient.create()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(GET_SONG)
                        .build(id))
                .retrieve()
                .bodyToFlux(SongDto.class)
                .retryWhen(Retry
                        .fixedDelay(3, Duration.ofMillis(100))
                );
    }

    public Flux<SongDto> createSongWithRetry() {
        return WebClient.create()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(SAVE_SONG)
                        .build())
                .retrieve()
                .bodyToFlux(SongDto.class)
                .retryWhen(Retry
                        .fixedDelay(3, Duration.ofMillis(100))
                );
    }

//    public Flux<SongDto> deleteSongWithRetry(Collection<Integer> ids) {
//        return WebClient.create()
//            .get()
//            .uri(uriBuilder -> uriBuilder
//                    .path(DELETE_SONGS)
//                    .queryParam("ids", ids)
//                    .build())
//            .exchangeToMono(clientResponse -> {
//                        var status = clientResponse.statusCode();
//                        if(status.equals(NO_CONTENT)){
//                            Mono.just(new SongDto());
//                        }
//
//            }
//            )
//            .retryWhen(Retry
//                    .fixedDelay(3, Duration.ofMillis(100))
//            );
//    }



}
