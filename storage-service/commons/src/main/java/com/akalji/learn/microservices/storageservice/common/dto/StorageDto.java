package com.akalji.learn.microservices.storageservice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Nikolai_Tikhonov
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageDto extends AbstractStorageDto {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StorageDto that = (StorageDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "StorageDto{" +
                "id=" + id +
                "} " + super.toString();
    }
}
