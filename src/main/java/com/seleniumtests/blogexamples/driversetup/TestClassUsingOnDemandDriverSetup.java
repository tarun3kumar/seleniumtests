package com.seleniumtests.blogexamples.driversetup;

import org.testng.annotations.Test;

public class TestClassUsingOnDemandDriverSetup extends BaseClassOnDemandDriverSetup {

    @Test
    public void testClassInstantiatingDriverWhenNeeded() throws InterruptedException {
        Thread.sleep(5000);
        // Chrome browser opens only on this line when driver is really needed.
        getDriver().get("https://www.google.com");
    }

}
