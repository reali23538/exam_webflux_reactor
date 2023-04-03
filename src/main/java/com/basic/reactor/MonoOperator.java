package com.basic.reactor;

import reactor.core.publisher.Mono;

public class MonoOperator {

    public static void main(String[] args) {
//        emptyMono();
//        monoWithNoSignal();
//        fooMono();
//        errorMono();
//        firstMono();

//        justOrEmptyMono();
//        defaultIfEmptyMono();
        blockingMono();
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

    static void firstMono() {
        Mono<Integer> m1 = Mono.just(1);
        Mono<String> m2 = Mono.just("A");

//        Mono.first(m1, m2)
//                .subscribe(System.out::println);
//        Mono.firstWithSignal(m1, m2)
//                .subscribe(System.out::println);
        Mono.firstWithValue(m1, m2)
                .subscribe(System.out::println);
    }

    static void justOrEmptyMono() {
//        User u = new User();
//        u.setName("a");
        User u = null;

        Mono.justOrEmpty(u)
                .subscribe(System.out::println);
    }

    static void defaultIfEmptyMono() {
        Mono<User> m = Mono.empty();

        m.defaultIfEmpty(new User("b"))
                .subscribe(System.out::println);
    }

    public static void blockingMono() {
        // blocking 되서 이렇게 안쓰는게 좋음
        String str = Mono.just("foo").block();
        System.out.println(str);
    }

    static class User {
        private String name;

        User() {
        }

        User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
