package com.cydeo.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_QueryParamTest {

    /*
    Given accepts type is JSON
    And Query Parameter values are
    gender | Female
    nameContains | J
    When user sends GET request /api/spartans/search
    Then response status code should be 200
    And response content type is application JSON
    And "Female" should be in response
    And "Janette" should be in response
     */

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://34.226.136.145:8000";
    }

    @Test
    public void queryParamTest() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("gender", "Female")
                .queryParam("nameContains", "J")
                .when()
                .get("/api/spartans/search");
        response.prettyPrint();
        assertEquals(200, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertTrue(response.getBody().asString().contains("Female"));
        assertTrue(response.getBody().asString().contains("Janette"));

    }
}
