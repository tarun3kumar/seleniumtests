package com.seleniumtests.tests.java8;

import java.util.Optional;

public class OptionalExample {

    static String data = null; // data retrieved from csv files etc
    static String defaultData = "This is default data";
    static String thisIsNeverNull = "I am never null";

    public static void main(String[] args) {
        System.out.println("##################");
        System.out.println("NPE :-((");
        System.out.println("test data is available: "+data.toLowerCase());


        // Null check with bloated code
        System.out.println("##################");
        System.out.println("bloated null check");
        if (data != null) {
            System.out.println("test data is available: "+data.toLowerCase());
        }



        System.out.println("##################");
        System.out.println("Ways to create Optional - ");
        System.out.println("1. Using Optional of when we know data is not null");
        System.out.println(Optional.of(thisIsNeverNull));


        System.out.println("##################");
        System.out.println("2. Using Optional ofNullable when is null");
        // Use ofNullable when value might be null
        Optional<String> dataOptional = Optional.ofNullable(data);
        // No more ugly null check :-))
        if (dataOptional.isPresent()) {
            System.out.println(dataOptional.get());
        }

        // Using Optional in functional style
        dataOptional.ifPresent(e -> System.out.println(e));

        // Or in functional style with method reference
        dataOptional.ifPresent(System.out::println);
        System.out.println("Did you notice no NPE was thrown despite data being null");



        // Let's run it again when data is not null
        data = "I am not null";
        Optional<String> dataOptionalNew = Optional.ofNullable(data);
        dataOptionalNew.ifPresent(d1-> System.out.println(d1.toUpperCase()));



        //Let's supply default value when data is null, with out Optional;
        System.out.println("##################");
        System.out.println("Without Optional");
        if (data != null) {
            System.out.println("test data is available: "+data.toLowerCase());
        } else {
            System.out.println("data is: " +defaultData);
        }


        System.out.println("##################");
        System.out.println("With Optional > using orElse");
        Optional<String> dataOptionalNew2 = Optional.ofNullable(data);
        System.out.println(dataOptionalNew2.orElse("data is: " +defaultData));
        System.out.println("No bulky if else required :-))");
    }
}
