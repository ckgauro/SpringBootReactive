package com.learnreactspring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Chandra
 */
@SpringBootTest
@AutoConfigureWebTestClient
@DirtiesContext
class FluxAndMonoControllerTest {
  //  @Autowired
  //  WebTestClient webTestClient;
    private final WebTestClient webTestClient;

    @Autowired
    FluxAndMonoControllerTest(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }


    @Test
    public void flux_approach1(){
        Flux<Integer> integerFlux=webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(integerFlux)
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .verifyComplete();

    }

}