package com.seleniumtests.tests.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class MapExamples {

    public static int tripleIt(int n) {
        System.out.println("Triple: " + n);
        return n * 3;
    }

    // Assigning lambda expression to a variable
    // Lambda expression is essentially an object which can be passed around.
    static Predicate<Integer> isGreaterThan4 = n -> n > 4;
    // This is same as
    /*static Predicate<Integer> isGreaterThan4 = new Predicate<Integer>() {
        @Override
        public boolean test(Integer integer) {
            return integer > 4;
        }
    };*/

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        // Get triple of first even number greater than 4 with java8
        /*System.out.println("###############");
        System.out.println("Without Lambda");
        int result = 0;
        for (int i : list) {
            if (i > 4 && i % 2 == 0) {
                result = i * 3;
                break;
            }
        }
        System.out.println(result);*/


        // Get triple of first even number greater than 4 with java8
        /*System.out.println("###############");
        System.out.println("with Lambda");
        System.out.println(
                list.stream()
                        .filter(Trial::isGreaterThan4)
                        .filter(Trial::isEven)
                        // For a single statement, value is returned automatically hence return is redundant
                        .map(n-> n*3)
                        .findFirst()
                        .get()
        );*/


        // Using predicate and method reference for mapping this time
        System.out.println("###############");
        System.out.println("with Lambda");
        System.out.println(
                list.stream()
                        .filter(isGreaterThan4)
                        .filter(Trial::isEven)
                        .map(MapExamples::tripleIt)
                        .findFirst()
                        .get()
        );
    }
}
