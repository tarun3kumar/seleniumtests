package com.seleniumtests.tests.api_testing;

import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;

public class CoreAPITest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

}
