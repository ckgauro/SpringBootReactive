package com.learnreactspring.repository;

import com.learnreactspring.document.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * @author Chandra
 */
@Component
@Slf4j
@Profile("!test")
public class ItemDataInitializer implements CommandLineRunner {

    private final ItemReactiveRepository itemReactiveRepository;
    private final ReactiveMongoRepository reactiveMongoRepository;

    @Autowired
    public ItemDataInitializer(ItemReactiveRepository itemReactiveRepository, ReactiveMongoRepository reactiveMongoRepository) {
        this.itemReactiveRepository = itemReactiveRepository;
        this.reactiveMongoRepository = reactiveMongoRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        initalDataSetUp();
    }
    public List<Item> data() {

        return Arrays.asList(new Item(null, "Samsung TV", 399.99),
                new Item(null, "LG TV", 329.99),
                new Item(null, "Apple Watch", 349.99),
                new Item("ABC", "Beats HeadPhones", 149.99));
    }
    private void initalDataSetUp() {
        itemReactiveRepository.deleteAll()
                .thenMany(Flux.fromIterable(data()))
                .flatMap(itemReactiveRepository::save)
                .thenMany(itemReactiveRepository.findAll())
                .subscribe(item->{
                    log.info("Item inserted from CommandLineRunner:"+item);
                });

    }

}
