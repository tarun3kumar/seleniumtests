package com.seleniumtests.tests.java8;

import java.util.Arrays;
import java.util.List;

public class ListExamples {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Get first even number greater than 4 without java8
        System.out.println("###############");
        System.out.println("Without Lambda");
        int result = 0;
        for (int i : list) {
            if (i > 4 && i % 2 == 0) {
                result = i;
                break;
            }
        }
        System.out.println(result);

        // Get first even number greater than 4 with java8
        System.out.println("###############");
        System.out.println("with Lambda");
        result = list.stream()
                .filter(i -> i > 4)
                .filter(i -> i % 2 == 0)
                .findFirst()
                // Do not use get before checking whether Optional has a value or not
                // Bad code follows
                .get();
        System.out.println(result);
    }
}
