package com.akalji.learn.microservices.storageservice.domain;

import com.akalji.learn.microservices.commons.domain.EntityObject;
import com.akalji.learn.microservices.storageservice.common.domain.StorageType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Nikolai_Tikhonov
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "storages")
public class Storage implements EntityObject<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    @Column(name = "bucket")
    private String bucket;

    @Column(name = "path")
    private String path;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

}
