package com.epam.mjc;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InterfaceCreator {

    public Predicate<List<String>> isValuesStartWithUpperCase () {
        return x -> x.stream()
                .allMatch(str -> str.length() > 0 && Character.isUpperCase(str.charAt(0)));
    };

    public Consumer<List<Integer>> addEvenValuesAtTheEnd() {
        return x -> {
            Stream<Integer> evenValues = x.stream().filter(i -> i % 2 == 0);
            Stream.concat(x.stream(), evenValues);
        };
    }

    public static Supplier<List<String>> filterCollection(List<String> values) {
        Predicate<String> startsWithUpperCase = s -> s.length() > 0 && Character.isUpperCase(s.charAt(0));
        Predicate<String> endsWith = s -> s.endsWith(".");
        Predicate<String> containMoreThan3Words = s -> s.split(" ").length > 3;

        return () -> values.stream().filter(startsWithUpperCase
                        .and(endsWith)
                        .and(containMoreThan3Words))
                .collect(Collectors.toList());
    }

    public static Function<List<String>, Map<String, Integer>> stringSize() {
        Function<String, Integer> strLength = String::length;
        return list -> list.stream()
                .collect(Collectors.toMap(Object::toString, m -> m.length()));
    }

    public static BiFunction<List<Integer>, List<Integer>, List<Integer>> concatList() {
        return (x1, x2) -> Stream.concat(x1.stream(), x2.stream()).collect(Collectors.toList());
    }
}
