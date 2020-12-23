package com.learnreactspring.repository;

import com.learnreactspring.document.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

/**
 * @author Chandra
 */
@Slf4j
@DataMongoTest
//@DirtiesContext
class ItemReactiveRepositoryTest {

    private final ItemReactiveRepository itemReactiveRepository;
    List<Item> itemList = Arrays.asList(new Item(null, "Samsung TV", 400.0),
            new Item(null, "LG TV", 420.0),
            new Item(null, "Apple Watch", 299.99),
            new Item(null, "Beats Headphones", 149.99),
            new Item("ABC", "Bose Headphones", 149.99));

    @Autowired
    ItemReactiveRepositoryTest(ItemReactiveRepository itemReactiveRepository) {
        this.itemReactiveRepository = itemReactiveRepository;
    }

    @BeforeEach
    void setUp() {
    //    log.info("Setup is called=======>");
        itemReactiveRepository.deleteAll()
                .thenMany(Flux.fromIterable(itemList))
                .flatMap(itemReactiveRepository::save)
                .doOnNext(item -> System.out.println("Insert Item is:" + item))
                .blockLast();


    }

    @Test
    public void getAllItems() {
       // log.info("getAllItems is called=======>");
        StepVerifier.create(itemReactiveRepository.findAll())
                .expectSubscription()
                .expectNextCount(5)
                .verifyComplete();
    }
    @Test
    public void getItemByID() {
        Step
    }
}