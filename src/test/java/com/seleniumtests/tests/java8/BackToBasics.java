package com.seleniumtests.tests.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BackToBasics {

    public static void main(String[] args) {

        // Learnt since childhood
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);

        List<Double> doubleList = new ArrayList<Double>();
        doubleList.add(1.0);


        // Integer, Double, Float, Long are subclass of Number
        List<Number> numberList = new ArrayList<Number>();
        numberList.add(1); // allowed as integer is a number
        numberList.add(1.2); // allowed as float is number

        // Even though Integer extends Number
        // List of Number is not same List on Integer. Sounds counter intuitive since Number is super class of Integer
        // But why?
        integerList.add(1);
        integerList.add(2);


        // Not allowed, List of Number is not same as List on Integer.
        // i.e. Number n1 = 1, Number n2 = 1.2;
        // List<Number> numberList1 = integerList;
        // List<Number> numberList2 = new ArrayList<Integer>();

        // If above were possible then following would be allowed
        // numberList1.add(1.0); // Double is a number and now we have two different data types in one List
        // Since numberList1 points to intList we should be able to get Integer out of it, is not it? :))
        // Integer integer = numberList1.get(0);


        // Solution? use wildcards (or bounded wild card to be more precise)
        List<? extends Number> numbers = new ArrayList<Integer>();

        // You still can not add any thing to this list as compiler knows that
        // list contains some type of number but does not know of which type and hence "? extends Number"...
        // Not allowed
        // numbers.add(1);
        // numbers.add(1.0);

        //.. but reading from list is allowed as it guaranteed to return a Number
        Number number = numbers.get(0);

        // Hence <? extends T> can only read T and can not add any type to List
        // But where can we use it?

        // Print IntegerList
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        printIntegerList(integerList);

        // Print Double List
        doubleList.add(1.1);
        doubleList.add(2.2);
        doubleList.add(3.3);
        printDoubleList(doubleList);

        // Print all Number list :))
        printList(integerList);
        printList(doubleList);


        // But what if we need to add to list?
        // And now super type <? super T>
        List<? super Integer> foo1 = new ArrayList<Integer>(); // Integer is a "superclass" of Integer (in this context)
        List<? super Integer> foo2 = new ArrayList<Number>(); // Number is a superclass of Integer
        List<? super Integer> foo3 = new ArrayList<Object>(); // Object is a superclass of Integer


        // Reading from list
        // Not allowed since foo1 might be pointing to Number or Object
        // Integer integer1 = foo1.get(0);

        // Not allowed since foo2 might be pointing to Integer or Object
        // Number number1 = foo1.get(0);

        // Allowed since list is point to some Object
        Object o = foo1.get(0);

        // Writing to list
        // Can write Integer to list
        foo1.add(1);

        // Homework - What else can you write to such list?


        // But when to use "extends" and when to use "super"
        // Follow PECS :)

        // Producer extends > if you need a List to produce T values i.e. you want to read Ts from the list
        // then you need to declare it with ? extends T, e.g. List<? extends Number>. But you cannot add to this list.


        // Consumer Super > if you need a List to consume T values i.e. you want to write Ts into the list
        // then you need to declare it with ? super T, e.g. List<? super Integer>.
        // But there are no guarantees what type of object you may read from this list.

        // If you need to both read from and write to a list,
        // then you need to declare it exactly with no wildcards, e.g. List<Integer>.


        // Ex
        Collections.copy(new ArrayList<Number>(), new ArrayList<Integer>()); // src produces hences extends and dest consumes hence super

        // Also allowed
        Collections.copy(new ArrayList<Number>(), new ArrayList<Double>());

        // Also allowed
        Collections.copy(new ArrayList<Number>(), new ArrayList<Number>());

        // Not allowed, can not copy Number list to Integer list as Number list might contain Double
        // Collections.copy(new ArrayList<Integer>(), new ArrayList<Number>());


        // Once again
        // Producer produces something more specific, hence extends
        // Consumer consumes something more general, hence super
    }

    private static void printIntegerList(List<Integer> integerList) {
        integerList.forEach(System.out::println);
    }

    private static void printDoubleList(List<Double> doubleList) {
        doubleList.forEach(System.out::println);
    }



    public static void printList(List<? extends Number> numbers) {
        numbers.forEach(System.out::println);
    }
}
