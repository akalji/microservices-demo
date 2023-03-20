package com.akalji.learn.microservice.song.service.controller;

import com.akalji.learn.microservice.song.service.domain.Song;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.Optional;

/**
 * @author Nikolai_Tikhonov
 */
public class StepDefs extends SongMetadataControllerScenarioTest {
    /**
     * Mock the repository method to avoid using DB
     */
    @Before
    public void init() {
        savedSongs = new HashedMap<>();
        Mockito.when(songMetadataRepository.findById(Mockito.any()))
                .thenAnswer(invocation -> Optional.of(savedSongs.get(invocation.getArgument(0))));
        Mockito.when(songMetadataRepository.save(Mockito.any()))
                .thenAnswer(invocation -> {
                    var toSave = (Song) invocation.getArgument(0);
                    toSave.setId(id++);
                    savedSongs.put(toSave.getId(), toSave);
                    return toSave;
                });
    }

    @When("^user creates new song$")
    public void user_creates_new_song() throws Throwable{
        save_new_song();
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        Assertions.assertEquals(statusCode, lastResponseCode);
    }
}
