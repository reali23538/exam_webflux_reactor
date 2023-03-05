package com.basic.reactor;

import reactor.core.publisher.Flux;

public class ReactorApplication {

    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A");
        flux.map(i -> "foo" + i);
        flux.subscribe(System.out::println);
    }

}
