package com.akalji.learn.microservice.song.service.controller;

import com.akalji.learn.microservice.song.service.dao.SongMetadataDao;
import com.akalji.learn.microservice.song.service.dao.SongMetadataDaoSpringDataImpl;
import com.akalji.learn.microservice.song.service.domain.Song;
import com.akalji.learn.microservice.song.service.repository.SongMetadataRepository;
import com.akalji.learn.microservice.song.service.service.SongMetadataService;
import com.akalji.learn.microservice.song.service.service.SongMetadataServiceImpl;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Nikolai_Tikhonov
 */
@WebMvcTest(SongMetadataController.class)
@CucumberContextConfiguration
public class SongMetadataControllerScenarioTest {
    @Autowired
    protected MockMvc mvc;

    protected Map<Integer, Song> savedSongs;
    protected int id = 0;

    @TestConfiguration
    static class SongMetadataControllerTestConfiguration {
        @Bean
        public SongMetadataService songMetadataService(){
            return new SongMetadataServiceImpl();
        }

        @Bean
        public SongMetadataDao songMetadataDao(){
            return new SongMetadataDaoSpringDataImpl();
        }

        @Bean
        public SongMetadataRepository songMetadataRepository(){
            return mock(SongMetadataRepository.class);
        }
    }


    @Autowired
    protected SongMetadataRepository songMetadataRepository;

    protected int lastResponseCode;

    public void save_new_song() throws Exception {
        MvcResult mvcResult = mvc.perform(post("/songs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"Test title\",\n" +
                                "    \"artist\":\"Test artist name\",\n" +
                                "    \"album\":\"test album\",\n" +
                                "    \"length\":\"0:01\",\n" +
                                "    \"resourceId\":40,\n" +
                                "    \"year\":1998\n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
        lastResponseCode = mvcResult.getResponse().getStatus();
    }

    public void get_new_song() throws Exception {
        mvc.perform(get("/songs/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("Test title")))
                .andExpect(jsonPath("artist", is("Test artist name")))
                .andExpect(jsonPath("album", is("test album")))
                .andExpect(jsonPath("length", is("0:01")))
                .andExpect(jsonPath("resourceId", is(40)))
                .andExpect(jsonPath("year", is(1998)));
    }


}
