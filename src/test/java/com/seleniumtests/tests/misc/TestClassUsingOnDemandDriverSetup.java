package com.seleniumtests.tests.misc;

import org.testng.annotations.Test;

import com.seleniumtests.blogexamples.driversetup.BaseClassOnDemandDriverSetup;

public class TestClassUsingOnDemandDriverSetup extends BaseClassOnDemandDriverSetup {

    @Test
    public void testClassInstantiatingDriverWhenNeeded() throws InterruptedException {
        Thread.sleep(5000);
        // Chrome browser opens only on this line when driver is really needed.
        getDriver().get("https://www.google.com");
        System.out.println("I do nothing useful");
    }

}
