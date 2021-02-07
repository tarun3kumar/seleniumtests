package com.seleniumtests.blogexamples.driversetup;

import org.testng.annotations.Test;

public class TestClassUsingBaseClassDriverSetup extends BaseClassDriverSetup {

    @Test
    public void testClassInstantiatingDriverInstantly() throws InterruptedException {
        // driver is launched before control reaches next statement,
        // Chrome browser opens before control reaches getDriver line
        Thread.sleep(5000);
        getDriver().get("https://www.google.com");
    }
}
