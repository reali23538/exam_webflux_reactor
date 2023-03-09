package com.basic.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TransformOperator {

    public static void main(String[] args) throws InterruptedException {
//        capitalizeOne();
//        capitalizeMany();
        asyncCapitalizeMany();
    }

    static void capitalizeOne() {
        Mono.just(new User("jin"))
                .map(user -> new User(user.getUsername().toUpperCase()))
                .subscribe(user -> System.out.println(user.getUsername()));
    }

    static void capitalizeMany() {
        Flux.just(new User("jin"), new User("lee"))
                .map(user -> new User(user.getUsername().toUpperCase()))
                .subscribe(user -> System.out.println(user.getUsername()));
    }

    static void asyncCapitalizeMany() throws InterruptedException {
        Flux.just(new User("jin"), new User("lee"))
                .flatMap(user -> Mono.just(new User(user.getUsername().toUpperCase())))
                .subscribe(user -> user.getUsername());
        Thread.sleep(3000);
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
