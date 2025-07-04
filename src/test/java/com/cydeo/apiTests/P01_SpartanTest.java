package com.cydeo.apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanTest {

    String spartanBaseURL="http://34.226.136.145:8000";

    @Test
    public void getAllSpartan() {

        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");

        //Status CODE
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

        Assertions.assertEquals(200,statusCode);

        //CONTENT TYPE
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);

        Assertions.assertEquals("application/json",contentType);

        // Print BODY
        System.out.println("***** RESPONSE AS STRING ********");
        System.out.println(response.asString());

        System.out.println("***** RESPONSE PRETTY PRINT ********");
        System.out.println(response.prettyPrint());
    }
}
