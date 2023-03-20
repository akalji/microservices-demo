package com.akalji.learn.microservice.song.service.controller;

import com.akalji.learn.microservice.song.service.dao.SongMetadataDao;
import com.akalji.learn.microservice.song.service.dao.SongMetadataDaoSpringDataImpl;
import com.akalji.learn.microservice.song.service.domain.Song;
import com.akalji.learn.microservice.song.service.repository.SongMetadataRepository;
import com.akalji.learn.microservice.song.service.service.SongMetadataService;
import com.akalji.learn.microservice.song.service.service.SongMetadataServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Nikolai_Tikhonov
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SongMetadataController.class)
public class SongMetadataControllerTest {
    @Autowired
    private MockMvc mvc;

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
    }

    @MockBean
    private SongMetadataRepository songMetadataRepository;

    @Test
    public void saved_song_got_correctly() throws Exception {
        mvc.perform(get("/songs/42")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("Test title")))
                .andExpect(jsonPath("artist", is("Test artist name")))
                .andExpect(jsonPath("album", is("test album")))
                .andExpect(jsonPath("length", is("0:01")))
                .andExpect(jsonPath("resourceId", is(40)))
                .andExpect(jsonPath("year", is(1998)));

    }

    /**
     * Mock the repository method to avoid using DB
     */
    @Before
    public void init() {
        var song = new Song();
        song.setId(42);
        song.setName("Test title");
        song.setArtist("Test artist name");
        song.setAlbum("test album");
        song.setLength("0:01");
        song.setResourceId(40);
        song.setYear(1998);

        when(songMetadataRepository.findById(42)).thenReturn(Optional.of(song));
    }
}
