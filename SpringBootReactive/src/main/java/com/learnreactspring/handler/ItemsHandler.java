package com.learnreactspring.handler;

import com.learnreactspring.document.Item;
import com.learnreactspring.repository.ItemReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

//import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author Chandra
 */
@Slf4j
@Component
public class ItemsHandler {
    private final ItemReactiveRepository itemReactiveRepository;

    @Autowired
    public ItemsHandler(ItemReactiveRepository itemReactiveRepository) {
        this.itemReactiveRepository = itemReactiveRepository;
    }

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    public Mono<ServerResponse> getAllItems(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemReactiveRepository.findAll(), Item.class);

    }

    public Mono<ServerResponse> getOneItem(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<Item> itemMono = itemReactiveRepository.findById(id);
        return itemMono.flatMap(item -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                //.body(fromObject(item)))
                .body(fromValue(item)))
                .switchIfEmpty(notFound)
                ;
    }

    public Mono<ServerResponse> createItem(ServerRequest serverRequest) {
        Mono<Item> itemToBeInserted = serverRequest.bodyToMono(Item.class);

        return itemToBeInserted.flatMap(item ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(itemReactiveRepository.save(item), Item.class)
        );

    }

    public Mono<ServerResponse> deleteItem(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");

        Mono<Void> deleteItem = itemReactiveRepository.deleteById(id);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deleteItem, Void.class);
    }

    public Mono<ServerResponse> updateItem(ServerRequest serverRequest) {

        String id = serverRequest.pathVariable("id");
        Mono<Item> updateItem = serverRequest.bodyToMono(Item.class)
                .flatMap(item -> {
                    Mono<Item> itemMono = itemReactiveRepository.findById(id)
                            .flatMap(item1 -> {
                                log.info("====**************====updateItem  Found==");
                                item1.setDescription(item.getDescription());
                                item1.setPrice(item.getPrice());
                                return itemReactiveRepository.save(item1);
                            });
                    return itemMono;
                });

        log.info("======**************====updateItem  starts==");
        log.info(updateItem.toString());
        log.info("======**************====updateItem  Ends==");
        return updateItem.flatMap(item -> {
            log.info("======**************====item  starts==");
            log.info(item.toString());
            log.info("======**************====item  Ends==");
            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(fromValue(item));
        }).switchIfEmpty(notFound);
    }

//    public Mono<ServerResponse> updateItem(ServerRequest serverRequest) {
//
//        String id = serverRequest.pathVariable("id");
//
//        Mono<Item> updatedItem = serverRequest.bodyToMono(Item.class)
//                .flatMap((item) -> {
//
//                    Mono<Item> itemMono = itemReactiveRepository.findById(id)
//                            .flatMap(currentItem -> {
//                                currentItem.setDescription(item.getDescription());
//                                currentItem.setPrice(item.getPrice());
//                                return itemReactiveRepository.save(currentItem);
//
//                            });
//                    return itemMono;
//                });
//
//        return updatedItem.flatMap(item ->
//                ServerResponse.ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(fromValue(item)))
//                .switchIfEmpty(notFound);
//
//
//    }


    public Mono<ServerResponse> itemsEx(ServerRequest serverRequest) {

        throw new RuntimeException("RuntimeException Occurred");
    }


}
