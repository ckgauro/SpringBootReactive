package com.learnreactspring.handler;

import com.learnreactspring.document.Item;
import com.learnreactspring.repository.ItemReactiveRepository;
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

import java.util.Arrays;
import java.util.List;

import static constants.ItemConstants.ITEM_FUNCTIONAL_END_POINT_V1;

/**
 * @author Chandra
 */

@AutoConfigureWebTestClient
@DirtiesContext
@SpringBootTest
@ActiveProfiles("test")
class ItemsHandlerTest {

    private final WebTestClient webTestClient;
    private final ItemReactiveRepository itemReactiveRepository;

    public List<Item> data() {

        return Arrays.asList(new Item(null, "Samsung TV", 399.99),
                new Item(null, "LG TV", 329.99),
                new Item(null, "Apple Watch", 349.99),
                new Item("ABC", "Beats HeadPhones", 149.99));
    }

    @Autowired
    ItemsHandlerTest(WebTestClient webTestClient, ItemReactiveRepository itemReactiveRepository) {
        this.webTestClient = webTestClient;
        this.itemReactiveRepository = itemReactiveRepository;
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
        webTestClient.get().uri(ITEM_FUNCTIONAL_END_POINT_V1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Item.class)
                .hasSize(4);

    }

    @Test
    public void getOneItem() {
        webTestClient.get().uri(ITEM_FUNCTIONAL_END_POINT_V1 + "/ABC")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.price").isEqualTo(149.99);

    }

    @Test
    public void getOneItem_notFound() {
        webTestClient.get().uri(ITEM_FUNCTIONAL_END_POINT_V1 + "/DEF")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void runTimeException() {
        webTestClient.get().uri("/fun/runtimeexception")
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody()
                .jsonPath("$.message", "RuntimeException Occurred");
    }

    @Test
    public void testCreateItem() {

        Item item = new Item(null, "Iphone X", 999.99);

        webTestClient.post().uri(ITEM_FUNCTIONAL_END_POINT_V1)
                .body(Mono.just(item), Item.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.description").isEqualTo("Iphone X")
                .jsonPath("$.price").isEqualTo("999.99")
        ;

    }
    @Test
    public void testDeleteOneItem() {

        webTestClient.delete().uri(ITEM_FUNCTIONAL_END_POINT_V1.concat("/{id}"), "ABC")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);

    }
    @Test
    public void testUpdateItem() {
        double newPrice=129.99;
        Item item = new Item(null,"Beats HeadPhones", newPrice);
        webTestClient.put().uri(ITEM_FUNCTIONAL_END_POINT_V1.concat("/{id}"), "ABC")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), Item.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.price",newPrice);

    }

    @Test
    public void testUpdateItem_notFound() {
        double newPrice=129.99;
        Item item = new Item(null,"Beats HeadPhones", newPrice);
       // webTestClient.put().uri(ITEM_FUNCTIONAL_END_POINT_V1.concat("/{id}"), "DEF") //no record with this ids
        webTestClient.put().uri(ITEM_FUNCTIONAL_END_POINT_V1+"/CK")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), Item.class)
                .exchange()
                .expectStatus().isNotFound();
    }



}