package com.seleniumtests.tests.java8;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamMisc2 {

    public static void main(String[] args) throws Exception {

        // Empty stream
        Stream<String> stringStream = Stream.empty();

        // Stream of collection, as seen in previous exercises
        // RegistrationPage registrationPage = new RegistrationPage(true);
        // Stream<WebElement> webElementStream = registrationPage.getAllLinks().stream();

        // Stream of String objects
        Stream<String> stringStream1 = Stream.of("a", "b", "c");

        // Or
        String[] strArray = {"a", "b", "c"};
        Stream<String> stringStream2 = Arrays.stream(strArray);


        // Stream Builder
        // Desired type (i.e. <String> on RHS) needs to be specified else Stream<Object> is returned
        Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

        // generate method
        // Limit the size as generated stream is infinite
        Stream<String> stringStream3 = Stream.generate(() -> "a").limit(5);
        stringStream3.forEach(System.out::println);

        // iterate method also create infinite stream and hence should be limited
        Stream<Integer> integerStream = Stream.iterate(2, n -> n * 2).limit(5);
        integerStream.forEach(System.out::println);

        // intStream as seen on prev. exercise
        IntStream intStream = IntStream.range(1, 5);
        IntStream intStream2 = IntStream.rangeClosed(1, 5);

        // LongStream
        LongStream longStream = LongStream.rangeClosed(1, 5);

        // Creating DoubleStream using Random class
        DoubleStream doubleStream = new Random().doubles(3);
        doubleStream.forEach(System.out::println);


        // Stream creation using File, where each line of file becomes and element of Stream
        // As seen on previous exercise :) just that here encoding is also specified
         Stream<String> bigDataNew = Files.lines(Paths.get("file_path"), Charset.forName("UTF-8"));
         bigDataNew.close();


        // Streams can not be used. Reusing streams throws IllegalStateException
        Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
        Optional<String> firstString = stream.findFirst();
        Optional<String> anyString = stream.findAny();

        // And hence we used collect API in past to collect result of stream i.e.
        List<String> elements =  Stream.of("ab", "bb", "cn", "bg").filter(element -> element.contains("b")).collect(Collectors.toList());
        Optional<String> anyElement = elements.stream().findAny();
        anyElement.ifPresent(System.out::println);

        Optional<String> firstElement = elements.stream().findFirst();
        firstElement.ifPresent(System.out::println);


        // Rule: intermediate operations which reduce the size of the stream
        // should be executed before operations which are applying to each element
        // Like skip(), filter(), distinct() should be at top of stream pipeline
        // This prevents executing unnecessary operations i.e. applying map before filter is expensive
    }
}
