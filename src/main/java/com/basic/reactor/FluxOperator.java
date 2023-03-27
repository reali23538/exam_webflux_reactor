package com.basic.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;

public class FluxOperator {

    public static void main(String[] args) throws InterruptedException {
//        emptyFlux();
//        fooBarFlux();
//        fooBarFromList();
//        errorFlux();
//        counterFlux();
//        zipFlux();
        thenFlux();
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

    static void zipFlux() {
        Flux<Integer> f1 = Flux.range(0, 10);
        Flux<Integer> f2 = Flux.range(11, 20);
        Flux<Integer> f3 = Flux.range(21, 30);

        Flux.zip(f1, f2, f3)
                .map(tuple -> tuple.getT1() + "/" + tuple.getT2() + "/" + tuple.getT3())
                .subscribe(System.out::println);
    }

    static void thenFlux() {
        Flux<Integer> f = Flux.range(0, 10);
        Mono<Void> m = f.then(); // 그냥 끝나는 Mono가 나옴
        m.subscribe(System.out::println);
    }







}
