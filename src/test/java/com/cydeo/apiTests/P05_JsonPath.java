package com.cydeo.apiTests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P05_JsonPath {

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
    public void getSingleSpartan() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2277).
                when().get("/api/spartans/{id}");

        response.prettyPrint();

        // Status Code --> 200
        assertEquals(200,response.statusCode());

        // Content Type is application/json
        assertEquals(ContentType.JSON.toString(),response.contentType());



        // Create JSON PATH OBJECT
        JsonPath jsonPath = response.jsonPath();


        /*

            And response payload/body values are
             id is 2277
             name is "Fidole"
             gender is "Male"
             phone is 6105035231
         */

        // Retrieve data by using JsonPath

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(2277,id);
        assertEquals("Fidole",name);
        assertEquals("Male",gender);
        assertEquals(6105035231l,phone);

    }

    @Test
    public void getAllSpartans() {

        Response response = get("/api/spartans");

         // response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        // Get me first spartan ID
        int firstSpartanID = jsonPath.getInt("id[0]");

        // Get me second spartan name
        String secondSpartanName = jsonPath.getString("name[1]");

        // Get me last spartan name
        String lastSpartanName = jsonPath.getString("name[-1]");

        // Get me all spartan names
        List<String> allNames = jsonPath.getList("name");

        // Get me all spartan IDs
        List<Integer> allIDs = jsonPath.getList("id");


        System.out.println("firstSpartanID = " + firstSpartanID);
        System.out.println("secondSpartanName = " + secondSpartanName);
        System.out.println("lastSpartanName = " + lastSpartanName);

        System.out.println("allNames = " + allNames);
        System.out.println("allIDs = " + allIDs);

    }
}
