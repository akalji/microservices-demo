package com.akalji.learn.microservice.song.service.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Nikolai_Tikhonov
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "com.akalji.learn.microservices.song-service")
public class SongServiceProperties {
}
