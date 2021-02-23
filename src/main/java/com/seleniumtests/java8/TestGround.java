package com.seleniumtests.java8;

public class TestGround {

    @FunctionalInterface
    public interface MyComparator {
        boolean compare(int a1, int a2);
    }

    MyComparator myComparator = (a1, a2) -> a1 > a2;

    public static void main(String[] args) {
        TestGround testGround = new TestGround();
        System.out.println(testGround.myComparator.compare(10, 11));
    }
}
