package com.basic.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static reactor.core.scheduler.Schedulers.parallel;

public class TransformOperator {

    public static void main(String[] args) throws InterruptedException {
//        capitalizeOne();
//        capitalizeMany();
//        asyncCapitalizeMany();
        flatMapSequentialTest();
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
                .subscribe(user -> System.out.println(user.getUsername()));
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

    static void flatMapSequentialTest() {
        Flux.just("a","b","c",
                  "d","e","f",
                  "g","h","i")
                .window(3)
//                .flatMap(f -> f.map(TransformOperator::toUpperCase)) // 딜레이 있는채로
//                .flatMap(f -> f.map(TransformOperator::toUpperCase).subscribeOn(parallel())) // 병렬로 > 딜레이 없어짐 > 단 섞여있음
//                .concatMap(f -> f.map(TransformOperator::toUpperCase).subscribeOn(parallel())) // 순서유지 > 다시 딜레이가 생김
                .flatMapSequential(f -> f.map(TransformOperator::toUpperCase).subscribeOn(parallel())) // 병렬로&순서유지 > 딜레이 없어짐
                .doOnNext(System.out::println)
                .blockLast();
    }

    static List<String> toUpperCase(String s) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(s.toUpperCase(), Thread.currentThread().getName());
    }

}
