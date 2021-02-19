package com.seleniumtests.tests.builderpatterntest;

import static com.seleniumtests.driver.WebUIDriver.getWebDriver;

import org.testng.annotations.Test;

import com.seleniumtests.builderpattern.BigUser1;
import com.seleniumtests.core.SeleniumTestPlan;

public class WithoutBuilderPattern extends SeleniumTestPlan {

    @Test
    public void withOutUsingBuilderPattern() {
        BigUser1 bigUser1 = new BigUser1("firstName", "lastName", "userName", "address2",
                "address1", "phone1", "phone2", "password", "confirmPassword");

        getWebDriver().get("https://www.seleniumtests.com/2018/02/big-registration-form.html");
        // Page object and further selenium operations using object generated above
        // RegistrationPage registrationPage = new RegistrationPage(bigUser1);
    }
}
