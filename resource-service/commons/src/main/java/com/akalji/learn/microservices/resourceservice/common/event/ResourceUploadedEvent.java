package com.akalji.learn.microservices.resourceservice.common.event;

import com.akalji.learn.microservices.commons.event.EntityEvent;
import com.akalji.learn.microservices.resourceservice.common.dto.ResourceDto;

/**
 * @author Nikolai_Tikhonov
 */
public class ResourceUploadedEvent extends EntityEvent<Integer, ResourceDto> {
    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public ResourceDto getContent() {
        return null;
    }
}
