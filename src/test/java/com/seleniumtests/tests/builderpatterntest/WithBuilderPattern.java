package com.seleniumtests.tests.builderpatterntest;

import static com.seleniumtests.driver.WebUIDriver.getWebDriver;

import org.testng.annotations.Test;

import com.seleniumtests.builderpattern.BigUser2;
import com.seleniumtests.core.SeleniumTestPlan;

public class WithBuilderPattern extends SeleniumTestPlan {

    @Test
    public void withBuilderPattern() {
        BigUser2 bigUser2 = new BigUser2.Builder()
                .firstName("firstName")
                .lastName("lastName")
                .userName("userName")
                .address1("address1")
                .address2("address2")
                .phone1("phone1")
                .phone2("phone2")
                .password("password")
                .confirmPassword("confirmPassword")
                .build();


        getWebDriver().get("https://www.seleniumtests.com/2018/02/big-registration-form.html");
        // Page object and further selenium operations using object generated above
        // RegistrationPage registrationPage = new RegistrationPage(bigUser1);
    }
}
