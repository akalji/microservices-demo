package com.akalji.learn.microservices.commons.event;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Nikolai_Tikhonov
 */
@JsonTypeInfo(property = "@class", use = JsonTypeInfo.Id.CLASS)
public interface Event<ID, C> {
    ID getId();
    C getContent();

}
