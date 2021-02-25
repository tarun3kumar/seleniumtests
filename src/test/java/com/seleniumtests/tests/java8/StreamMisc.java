package com.seleniumtests.tests.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMisc {

    public static void main(String[] args) throws IOException {

        //Instantiating interface using static method on interface
        IntStream intStream = IntStream.range(1, 10);
        intStream.forEach(System.out::println);


        // Sum int stream
        IntStream anotherIntStream = IntStream.range(1, 10);
        System.out.println(anotherIntStream.sum());


        // Sum of doubles
        Stream<Double> doubleStream = Stream.of(1.0, 3.5, 11.8, 4.3);
        // 0.0 is starting value
        // d1 is running total and d2 is new element being passed
        // Sum, min, max, average, and string concatenation are all special cases of reduction
        // Hence sum can be expressed as - Integer sum = integers.reduce(0, (a, b) -> a+b);
        Double aDouble = doubleStream.reduce(0.0, (d1, d2) -> d1+d2);
        System.out.println(aDouble);
        System.out.println("Did you notice that BinaryOperator on reduce argument does not have abstract method on its own");


        // Once again instantiating Stream interface using static interface method
        Stream<String> stringStream = Stream.of("Marius", "Oleh", "Svitlana", "Thierry", "Alex", "Balint", "Tarun");
        stringStream
                .sorted()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("Can you think of where you can use sorting with WebDriver?");


        // Sort and filter Stream on condition which is satisfied
        Stream<String> stringStream2 = Stream.of("Marius", "Oleh", "Svitlana", "Thierry");
        System.out.println(
                stringStream2
                        .sorted()
                        .filter(s -> s.contains("i"))
                        .findFirst()
                        .orElse("No String matching filter was found"));


        // Sort and filter Stream on condition which is not satisfied, hence nothing is printed
        Stream<String> stringStream3 = Stream.of("Marius", "Oleh", "Svitlana", "Thierry");
        stringStream3
                .sorted()
                .filter(s -> s.contains("xyz"))
                .findFirst()
                .ifPresent(System.out::println);


        //Find average of triple of elements in a stream
        System.out.println(
                Arrays
                        .stream(new int[]{1, 2, 3, 4})
                        .map(n -> n * 3)
                        .average()
                        .getAsDouble()
        );


        // Reading from file
        Stream<String> dataStream = Files.lines(Paths.get("src/test/resources/com/seleniumtests/tests/web/sampledata.txt"));
        dataStream.filter(s->s.contains("test")).forEach(System.out::println);
        // Remember to close it to prevent memory leaks
        dataStream.close();
        System.out.println("Can you think of where you can use it in selenium?");


        // Get data where salary is more than 70000$
        Stream<String> bigData = Files.lines(Paths.get("src/test/resources/com/seleniumtests/tests/web/bigdata.txt"));
        bigData
                .map(d->d.split(","))
                .filter(d->Integer.parseInt(d[2].trim())>70000)
                .forEach(s-> System.out.println(s[0] +", "+ s[1] +", "+s[2] +", "+s[3]));
        bigData.close();


        // Same as prev. but collect big data to a map to be exercised later
        Stream<String> bigDataNew = Files.lines(Paths.get("src/test/resources/com/seleniumtests/tests/web/bigdata.txt"));
        Map<String, List<String>> bigDataMap =
                bigDataNew
                        .map(d -> d.split(","))
                        .filter(d -> Integer.parseInt(d[2].trim()) > 70000)
                        .collect(Collectors.toMap(
                                key -> key[0],
                                values -> Arrays.asList(values[1], values[2], values[3])
                        ));

        bigDataMap.forEach((k, v) -> System.out.println("Key is: " + k + ", values are:" + v));
        bigDataNew.close();
    }
}
