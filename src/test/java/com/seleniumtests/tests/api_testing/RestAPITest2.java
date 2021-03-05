package com.seleniumtests.tests.api_testing;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.lessThan;

import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class RestAPITest2 extends CoreAPITest {

    @Test
    public void checkArrayElement() {
        get(API_PAGE_2)
                .then()
                .body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in"));
    }

    @Test
    public void specifyingHttpVerb() {
        request("GET", API_PAGE_2)
                .then()
                .statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test
    public void verifyResponseTime() {
        get(API_PAGE_2)
                .then()
                .body("data.email[5]", equalTo("rachel.howell@reqres.in"))
                .and()
                .time(lessThan(2000L), TimeUnit.MILLISECONDS);

        // Rerun with least response time and make it fail
    }

    @Test
    public void fetchHeadersAndCookies() {
        Map<String, String> cookieMap =
                get(API_PAGE_2)
                        .then()
                        .log()
                        .headers()
                        .and()
                        .log()
                        .cookies()
                        .and()
                        .extract()
                        .cookies();

        cookieMap.keySet().forEach(s -> System.out.println("cookie is:" + s + ", values is: " + cookieMap.get(s)));
    }


    @Test
    public void logResponseIfConditionIsTrue() {
        get("/users/eugenp")
                .then().log().ifError();
    }

    @Test
    public void extractEntireResponse() {
        // Once again BDD syntax
        Response response =

                given().
                        pathParam("user_count", 1).
                        when().
                        get("/api/users/{user_count}").
                        then().
                        contentType(JSON).
                        body("data.first_name", equalTo("George")).
                        extract().
                        response();

        MatcherAssert.assertThat(response.path("data.last_name"), equalTo("Bluth"));
        MatcherAssert.assertThat(response.path("data.email"), equalTo("george.bluth@reqres.in"));

        response.headers().asList().forEach(System.out::println);

        // Now response data can be used to create subsequent requests
    }


    @Test
    public void specifyHeader() {
        given()
                .header("my_header", "dummy_header")
                .get(API_PAGE_2)
                .then()
                .body("page", equalTo(2));
    }

    @Test
    public void specifyCookie() {
        given()
                .cookies("my_cookie", "dummy_cookie")
                .get(API_PAGE_2)
                .then()
                .body("page", equalTo(2));
    }

}
