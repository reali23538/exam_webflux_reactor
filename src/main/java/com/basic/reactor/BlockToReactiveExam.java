package com.basic.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockToReactiveExam {

    public static void main(String[] args) throws InterruptedException {
//        blockingMono();
//        blockingFlux();

//        blockingRepositoryToFlux();
        fluxToBlockingRepository();
    }

    public static void blockingMono() {
        // blocking 되서 이렇게 안쓰는게 좋음
        String str = Mono.just("foo").block();
        System.out.println(str);
    }

    public static void blockingFlux() {
        // blocking 되서 이렇게 안쓰는게 좋음
        Iterable<Integer> itr = Flux.range(0, 10).toIterable();
        itr.forEach(System.out::println);
    }

    public static void blockingRepositoryToFlux() throws InterruptedException {
        // blocking 되는 database를 reactive하게 가져오는 방법
        List<User> users = Arrays.asList(new User("abc"), new User("def"));
        UserRepository userRepository = new UserRepository(users);

        Flux.defer(() -> Flux.fromIterable(userRepository.findAll()))
                .subscribeOn(Schedulers.elastic())
                .doOnNext(System.out::println)
                .subscribe();
        Thread.sleep(10000);
    }

    public static void fluxToBlockingRepository() {
        // flux를 database에 넣는 방법
        UserRepository userRepository = new UserRepository();
        Flux<User> flux = Flux.just(new User("ghi"), new User("jkl"));

        flux.publishOn(Schedulers.elastic())
                .doOnNext(userRepository::save)
                .then();
    }

    public static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class UserRepository {

        private List<User> users = new ArrayList<>();

        public UserRepository() {}

        public UserRepository(List<User> users) {
            this.users = users;
        }

        public List<User> findAll() {
            return users;
        }

        public void save(User user) {
            this.users.add(user);
        }
    }

}
