package com.seleniumtests.tests.api_testing;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.hasItems;

import org.testng.annotations.Test;

public class RestAPITest2 extends CoreAPITest {

    @Test
    public void checkArrayElement() {
        get("/api/users?page=2")
                .then()
                .body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in"));
    }

}
