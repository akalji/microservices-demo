package com.akalji.learn.microservice.song.service.controller;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Nikolai_Tikhonov
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "/Users/Nikolai_Tikhonov/Projects/Learn/microservices/microservices/song-service/service/src/it/resources/features")
public class CucumberComponentTest {

}
