package com.learnreactspring.repository;

import com.learnreactspring.document.ItemCapped;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 * @author Chandra
 */

public interface ItemReactiveCappedRepository  extends ReactiveMongoRepository<ItemCapped,String> {

    @Tailable
    Flux<ItemCapped> findItemsBy();
}
