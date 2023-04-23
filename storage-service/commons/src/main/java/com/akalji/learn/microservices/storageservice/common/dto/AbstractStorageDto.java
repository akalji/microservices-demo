package com.akalji.learn.microservices.storageservice.common.dto;

import com.akalji.learn.microservices.storageservice.common.domain.StorageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Nikolai_Tikhonov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractStorageDto {
    private StorageType storageType;
    private String bucket;
    private String path;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractStorageDto that = (AbstractStorageDto) o;
        return storageType == that.storageType && Objects.equals(bucket, that.bucket) && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storageType, bucket, path);
    }

    @Override
    public String toString() {
        return "AbstractStorageDto{" +
                "storageType=" + storageType +
                ", bucket='" + bucket + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
