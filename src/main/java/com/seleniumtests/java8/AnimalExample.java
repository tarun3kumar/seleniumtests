package com.seleniumtests.java8;

public class AnimalExample {

    public static void main(String[] args) {
        AnimalExample animalExample = new AnimalExample();

        // Invocation using concrete classes
        System.out.println("Cat's turn");
        animalExample.makeItRun(new Cat());
        System.out.println("###################");
        System.out.println("###################");
        System.out.println("Dog's turn");
        animalExample.makeItRun(new Dog());

        System.out.println("###################");
        System.out.println("###################");
        System.out.println("Anonymous inner class invocation");
        // Invocation using anonymous inner class. How dirty it is : P
        animalExample.makeItRun(new Animal() {
            @Override
            public void runNow() {
                System.out.println("I do not have a body but I run fast");

            }
        });

        System.out.println("###################");
        System.out.println("###################");
        // Lambda expression > arguments LHS (0 arguments here) and body RHS
        System.out.println("Run using Lambda");
        animalExample.makeItRun(() -> System.out.println("I was powered with lambda run"));


        System.out.println("###################");
        System.out.println("###################");
        System.out.println("One more anonymous inner class invocation");
        // Another invocation using anonymous inner class where method takes argument. Still dirty: P
        animalExample.
                makeItRunWithGivenSpeed(new AnimalHavingMethodArgument() {
                    @Override
                    public void runNowWithGivenSpeed(double speed) {
                        System.out.println("I run using given argument: " + speed);
                    }
                }, 70.50);

        System.out.println("###################");
        System.out.println("###################");
        System.out.println("Another run using Lambda");
        // Lambda expression > arguments LHS and body RHS
        animalExample.
                makeItRunWithGivenSpeed(
                        speed -> System.out.println("I run using given argument: " + speed), 70.50);


        System.out.println("###################");
        System.out.println("###################");
        System.out.println("This also uses lambda and is going to print twice");
        // same as above just some more braces
        animalExample.
                makeItRunWithGivenSpeed(
                        (speed) ->
                        {
                            System.out.println("I run using given argument: " + speed);
                            System.out.println("I am running again with double the speed: " + speed * 2);
                        }, 70.50);

    }

    public void makeItRun(Animal animal) {
        animal.runNow();
    }

    public void makeItRunWithGivenSpeed(AnimalHavingMethodArgument animalHavingMethodArgument, double speed) {
        animalHavingMethodArgument.runNowWithGivenSpeed(speed);
    }
}
