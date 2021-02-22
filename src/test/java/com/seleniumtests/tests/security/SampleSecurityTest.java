package com.seleniumtests.tests.security;

import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ClientApiException;

import com.seleniumtests.blogexamples.driversetup.BaseSecurity;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SampleSecurityTest extends BaseSecurity {

    private static final String REG_URL = "https://juice-shop.herokuapp.com/";

    @Test()
    public void scanRegPage() throws ClientApiException {
        getDriver().get(REG_URL);
        // some more logic using page object goes here
        checkRiskCount(REG_URL);
    }
}