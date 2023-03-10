package com.basic.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MergeOperator {

    public static void main(String[] args) {
//        mergeTest();
        mergeMonoTest();
    }

    static void mergeTest() {
        Flux<Long> flux1 = Flux.interval(Duration.ofMillis(100)).take(10);
        Flux<Long> flux2 = Flux.just(100l, 200l, 300l);

        flux1
//                .mergeWith(flux2) // 먼저 나온 순서대로
                .concatWith(flux2) // 순서대로
                .doOnNext(System.out::println)
                .blockLast();
    }

    static void mergeMonoTest() {
        Mono<Integer> mono1 = Mono.just(1);
        Mono<Integer> mono2 = Mono.just(2);

        Flux.concat(mono1, mono2) // 순서대로 합쳐짐
                .doOnNext(System.out::println)
                .blockLast();
    }



}
