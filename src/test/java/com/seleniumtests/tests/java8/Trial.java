package com.seleniumtests.tests.java8;

import java.util.Arrays;
import java.util.List;

public class Trial {

    public static boolean isGreaterThan4(int n) {
        System.out.println("isGreaterThan 4: " + n);
        return n > 4;
    }

    public static boolean isEven(int n) {
        System.out.println("isEven is called only after number greater than 4 is reached: " + n);
        System.out.println("isEven: " + n);
        return n % 2 == 0;
    }

    public static boolean isEvenWithMoreLogic(int n) {
        System.out.println("isEven is called only after number greater than 4 is reached: " + n);
        System.out.println("isEven: " + n);
        return n % 2 == 0;
    }


    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 13);

        // Get first even number greater than 4 with java8
        System.out.println("###############");
        System.out.println("with Lambda");
        System.out.println(
                list.stream()
                        .filter(i -> isGreaterThan4(i))
                        .filter(i -> isEven(i))
                        .findFirst()
                        .get()
        );

        System.out.println("###############");
        System.out.println("with Lambda and method reference");
        System.out.println(
                list.stream()
                        // Higher order function, passing function to function :)
                        // Method reference is possible since what you receive is what you sent
                        .filter(Trial::isGreaterThan4)
                        // Passing function to function once again :))
                        // Did you check isEven is called only when required aka lazy evaluation
                        // i.e. print line in isEven method is called only when number > 4
                        .filter(Trial::isEven)
                        .findFirst()
                        .get());

        System.out.println("###############");
        System.out.println("No method reference possible due to more logic on isEvenWithMoreLogic call");
                list.stream()
                        // Method reference not possible since you modified what you received
                        .filter(i -> isEvenWithMoreLogic(i * 5))
                        .findFirst()
                        .get();

        System.out.println("###############");
        System.out.println("Remove terminal operation and no intermediate operation is carried out");
        list.stream()
                .filter(Trial::isGreaterThan4)
                .filter(Trial::isEven);

    }
}
