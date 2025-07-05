package com.cydeo.apiTests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
    public static void init() {
        baseURI = "http://cydeo.onthewifi.com:8000";
    }
        @DisplayName("Path Method")
        @Test
        public void pathMethod() {
            Response response = given()
                    .accept(ContentType.JSON)
                    .and()
                    .pathParam("id", 3)
                    .when()
                    .get("/api/v2/spartans/{id}");

            response.prettyPrint();


            assertEquals(200, response.getStatusCode());
            assertEquals(ContentType.JSON.toString(), response.contentType());

            JsonPath jsonPath = response.jsonPath();

            int id = jsonPath.getInt("data.id");
            assertEquals(3, id);

            String name = jsonPath.getString("data.name");
            assertEquals("Fidole", name);

            String gender = jsonPath.getString("data.gender");
            assertEquals("Male", gender);

            String phoneStr = jsonPath.getString("data.phone");
            long phone = Long.parseLong(phoneStr);
            assertEquals(6105035231L, phone);
        }


    }

