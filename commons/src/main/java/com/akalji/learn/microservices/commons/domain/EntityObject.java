package com.akalji.learn.microservices.commons.domain;

/**
 * @author Nikolai_Tikhonov
 */
public interface EntityObject<ID> {
    ID getId();
    void setId(ID id);
}
