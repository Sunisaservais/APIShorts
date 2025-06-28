package com.cydeo.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P03_QueryParamTest {

    /*
    Given accept type is JSON
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
    public static void init(){

        RestAssured.baseURI= "http://34.226.136.145:8000";
    }

    @Test
    public void queryParamTest() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParam("nameContains", "J")
                .queryParam("gender", "Female").
                when().get("/api/spartans/search");


        //    Then response status code should be 200
        Assertions.assertEquals(200,response.statusCode());

        //    And response content type is application JSON
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        //    And "Female" should be in response
        Assertions.assertTrue(response.asString().contains("Female"));

        //    And "Janette" should be in response
        Assertions.assertTrue(response.asString().contains("Janette"));

        response.prettyPrint();



    }
}
