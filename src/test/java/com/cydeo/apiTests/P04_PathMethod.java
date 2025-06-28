package com.cydeo.apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_PathMethod {

    /*
    Given accept type is JSON
    And path param id is 3
    When user sends GET request /api/spartans/{id}
    Then response status code should be 200
    And response content type is application/json
    And response payload/body values are
         id is 3
         name is "Fidole"
         gender is "Male"
         phone is 6105035231
     */

    @BeforeAll
    public static void init(){

        baseURI= "http://34.226.136.145:8000";
    }

    @Test
    public void pathMethod() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2277).
                when().get("/api/spartans/{id}");

        response.prettyPrint();

        //    Then response status code should be 200
        assertEquals(200,response.statusCode());

        //    And response content type is application/json
        assertEquals(ContentType.JSON.toString(),response.contentType());

        //    And response payload/body values are
        /*
            {
                "id": 2277,
                "name": "Fidole",
                "gender": "Male",
                "phone": 6105035231
            }
         */
        //         id is 3
        int id=response.path("id");
        System.out.println("id = " + id);
        assertEquals(2277,id);

        //         name is "Fidole"
        String name=response.path("name");
        System.out.println("name = " + name);
        assertEquals("Fidole",name);

        //         gender is "Male"
        String gender=response.path("gender");
        System.out.println("gender = " + gender);
        assertEquals("Male",gender);

        //         phone is 6105035231
       long phone= response.path("phone");
        System.out.println("phone = " + phone);
        assertEquals(6105035231l,phone);


    }

    @Test
    public void getAllSpartans() {

        Response response = get("/api/spartans");

        response.prettyPrint();


        // Get me first spartan ID
        System.out.println("response.path(\"id[0]\") = " + response.path("id[0]"));

        //Get me second spartan name
        System.out.println("response.path(\"name[1]\") = " + response.path("name[1]"));

        //Get me last spartan name
        System.out.println("response.path(\"name[-1]\") = " + response.path("name[-1]"));

        //Get me all spartan names

       List<String> allNames= response.path("name");
        System.out.println("***** NAMES *****");
        System.out.println(allNames);


        //Get me all spartan IDs
        System.out.println("***** IDs *****");
        List<Integer> allIDs=response.path("id");
        System.out.println(allIDs);


    }
}