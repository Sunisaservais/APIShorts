package com.cydeo.apiTests;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P07_JsonToCollections extends SpartanTestBase {

     /*
    Given accept type is JSON
    And path param id is 3
    When user sends GET request /api/spartans/{id}
    Then response status code should be 200
    And response content type is application/json
    And response payload/body values are
         id is 2277
         name is "Fidole"
         gender is "Male"
         phone is 6105035231
     */


    @Test
    public void jsonToMap() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2277).
                when().get("/api/spartans/{id}").
                then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        // FIRST WAY --> RESPONSE.AS() ---> MAP

        Map<String,Object> spartanMap = response.as(Map.class);
        System.out.println("spartanMap = " + spartanMap);


        //         id is 3
        assertEquals(2277,spartanMap.get("id"));
        //         name is "Fidole"
        assertEquals("Fidole",spartanMap.get("name"));

        // SECOND WAY --> JSONPATH.GETMAP() ---> MAP

        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> spMap = jsonPath.getMap("");
        System.out.println("spMap = " + spMap);

        //         id is 3
        assertEquals(2277,spMap.get("id"));
        //         name is "Fidole"
        assertEquals("Fidole",spMap.get("name"));

    }

    @Test
    public void jsonToList() {

        Response response = given().accept(ContentType.JSON).
                when().get("/api/spartans").
                then().statusCode(200)
                .extract().response();

        // FIRST WAY --> RESPONSE.AS(List.class)
        System.out.println("******** RESPONSE.AS ********");
        List<Map<String,Object>> spartanListAsMap = response.as(List.class);

        for (Map<String, Object> eachSpartan : spartanListAsMap) {
            System.out.println(eachSpartan);
        }

        System.out.println("******** JSONPATH.GETLIST ********");

        // SECOND WAY --> jsonPath.getList("")
        JsonPath jsonPath = response.jsonPath();
        List<Map<String,Object>> spListAsMap = jsonPath.getList("");

        for (Map<String, Object> eachSpartan : spListAsMap) {
            System.out.println(eachSpartan);
        }


    }


    @Test
    public void partialJsonToMap() {

        Response response = given().accept(ContentType.JSON).
                when().get("/api/spartans").
                then().statusCode(200)
                .extract().response();

        //RESPONSE AS --> It will not allow to put GPATH SYNTAX to retrieve partial JSON OBJECT

        // JSONPATH --> will allow us to put GPATH SYNTAX to retrieve partial part of JSON OBJECT

        JsonPath jsonPath = response.jsonPath();

        Map<String, Object> firstSpartanAsMap = jsonPath.getMap("[0]");

        System.out.println("firstSpartanAsMap = " + firstSpartanAsMap);


    }
}
