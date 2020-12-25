package com.learnreactspring.initialize;

import com.learnreactspring.document.Item;
import com.learnreactspring.document.ItemCapped;
import com.learnreactspring.repository.ItemReactiveCappedRepository;
import com.learnreactspring.repository.ItemReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
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

    private final ReactiveMongoOperations mongoOperations;

    @Autowired
    ItemReactiveCappedRepository itemReactiveCappedRepository;

    @Autowired
    public ItemDataInitializer(ItemReactiveRepository itemReactiveRepository,   ReactiveMongoOperations mongoOperations) {
        this.itemReactiveRepository = itemReactiveRepository;
       // this.reactiveMongoRepository = reactiveMongoRepository;
        this.mongoOperations = mongoOperations;
    }


    @Override
    public void run(String... args) throws Exception {
        initalDataSetUp();
        createCappedCollection();
        dataSetUpforCappedCollection();
    }
    public List<Item> data() {

        return Arrays.asList(new Item(null, "Samsung TV", 399.99),
                new Item(null, "LG TV", 329.99),
                new Item(null, "Apple Watch", 349.99),
                new Item("ABC", "Beats HeadPhones", 149.99));
    }
    private void createCappedCollection() {
        mongoOperations.dropCollection(ItemCapped.class)
                .then(mongoOperations.createCollection(ItemCapped.class, CollectionOptions.empty().maxDocuments(20).size(50000).capped()));

    }
    public void dataSetUpforCappedCollection(){

        Flux<ItemCapped> itemCappedFlux = Flux.interval(Duration.ofSeconds(1))
                .map(i -> new ItemCapped(null,"Random Item " + i, (100.00+i)));

        itemReactiveCappedRepository
                .insert(itemCappedFlux)
                .subscribe((itemCapped -> {
                    log.info("Inserted Item is " + itemCapped);
                }));

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
