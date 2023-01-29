package com.akalji.learn.microservices.resourceservice.repository;

import com.akalji.learn.microservices.resourceservice.common.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nikolai_Tikhonov
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
