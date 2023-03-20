package com.akalji.learn.microservices.resourceservice.contract;

import com.akalji.learn.microservices.resourceservice.controller.ResourceController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author Nikolai_Tikhonov
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseContractTestClass {
    @Autowired
    private ResourceController resourceController;

    @BeforeAll
    public void setup() {
        var standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(resourceController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }
}
