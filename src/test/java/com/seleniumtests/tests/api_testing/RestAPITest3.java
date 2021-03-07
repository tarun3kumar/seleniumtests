package com.seleniumtests.tests.api_testing;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.greaterThan;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class RestAPITest3 extends CoreAPITest {

    @Test
    public void specifyAuth() {
        // in each request
        given().auth()
                .basic("username", "password")
                .get(API_PAGE_2)
                .then()
                .body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in"));

        // or base class setup i.e. in CoreAPITest class
    }

    @Test(dataProvider = "basicAuthCredential")
    public void preemptiveBasicAuth(String userName, String password) {
        given()
                .auth()
                .preemptive()
                .basic(userName, password)
                .get("https://www.httpwatch.com/httpgallery/authentication/authenticatedimage/default.aspx?0.01753906557239593")
                .then()
                .log()
                .cookies()
                .assertThat()
                .cookie("LastPassword", equalTo(password));
    }

    @DataProvider(name = "basicAuthCredential")
    public Object[][] getBasicAuthCredentials() {
        return new Object[][]{{"wrong username", UUID.randomUUID().toString()},
                {"httpwatch", UUID.randomUUID().toString()}};
    }

    @Test
    public void ChallengedBasicAuth() {
        // remove API preemptive from preemptiveBasicAuth example :)
    }

    @Test
    public void postToCreate() {
        Map<String, String> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "seleniumtest");
        jsonAsMap.put("job", "qa");

        Response response = given()
                .body(jsonAsMap)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(HTTP_CREATED)
                .log()
                .body()
                .extract()
                .response();

        MatcherAssert.assertThat(Integer.valueOf(response.path("id")), greaterThan(0));
    }

    @Test
    public void postUsingJavaObject() {

        User user = new User("testuser1", "qa_engineer");

        Response response = given()
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(HTTP_CREATED)
                .log()
                .body()
                .extract()
                .response();

        MatcherAssert.assertThat(Integer.valueOf(response.path("id")), greaterThan(0));
    }

    @Test
    public void deserializeResponse() {

        User user = new User("testuser1", "qa_engineer");

        UserCreationStats userCreationStats = given()
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(HTTP_CREATED)
                .log()
                .body()
                .extract()
                .response()
                .as(UserCreationStats.class);

        System.out.println("id is: " + userCreationStats.id);
        System.out.println("created at : " + userCreationStats.createdAt);
    }

    public class User {
        private String name;
        private String job;

        public User(String name, String job) {
            this.name = name;
            this.job = job;
        }
    }

    public class UserCreationStats {
        private String id;
        private String createdAt;

        public UserCreationStats(String id, String createdAt) {
            this.id = id;
            this.createdAt = createdAt;
        }
    }
}
