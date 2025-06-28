package com.cydeo.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P02_PathParamTest {

    /*
        Given accept type is JSON
        And ID Parameter values is 3
        When user sends GET request /api/spartans/{id}
        Then response status code should be 200
        And response content type is application/json

     */
    @BeforeAll
    public static void init(){

        RestAssured.baseURI= "http://34.226.136.145:8000";
    }


    @Test
    public void pathParamTest() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 410).
                when().get("/api/spartans/{id}");

        // status code should be 200
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);
        Assertions.assertEquals(200,statusCode);

        Assertions.assertEquals(200,response.statusCode());


        // response content type is application/json
        Assertions.assertEquals("application/json",response.contentType());

        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        // print out response

        response.prettyPrint();


    }
}
