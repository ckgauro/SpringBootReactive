package com.learnreactspring.controller;

import com.learnreactspring.document.Item;
import com.learnreactspring.repository.ItemReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static constants.ItemConstants.ITEM_END_POINT_V1;

/**
 * @author Chandra
 */

@RestController
@Slf4j
public class ItemController {
    private final ItemReactiveRepository itemReactiveRepository;

    public ItemController(ItemReactiveRepository itemReactiveRepository) {
        this.itemReactiveRepository = itemReactiveRepository;
    }
    @GetMapping(ITEM_END_POINT_V1)
    public Flux<Item> getAllItems(){
        return itemReactiveRepository.findAll();
    }

}
