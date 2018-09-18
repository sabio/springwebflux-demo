package com.demos.demowebflux.simpletests;


import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleTests {

    @Test
    public void singleStreamExample(){
        Flux<String> dogs = Flux.just("Donkey", "Filo", "Megan", "Newton");
        dogs.toStream().forEach(System.out::println);
    }

    @Test
    public void singleStreamExample2(){
        Flux<String> dogs = Flux.just("Donkey", "Filo", "Megan", "Newton");
        dogs.subscribe(System.out::println);
    }

    @Test
    public void singleStreamExample3(){
        Flux<String> dogs = Flux.just("Donkey", "Filo", "Megan", "Newton");
        dogs.doOnEach(d -> System.out.println(d.get())).subscribe();
    }

    @Test
    public void singleStreamExample4(){
        Flux<String> dogs = Flux.just("Donkey", "Filo", "Megan", "Newton");
        dogs.subscribe((s) -> System.out.println(s), (error) -> System.out.println(error), () -> System.out.println("Process finished!!!") );
    }

    @Test
    public void singleStreamExampleMapping(){
        Flux<String> dogs = Flux.just("Donkey", "Filo", "Megan", "Newton");
        dogs.map(String::length).doOnEach(System.out::println).subscribe();
    }

    @Test
    public void singleStreamExampleMapping2(){
        Flux<String> dogs = Flux.just("Donkey", "Filo", "Megan", "Newton");
        dogs.map(String::length).doOnEach(s -> System.out.println(s.get())).subscribe();
    }

    @Test
    public void singleStreamExampleFilter(){
        Flux<String> dogs = Flux.just("Donkey", "Filo", "Megan", "Newton");
        dogs.filter(s -> s.length() > 4).doOnEach(s -> System.out.println(s.get())).subscribe();
    }

    @Test
    public void singleStreamExampleLimitAndSort() {
        Flux<String> dogs = Flux.just("Donkey", "Filo", "Megan", "Newton");
        dogs.take(2).doOnEach(System.out::println).subscribe();
        dogs.sort().subscribe(System.out::println);
    }

    @Test
    public void collectJoiningString(){
        Flux<String> dogs = Flux.just("Donkey", "Filo", "Megan", "Newton");
        dogs.collect(Collectors.joining(",")).subscribe(System.out::println);
    }

    @Test
    public void reduction(){
        Flux<String> dogs = Flux.just("Donkey", "Filo", "Megan", "Newton");
        dogs.reduce("", (a,b) -> a+b).subscribe(System.out::println);
    }

    @Test
    public void flatmap(){
        Flux<List<String>> dogs = Flux.just(Arrays.asList("Donkey", "Filo"), Arrays.asList("Megan", "Newton"));
        dogs.flatMap(list -> Flux.fromIterable(list)).subscribe(System.out::println);

    }


}
