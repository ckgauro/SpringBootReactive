package com.learnreactspring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Chandra
 */
class FluxAndMonoControllerTest {

    private final WebTestClient webTestClient;

    FluxAndMonoControllerTest(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }


    @Test
    public void flux_approach1(){
        Flux<Integer> integerFlux=webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
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