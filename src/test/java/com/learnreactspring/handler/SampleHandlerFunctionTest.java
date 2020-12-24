package com.learnreactspring.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
/**
 * @author Chandra
 */
@SpringBootTest
@AutoConfigureWebTestClient
@DirtiesContext
class SampleHandlerFunctionTest {

    private final WebTestClient webTestClient;

    @Autowired
    SampleHandlerFunctionTest(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }


    @Test
    public void flux_approach1() {
        Flux<Integer> integerFlux =
                webTestClient.get().uri("/functional/flux")
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
                .expectNext(5)
                .verifyComplete();

    }

    @Test
    public void flux_approach2() {
        Flux<Integer> integerFlux =
                webTestClient.get().uri("/functional/flux")
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
                .thenCancel()
                .verify();

    }
    @Test
    public void mono(){
        Integer expectedValue = new Integer(1);
        webTestClient.get().uri("/functional/mono")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith(response-> Assertions.assertEquals(expectedValue, response.getResponseBody()));

    }



}