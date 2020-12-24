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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

/**
 * @author Chandra
 */
@Slf4j
@DataMongoTest
@DirtiesContext
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
        StepVerifier.create(itemReactiveRepository.findById("ABC"))
                .expectSubscription()
                //.expectNextCount(1)
                .expectNextMatches(item -> item.getDescription().equals("Bose Headphones"))
                .verifyComplete();
    }

    @Test
    public void findItemByDescrition() {

        StepVerifier.create(itemReactiveRepository.findByDescription("Bose Headphones").log("find Item Description:"))
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();

    }

    @Test
    public void saveItem() {
        Item item = new Item(null, "Google Home mini", 30.00);

        StepVerifier.create(itemReactiveRepository.save(item).log())
                .expectSubscription()
                .expectNextMatches(item1 -> item.getDescription().equals(item1.getDescription()) && item1.getId() != null)
                .verifyComplete();
    }

    @Test
    public void updateItem() {
        double newPrice = 520.00;
        Mono<Item> updateItem = itemReactiveRepository.findByDescription("LG TV")
                .map(item -> {
                    item.setPrice(newPrice);
                    return item;
                })
                .flatMap(itemReactiveRepository::save);
//
//          .flatMap((item) -> {
//            return itemReactiveRepository.save(item); //saving the item with the new price
//        });

        StepVerifier.create(updateItem)
                .expectSubscription()
                .expectNextMatches(item -> item.getPrice() == 520.00)
                .verifyComplete();
    }

    @Test
    public void deleteItemById() {
        Mono<Void> deletedItem = itemReactiveRepository.findById("ABC")
                .map(Item::getId)
                .flatMap(id -> itemReactiveRepository.deleteById(id));

        StepVerifier.create(deletedItem.log())
                .expectSubscription()
                .verifyComplete();

        StepVerifier.create(itemReactiveRepository.findAll())
                .expectSubscription()
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    public void deleteItemById2() {
        Mono<Void> deletedItem = itemReactiveRepository.deleteById("ABC");

        StepVerifier.create(deletedItem.log())
                .expectSubscription()
                .verifyComplete();

        StepVerifier.create(itemReactiveRepository.findAll())
                .expectSubscription()
                .expectNextCount(4)
                .verifyComplete();
    }
    @Test
    public void deleteItem() {
        Mono<Void> deletedItem = itemReactiveRepository.findByDescription("LG TV") // Mono<Item>
                .flatMap((item) -> {
                    return itemReactiveRepository.delete(item);
                });

        StepVerifier.create(deletedItem.log())
                .expectSubscription()
                .verifyComplete();

        StepVerifier.create(itemReactiveRepository.findAll().log("The new Item List : "))
                .expectSubscription()
                .expectNextCount(4)
                .verifyComplete();

    }



}