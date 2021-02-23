package com.seleniumtests.tests.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.seleniumtests.core.SeleniumTestPlan;
import com.seleniumtests.webpage.RegistrationPage;

public class SeleniumWithJava8Session3 extends SeleniumTestPlan {

    private static boolean hasWordTest(WebElement element) {
        return SeleniumWithJava8Session2.hasGivenWord(element, "test");
    }

    @Test
    public void usingJava8() throws Exception {
        RegistrationPage registrationPage = new RegistrationPage(true);
        List<WebElement> linkElements = registrationPage.getAllLinks();


        System.out.println("###################");
        System.out.println("###################");
        System.out.println("Without Lambda");
        // Task:
        // Find all the links containing word "test" where word length is > 4
        // Create new collection by appending word "new data" to returned collection
        // without java 8
        List<String> listContainingWordTest = new ArrayList<>();
        for(WebElement webElement : linkElements) {
            String linkText = webElement.getText();
            if(linkText.toLowerCase().contains("test") && linkText.length() > 4) {
                listContainingWordTest.add(linkText);
            }
        }
        System.out.println("listContainingWordTest: " +listContainingWordTest);
        System.out.println("##################");
        List<String> newTestDataList = new ArrayList<>();
        for(String word: listContainingWordTest) {
            newTestDataList.add(word.concat(" new data"));
        }
        System.out.println("newTestDataList: " +newTestDataList);




        System.out.println("###################");
        System.out.println("###################");
        System.out.println("only lambda from now on :))");

        // And now with java 8
        List<String> testData =
                linkElements
                        .stream()
                        .filter(e -> e.getText().toLowerCase().contains("test"))
                        .filter(webElement -> webElement.getText().length() > 4)
                        .map(element -> element.getText().concat(" new data"))
                        .collect(Collectors.toList());
        // Did you notice that map above returns Stream of String
        // How small is the implementation using map :)
        testData.forEach(d-> System.out.println("new data is: "+d));
        testData.forEach(data -> registrationPage.enterFirstName(data));



        // And now with java 8 and method references
        testData =
                linkElements
                        .stream()
                        .filter(SeleniumWithJava8Session3::hasWordTest)
                        .filter(element -> element.getText().length() > 4)
                        .map(element -> element.getText().concat(" new data"))
                        .collect(Collectors.toList());
        testData.forEach(d-> System.out.println("new data is: "+d));
    }
}
