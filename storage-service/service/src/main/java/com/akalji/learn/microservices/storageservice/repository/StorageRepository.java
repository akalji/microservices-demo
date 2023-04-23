package com.akalji.learn.microservices.storageservice.repository;

import com.akalji.learn.microservices.storageservice.domain.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nikolai_Tikhonov
 */
@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {
}
