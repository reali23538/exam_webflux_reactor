package com.basic.reactor;

import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class StepVerifierTest {

    public static void main(String[] args) {
//        expectFooBarComplete();
//        expectFooBarError();
        expectSkylerJessiComplete();
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


}
