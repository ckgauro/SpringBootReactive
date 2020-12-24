package com.learnreactspring.controller;

import com.learnreactspring.repository.ItemReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


/**
 * @author Chandra
 */

@Slf4j
@RestController
public class FluxAndMonoController {



    @GetMapping("/flux")
    public Flux<Integer>  returnFlux(){
        return Flux.just(1,2,3,4)
                //.delayElements(Duration.ofSeconds(1))
                .log();
    }

    //@GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_NDJSON)
    @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer>  returnFluxStream(){
        return Flux.just(1,2,3,4)
                //.delayElements(Duration.ofSeconds(1))
               // .delayElements(Duration.ofMillis(100))
                .log();
    }
    @GetMapping(value = "/fluxstreaminfinte", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long>  returnFluxStreamInfine(){
        return Flux.interval(Duration.ofSeconds(1))
                .log();

    }

    @GetMapping("/mono")
    public Mono<Integer> returnMono(){

        return Mono.just(1)
                .log();

    }
}
