package com.basic.reactor;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Supplier;

public class StepVerifierTest {

    public static void main(String[] args) {
//        expectFooBarComplete();
//        expectFooBarError();
//        expectSkylerJessiComplete();
        expect10Elements();
    }

    static void expectFooBarComplete() {
        Flux<String> flux = Flux.just("foo", "bar");
        StepVerifier.create(flux)
                .expectNext("foo")
                .expectNext("bar")
                .verifyComplete();
    }

    static void expectFooBarError() {
        Flux<String> flux = Flux.just("foo", "bar");
        StepVerifier.create(flux)
                .expectNext("foo")
                .expectNext("bar")
                .verifyError(RuntimeException.class);
    }

    static void expectSkylerJessiComplete() {
        Flux<User> flux = Flux.just(new User("skyler"), new User("jessi"));
//        StepVerifier.create(flux)
//                .assertNext(u -> assertThat(u.getUsername()).isEqualTo("skyler"))
//                .assertNext(u -> assertThat(u.getUsername()).isEqualTo("jessi"))
//                .verifyComplete();
    }

    public static class User {
        private String username;

        public User(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }

    static void expect10Elements() {
        Flux<Long> take10 = Flux.interval(Duration.ofMillis(100))
                .take(10);

        StepVerifier.create(take10)
                .expectNextCount(10)
                .verifyComplete();
    }

    static void expect3600Elements(Supplier<Flux<Long>> supplier) {
        /*
            1초에 하나씩 숫자 데이터를 만든다고 했을때
            1시간을 감아서
            3600개가 되는지 확인하는 예제
         */
        StepVerifier.withVirtualTime(supplier)
                .thenAwait(Duration.ofHours(1))
                .expectNextCount(3600)
                .verifyComplete();
    }

}
