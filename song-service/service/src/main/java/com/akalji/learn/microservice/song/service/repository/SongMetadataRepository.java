package com.akalji.learn.microservice.song.service.repository;

import com.akalji.learn.microservice.song.service.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nikolai_Tikhonov
 */
@Repository
public interface SongMetadataRepository extends JpaRepository<Song, Integer> {
}
