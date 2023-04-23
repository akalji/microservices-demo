package com.akalji.learn.microservices.storageservice.common;

/**
 * @author Nikolai_Tikhonov
 */
public final class Endpoints {
    public static final String STORAGE_SERVICE_PREFIX = "/storages";
    public static final String GET_STORAGE = STORAGE_SERVICE_PREFIX + "/{id}";
    public static final String GET_ALL_STORAGES = STORAGE_SERVICE_PREFIX;


    private Endpoints() {}
}
