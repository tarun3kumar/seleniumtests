package com.seleniumtests.blogexamples.driversetup;

import org.testng.annotations.Test;

public class TestClassUsingOnDemandDriverSetup extends BaseClassOnDemandDriverSetup {

    @Test
    public void testClassInstantiatingDriverWhenNeeded() throws InterruptedException {
        Thread.sleep(5000);
        // driver is instantiates only when required
        getDriver().get("https://www.google.com");
    }

}
