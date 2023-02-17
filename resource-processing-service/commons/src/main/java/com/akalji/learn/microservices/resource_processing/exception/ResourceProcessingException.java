package com.akalji.learn.microservices.resource_processing.exception;

/**
 * @author Nikolai_Tikhonov
 */
public class ResourceProcessingException extends RuntimeException {

    public ResourceProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceProcessingException(String message) {
        super(message);
    }

    public ResourceProcessingException(Throwable cause) {
        super(cause);
    }
}
