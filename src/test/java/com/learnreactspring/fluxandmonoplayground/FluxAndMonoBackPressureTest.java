package com.learnreactspring.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

import java.util.Observable;

/**
 * @author Chandra
 */
public class FluxAndMonoBackPressureTest {
    @Test
    public void backPressureTest() {

        Flux<Integer> finiteFlux = Flux.range(1, 10)
                .log();
        StepVerifier.create(finiteFlux)
                .expectSubscription()
                .thenRequest(1)
                .expectNext(1)
                .thenRequest(1)
                .expectNext(2)
//                .thenRequest(5)
//                .expectNext(3)
//                .thenRequest(50)
                .thenCancel()
                .verify();
    }

    @Test
    public void backPressure() {
        Flux<Integer> finiteFlux = Flux.range(1, 10)
                .log();

        finiteFlux.subscribe((element) -> System.out.println("Element is : " + element)
                , (e) -> System.err.println("Exception is : " + e)
                , () -> System.out.println("Done")
                , (subscription -> subscription.cancel()));


    }

    @Test
    public void backPressureWith() {
        Flux<Integer> finiteFlux = Flux.range(1, 10)
                .log();

//        finiteFlux.subscribe(element -> System.out.println("Element is:" + element)
//                , (error) -> System.out.println("Exception is :" + error)
//                , () -> System.out.println("Done!!!")
//
//        );

//        finiteFlux.subscribe((element) -> System.out.println("Element is : " + element)
//                , (e) -> System.err.println("Exception is : " + e)
//                , () -> System.out.println("Done")
//                , (subscription -> subscription.cancel()));


//        Subscriber d = finiteFlux.subscribeWith(
//              //  EmitterProcessor.create()
//                Sinks.Many
//        );


    }
    @Test
    public void customized_backPressure() {
        Flux<Integer> finiteFlux=Flux.range(1,10).log();

        finiteFlux.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnNext(Integer value) {
                request(1);
                System.out.println("Value received is : " + value);
                if(value == 4){
                    cancel();
                }

            }
        });


    }

}
