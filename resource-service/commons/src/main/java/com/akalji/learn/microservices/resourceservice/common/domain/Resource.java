package com.akalji.learn.microservices.resourceservice.common.domain;

import com.akalji.learn.microservices.commons.domain.EntityObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

/**
 * @author Nikolai_Tikhonov
 */
@Entity
@Getter
@Setter
@Table(name = "resources")
public class Resource implements EntityObject<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uri")
    private String uri;

    @Transient
    private InputStream content;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

}
