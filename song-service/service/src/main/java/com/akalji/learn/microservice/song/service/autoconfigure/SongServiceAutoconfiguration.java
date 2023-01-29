package com.akalji.learn.microservice.song.service.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nikolai_Tikhonov
 */
@Configuration
@EnableConfigurationProperties(SongServiceProperties.class)
public class SongServiceAutoconfiguration {

}
