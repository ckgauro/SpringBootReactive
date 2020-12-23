package com.learnreactspring.repository;

import com.learnreactspring.document.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author Chandra
 */
public interface ItemReactiveRepository  extends ReactiveMongoRepository<Item,String> {

    Mono<Item> findByDescription(String description);

}
