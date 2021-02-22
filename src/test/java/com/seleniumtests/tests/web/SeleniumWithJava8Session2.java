package com.seleniumtests.tests.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.seleniumtests.core.SeleniumTestPlan;
import com.seleniumtests.webpage.RegistrationPage;

public class SeleniumWithJava8Session2 extends SeleniumTestPlan {

    @Test
    public void usingJava8() throws Exception {
        RegistrationPage registrationPage = new RegistrationPage(true);
        List<WebElement> linkElements = registrationPage.getAllLinks();

        System.out.println("###################");
        System.out.println("###################");
        System.out.println("get link count containing text selenium with out lambda");
        List<WebElement> resultantList = new ArrayList<>();
        for (WebElement webElement : linkElements) {
            if(webElement.getText().toLowerCase().contains("selenium")) {
                resultantList.add(webElement);
            }
        }
        System.out.println("Total links containing word selenium: " +resultantList.size());


        System.out.println("###################");
        System.out.println("###################");
        System.out.println("get link count containing text selenium with lambda");
        System.out.println("Total links containing word selenium: " +
                linkElements.stream()
                .filter(s -> s.getText().toLowerCase().contains("selenium"))
                .count());


        System.out.println("###################");
        System.out.println("###################");
        System.out.println("Abstract out the common statements to a method:");
        System.out.println("Total links containing word selenium: "
                +getLinkCountForGivenWord(linkElements, "selenium"));


        System.out.println("###################");
        System.out.println("###################");
        System.out.println("get link count containing text test and word length is > 4,  with out lambda");
        resultantList = new ArrayList<>();
        for (WebElement webElement : linkElements) {
            String word = webElement.getText().toLowerCase();
            if(word.contains("test") && word.length() > 4) {
                resultantList.add(webElement);
            }
        }
        System.out.println("resultant list size: " +resultantList.size());
        System.out.println("print total text of those links: ");
        for(WebElement webElement : resultantList) {
            System.out.println(webElement.getText());
        }

        System.out.println("###################");
        System.out.println("###################");
        System.out.println("get link count containing text test and word length is > 4,  with lambda");
        List<WebElement> listRetrievedUsingLambda =
                linkElements.stream()
                                .filter(element -> element.getText().toLowerCase().contains("test"))
                                .filter(element -> element.getText().length() > 4)
                                .collect(Collectors.toList());
        System.out.println("resultant list size: " +listRetrievedUsingLambda.size());
        System.out.println("print total text of those links: ");
        listRetrievedUsingLambda.forEach(element -> System.out.println(element.getText()));
    }


    private static long getLinkCountForGivenWord(List<WebElement> inputElements, String word) {
        return inputElements.stream()
                .filter(s -> hasGivenWord(s, word))
                .count();

    }

    private static boolean hasGivenWord(WebElement element, String word) {
        return element.getText().toLowerCase().contains(word);
    }


}
