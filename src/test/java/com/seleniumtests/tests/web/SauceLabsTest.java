package com.seleniumtests.tests.web;


import org.testng.annotations.Test;

import com.seleniumtests.core.SeleniumTestPlan;
import com.seleniumtests.webpage.SauceLabsPage;


public class SauceLabsTest extends SeleniumTestPlan {

    public SauceLabsTest() throws Exception {
    }

    @Test(groups = "sauce")
    public void sauceLabsTest() throws Exception {
        SauceLabsPage sauceLabsPage = new SauceLabsPage(true);
        sauceLabsPage.getMeToTheSauceLabs();
    }
}
