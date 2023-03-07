package com.basic.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

public class ReactorApplication {

    public static void main(String[] args) throws InterruptedException {
//        emptyFlux();
//        fooBarFlux();
//        fooBarFromList();
//        errorFlux();
//        counterFlux();
    }

    static void emptyFlux() {
        Flux<String> flux = Flux.empty();
        flux.doOnNext(System.out::println)
                .subscribe();
    }

    static void fooBarFlux() {
        Flux<String> flux = Flux.just("foo", "bar");
        flux.subscribe(System.out::println);
    }

    static void fooBarFromList() {
        Flux<String> flux = Flux.fromIterable(Arrays.asList("foo", "bar"));
        flux.subscribe(System.out::println);
    }

    static void errorFlux() {
        Flux<String> flux = Flux.error(IllegalStateException::new);
        flux.doOnError(System.out::println)
                .subscribe();
    }

    static void counterFlux() throws InterruptedException {
        Flux.interval(Duration.ofMillis(100))
                .take(5) // 5개만
                .subscribe(System.out::println);
        Thread.sleep(3000);
    }

}
