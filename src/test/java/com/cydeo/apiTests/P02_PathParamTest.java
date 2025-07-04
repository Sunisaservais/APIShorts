package com.cydeo.apiTests;

import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_PathParamTest {

    /*
        Given accepts type is JSON
        And ID Parameter values is 3
        When user sends GET request /api/spartans/{id}
        Then response status code should be 200
        And response content type is application/json

     */
    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://34.226.136.145:8000";
    }

    @DisplayName("Get a Spartans by ID")
    @Test
    public void getSpartans() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 410)
                .when()
                .get("/api/spartans/{id}");

        response.prettyPrint();
        assertEquals(200, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

    }
}
