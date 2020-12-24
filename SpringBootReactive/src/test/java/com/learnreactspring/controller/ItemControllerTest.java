package com.learnreactspring.controller;

import com.learnreactspring.document.Item;
import com.learnreactspring.repository.ItemReactiveRepository;
import constants.ItemConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static constants.ItemConstants.ITEM_END_POINT_V1;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Chandra
 */
@AutoConfigureWebTestClient
@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
@Slf4j
class ItemControllerTest {
    private final WebTestClient webTestClient;
    private final ItemReactiveRepository itemReactiveRepository;

    @Autowired
    ItemControllerTest(WebTestClient webTestClient, ItemReactiveRepository itemReactiveRepository) {
        this.webTestClient = webTestClient;
        this.itemReactiveRepository = itemReactiveRepository;
    }

    public List<Item> data() {

        return Arrays.asList(new Item(null, "Samsung TV", 399.99),
                new Item(null, "LG TV", 329.99),
                new Item(null, "Apple Watch", 349.99),
                new Item("ABC", "Beats HeadPhones", 149.99));
    }

    @BeforeEach
    void setUp() {
        itemReactiveRepository.deleteAll()
                .thenMany(Flux.fromIterable(data()))
                .flatMap(itemReactiveRepository::save)
                .doOnNext(item -> System.out.println("Insert item is:" + item))
                .blockLast();
    }

    @Test
    public void getAllItems() {
        webTestClient.get().uri(ITEM_END_POINT_V1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Item.class)
                .hasSize(4);

    }

    @Test
    public void getAllItems_approach2() {

        webTestClient.get().uri(ITEM_END_POINT_V1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Item.class)
                .hasSize(4)
                .consumeWith(response -> {
                    List<Item> items = response.getResponseBody();
                    items.forEach(item -> {
                        System.out.println("Item===>" + item.toString());
                        assertTrue(item.getId() != null);
                    });
                });

    }

    @Test
    public void getAllItems_approach3() {
        Flux<Item> itemFlux = webTestClient.get().uri(ITEM_END_POINT_V1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                // .expectBody(Item.class)
                .returnResult(Item.class)
                .getResponseBody();
        StepVerifier.create(itemFlux.log("value from network: "))
                .expectNextCount(4)
                .verifyComplete();

    }

    @Test
    public void getOneItem() {
        webTestClient.get().uri(ITEM_END_POINT_V1 + "/ABC")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.price", 149.99);
    }

    @Test
    public void getOneItem_notFound() {
        webTestClient.get().uri(ITEM_END_POINT_V1 + "/DEF")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void createItem() {
        Item item = new Item(null, "Iphone X", 999.99);

        webTestClient.post().uri(ITEM_END_POINT_V1)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), Item.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.description").isEqualTo("Iphone X")
                .jsonPath("$.price").isEqualTo(999.99);

    }

    @Test
    public void deleteItem() {

        webTestClient.delete().uri(ITEM_END_POINT_V1 + "/ABC")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }

    @Test
    public void updateItem() {
        double newPrice = 129.33;
        Item item = new Item(null, "Beats HeadPhone", newPrice);

        webTestClient.put().uri(ITEM_END_POINT_V1 + "/ABC")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), Item.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.price").isEqualTo(newPrice);
    }
    @Test
    public void updateItem_notFound(){
        double newPrice = 129.33;
        Item item = new Item(null, "Beats HeadPhone", newPrice);

        webTestClient.put().uri(ITEM_END_POINT_V1+"/DEF")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(item),Item.class)
                .exchange()
                .expectStatus().isNotFound();

    }
    @Test
    public void runTimeException(){
        webTestClient.get().uri(ITEM_END_POINT_V1+"/runtimeException")
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(String.class)
               // .isEqualTo("RuntimeException Occurred.");
        ;
    }


}