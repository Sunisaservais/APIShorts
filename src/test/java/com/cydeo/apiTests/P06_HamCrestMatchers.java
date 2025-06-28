package com.cydeo.apiTests;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P06_HamCrestMatchers extends SpartanTestBase {



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

    @Test
    public void hamcrestMatchers() {


        given().accept(ContentType.JSON)
                .pathParam("id",2277).
        when().get("/api/spartans/{id}").
        then().assertThat()   // to just increase readability
                .statusCode(200)
                .and()   // to just increase readability
                .contentType("application/json")
                .body("id", is(2277))
                .body("name",is("Fidole"))
                .body("gender",is("Male"))
                .body("phone",is(6105035231l));

    }

    @Test
    public void extractAsResponse() {


        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2277).
                when().get("/api/spartans/{id}").
                then().assertThat()   // to just increase readability
                .statusCode(200)
                .and()   // to just increase readability
                .contentType("application/json")
                .body("id", is(2277))
                .body("name", is("Fidole"))
                .body("gender", is("Male"))
                .body("phone", is(6105035231l))
                .extract().response();

        // Get me id information
        System.out.println("response.path(\"id\") = " + response.path("id"));

    }


    @Test
    public void extractAsJsonPath() {


        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("id", 2277).
                when().get("/api/spartans/{id}").
                then().assertThat()   // to just increase readability
                .statusCode(200)
                .and()   // to just increase readability
                .contentType("application/json")
                .body("id", is(2277))
                .body("name", is("Fidole"))
                .body("gender", is("Male"))
                .body("phone", is(6105035231l))
                .extract().jsonPath();


        // Get me id information
        System.out.println("jsonPath.getInt(\"id\") = " + jsonPath.getInt("id"));

    }





}
