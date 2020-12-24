package com.learnreactspring.handler;

import com.learnreactspring.document.Item;
import com.learnreactspring.repository.ItemReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

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
       // webTestClient

    }

}