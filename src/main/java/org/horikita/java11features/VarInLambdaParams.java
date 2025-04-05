package org.horikita.java11features;

import java.util.List;

//Java 10 introduced var for local variables, but Java 11 allows var in lambda parameters.
public class VarInLambdaParams {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        numbers.stream().map((var num) -> num * 2)
                .forEach(System.out::println);
    }

}
