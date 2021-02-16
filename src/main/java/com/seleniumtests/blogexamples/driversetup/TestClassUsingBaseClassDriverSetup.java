package com.seleniumtests.blogexamples.driversetup;

import org.testng.annotations.Test;

public class TestClassUsingBaseClassDriverSetup extends BaseClassDriverSetup {

    @Test
    public void testClassInstantiatingDriverInstantly() throws InterruptedException {
        // Chrome browser opens before control reaches getDriver line i.e. before driver is really needed
        Thread.sleep(5000);
        getDriver().get("https://www.google.com");
        System.out.println("I do nothing useful");
    }
}
