package com.seleniumtests.tests.web;

import java.util.Optional;

import org.testng.annotations.Test;

import com.seleniumtests.core.SeleniumTestPlan;
import com.seleniumtests.webpage.RegistrationPage;

public class SeleniumWithJava8Session4 extends SeleniumTestPlan {

    // data being read from csv etc sources
    static Optional<String> testdata = Optional.ofNullable("data set 1");
    static String defaultData = "Default Test data";


    @Test
    public void usingJava8() throws Exception {
        RegistrationPage registrationPage = new RegistrationPage(true);
        registrationPage.acceptCookie();


        System.out.println("####################");
        System.out.println("test with lower and upper case data");
        registrationPage.enterFirstName(testdata.orElse(defaultData));
        registrationPage.clickSubmitButton();
        // Some assertion go here

        new RegistrationPage(true);
        registrationPage.enterFirstName(testdata.map(String::toUpperCase).orElse(defaultData));
        registrationPage.clickSubmitButton();
        // Some more assertions go here



        System.out.println("####################");
        System.out.println("test when data value is null");
        testdata = Optional.ofNullable(null);
        registrationPage.enterFirstName(testdata.orElse(defaultData));
        registrationPage.clickSubmitButton();
        // Some assertion go here

        new RegistrationPage(true);
        // No upper case operation would be executed this time since data value is null
        registrationPage.enterFirstName(testdata.map(String::toUpperCase).orElse(defaultData));
        registrationPage.clickSubmitButton();
        // Some more assertions go here



        System.out.println("####################");
        System.out.println("test data when locale is available");
        // Locale against which tests are to be run i.e. "en", "fr" etc
        // Default locale is "en"
        // Locale read from external file like testng.xml
        registrationPage.enterFirstName(getTestData("fr"));
        registrationPage.clickSubmitButton();
        // Some more assertions here



        System.out.println("####################");
        System.out.println("test data when locale is not available");
        // Hence test data for default language english would be considered
        registrationPage.enterFirstName(getTestData(null));
        registrationPage.clickSubmitButton();
        // Some more assertions here


        System.out.println("####################");
        System.out.println("Throw exception when Optional is empty");
        System.out.println("Would be retired 3 times due to retry analyzer");
        String locale = null;
        getTestDataElseThrowException(locale);



    }


    private static String getTestData(String locale) {
        if (Optional.ofNullable(locale).isPresent()) {
            return "données de test";
        } else {
            return defaultData;
        }
    }

    private static String getTestDataElseThrowException(String locale) {
        return Optional.ofNullable(locale).orElseThrow(IllegalStateException::new);
    }

}
