package com.seleniumtests.tests.web;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.seleniumtests.core.SeleniumTestPlan;
import com.seleniumtests.webpage.RegistrationPage;

public class SeleniumWithJava8 extends SeleniumTestPlan {

    @Test
    public void usingJava8() throws Exception {
        RegistrationPage registrationPage = new RegistrationPage(true);
        List<WebElement> inputElements = registrationPage.getAllTextFields();

        System.out.println("###################");
        System.out.println("###################");
        System.out.println("without lambda");
        for(WebElement webElement : inputElements) {
            System.out.println(webElement.getAttribute("value"));
        }

        System.out.println("###################");
        System.out.println("###################");

        System.out.println("with lambda");
        inputElements.forEach(element -> System.out.println(element.getAttribute("value")));
    }
}
