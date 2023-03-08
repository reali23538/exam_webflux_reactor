package com.basic.reactor;

import reactor.core.publisher.Mono;

public class MonoOperator {

    public static void main(String[] args) {
//        emptyMono();
//        monoWithNoSignal();
//        fooMono();
        errorMono();
    }

    static void emptyMono() {
        Mono<String> mono = Mono.empty();
        mono.subscribe(System.out::println);
    }

    static void monoWithNoSignal() {
        Mono<String> mono = Mono.never();
        mono.subscribe(System.out::println);
    }

    static void fooMono() {
        Mono<String> mono = Mono.just("foo");
        mono.subscribe(System.out::println);
    }

    static void errorMono() {
        Mono.error(IllegalStateException::new)
                .subscribe(System.out::println);
    }

}
