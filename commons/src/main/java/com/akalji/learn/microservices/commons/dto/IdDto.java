package com.akalji.learn.microservices.commons.dto;

import java.io.Serializable;

/**
 * @author Nikolai_Tikhonov
 */
public class IdDto implements Serializable {
    private final Integer id;

    public IdDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
