package com.seleniumtests.java8;

import java.util.Arrays;
import java.util.List;

public class MapExamples {

    public static int tripleIt(int n) {
        System.out.println("Triple: " + n);
        return n * 3;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        // Get triple of first even number greater than 4 with java8
        System.out.println("###############");
        System.out.println("Without Lambda");
        int result = 0;
        for (int i : list) {
            if (i > 4 && i % 2 == 0) {
                result = i * 3;
                break;
            }
        }
        System.out.println(result);

        // Get triple of first even number greater than 4 with java8
        System.out.println("###############");
        System.out.println("with Lambda");
        System.out.println(
                list.stream()
                        .filter(Trial::isGreaterThan4)
                        .filter(Trial::isEven)
                        .map(MapExamples::tripleIt)
                        .findFirst()
                        .get()
        );
    }
}
