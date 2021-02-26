package com.seleniumtests.tests.web;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

import com.seleniumtests.core.SeleniumTestPlan;
import com.seleniumtests.webpage.RegistrationPage;

public class SeleniumWithJava8Session6 extends SeleniumTestPlan {

    @Test
    public void usingJava8() throws Exception {
        RegistrationPage registrationPage = new RegistrationPage(true);
        registrationPage.acceptCookie();

        List<String> requiredListElements =
                registrationPage
                        .getAllLinks()
                        .stream()
                        .map(e -> e.getText().toLowerCase())
                        .skip(10) // Remove first 10 elements
                        .collect(Collectors.toList());

        System.out.println("remainingListElements.size: " + requiredListElements.size());
        requiredListElements.forEach(System.out::println);


        // Remove empty links
        System.out.println("##########################");
        List<String> remainingListElements =
                requiredListElements
                        .stream()
                        .filter(s -> s.length() > 0) // Remove empty links
                        .collect(Collectors.toList());
        System.out.println("remainingListElements.size(): " + remainingListElements.size());
        remainingListElements.forEach(System.out::println);
        System.out.println("##########################");


        // filter links containing word "more"
        System.out.println("##############");
        List<String> linksWithWordMore =
                remainingListElements
                        .stream()
                        .filter(s -> s.contains("more"))
                        .collect(Collectors.toList());
        System.out.println("linksWithWordMore.size(): " + linksWithWordMore.size());
        linksWithWordMore.forEach(System.out::println);
        System.out.println("##########################");
    }
}
