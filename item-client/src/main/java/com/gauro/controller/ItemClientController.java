package com.gauro.controller;

import com.gauro.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Chandra
 */
@RestController
@Slf4j
public class ItemClientController {
    WebClient webClient = WebClient.create("http://localhost:8080");

    @GetMapping("/client/retrieve")
    public Flux<Item> getAllItemsUsingRetrieve() {
        return webClient.get().uri("/v1/items")
                .retrieve()
                .bodyToFlux(Item.class)
                .log("Item in Client Project retrive:");
    }

    @GetMapping("/client/exchange")
    public Flux<Item> getAllItemsUsingExchange() {
        return webClient.get().uri("/v1/items")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Item.class))
                .log("Items in Client Project exchange: ");
    }

    @GetMapping("/client/exchangeNew")
    public Flux<Item> getAllItemsUsingExchangeNew() {
        return webClient.get().uri("/v1/items")
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Item.class))
                .log("Items in Client Project exchange: ");
    }

    @GetMapping("/client/retrieve/singleItem/{id}")
    public Mono<Item> getOneItemUsingRetrieve(@PathVariable String id) {
        return webClient.get().uri("/v1/items/{id}", id)
                .retrieve()
                .bodyToMono(Item.class)
                .log("Items in Client Project retrieve single Item: ");
    }

    @GetMapping("/client/exchange/singleItem/{id}")
    public Mono<Item> getOneItemUsingExchange(@PathVariable String id) {
        return webClient.get().uri("/v1/items/{id}", id)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(Item.class))
                .log("Item in Client Project retrieve single Item: ");

    }

    @PostMapping("/client/createItem")
    public Mono<Item> createItem(@RequestBody Item item) {
        Mono<Item> itemMono = Mono.just(item);

        return webClient.post().uri("/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemMono, Item.class)
                .retrieve()
                .bodyToMono(Item.class)
                .log("Item Created :");

    }

    @DeleteMapping("/client/deleteItem/{id}")
    public Mono<Void> deleteItem(@PathVariable String id) {
        return webClient.delete().uri("/v1/items/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .log("Deleted Item is :" + id);
    }


    @GetMapping("/client/retrieve/error")
    public Flux<Item> errorRetrieve() {
        return webClient.get()
                .uri("/v1/items/runtimeException")
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    Mono<String> errorMono = clientResponse.bodyToMono(String.class);
                    return errorMono.flatMap(errorMessage -> {
                        log.error("The error Message is :" + errorMessage);
                        throw new RuntimeException(errorMessage);
                    });
                })
                .bodyToFlux(Item.class);

    }

    @GetMapping("/client/exchange/error")
    public Flux<Item> errorExchange() {
        return webClient.get()
                .uri("/v1/items/runtimeException")
                .exchange()
                .flatMapMany(clientResponse -> {
                    if (clientResponse.statusCode().is5xxServerError()) {
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(errorMessage -> {
                                    log.error("Error Message in errorExchange :" + errorMessage);
                                    throw new RuntimeException(errorMessage);
                                });
                    } else {
                        return clientResponse.bodyToFlux(Item.class);
                    }
                });

    }

//    @GetMapping("/client/exchange/errorNew")
//    public Mono<Item> errorExchangeNew() {
//        return webClient.get()
//                .uri("/v1/items/runtimeException")
//                .exchangeToMono(clientResponse -> {
//                    if (clientResponse.statusCode().is5xxServerError()) {
//                        return clientResponse.bodyToFlux(String.class).map(errorMessage -> {
//                            log.error(errorMessage);
//                            return clientResponse.createException().flatMap( Mono::error);
//                           // return clientResponse.createException().map(Flux.just(errorMessage));
//
//
//                          //  return new RuntimeException(errorMessage);
//                        });
//                    } else {
//                        return clientResponse.bodyToMono(Item.class);
//                    }
//                });
//
//    }


}
