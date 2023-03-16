package com.basic.reactor;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorOperator {

    public static void main(String[] args) {
//        monoOnErrorResume();
        fluxOnErrorResume();
//        monoOnErrorReturn();
    }

    static void monoOnErrorResume() {
        Mono.error(new RuntimeException()).log()
                .onErrorResume(e -> Mono.just(2))
                .doOnNext(System.out::println)
                .subscribe();
    }

    static void fluxOnErrorResume() {
        Flux.error(new RuntimeException()).log()
                .onErrorResume(e -> Flux.just(1, 2, 3))
                .doOnNext(System.out::println)
                .subscribe();
    }

    static void monoOnErrorReturn() {
        Mono.just("hello")
                .log()
                .map(str -> {
                    try {
                        return Integer.parseInt(str);
                    } catch (Exception e) {
                        System.out.println("error");
                        throw Exceptions.propagate(e);
                    }
                })
                .onErrorReturn(200)
                .doOnNext(System.out::println)
                .subscribe();
    }

}
