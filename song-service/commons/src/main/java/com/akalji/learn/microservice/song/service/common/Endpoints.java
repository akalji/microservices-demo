package com.akalji.learn.microservice.song.service.common;

/**
 * @author Nikolai_Tikhonov
 */
public final class Endpoints {
    private static final String SONG_SERVICE_PREFIX = "/songs";
    public static final String GET_SONG = SONG_SERVICE_PREFIX + "/{id}";

    public static final String SAVE_SONG = SONG_SERVICE_PREFIX + "/";
    public static final String DELETE_SONGS = SONG_SERVICE_PREFIX + "/";


    private Endpoints() {}
}
