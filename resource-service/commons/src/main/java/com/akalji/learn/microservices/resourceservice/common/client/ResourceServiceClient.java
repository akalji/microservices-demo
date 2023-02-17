package com.akalji.learn.microservices.resourceservice.common.client;

/**
 * @author Nikolai_Tikhonov
 */
public interface ResourceServiceClient {
    byte[] getResourceById(Integer id);
}
