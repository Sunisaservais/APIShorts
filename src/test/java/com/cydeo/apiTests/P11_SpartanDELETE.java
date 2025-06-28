package com.cydeo.apiTests;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P11_SpartanDELETE extends SpartanTestBase{




    @Test
    public void spartanDELETE() {

       given().pathParam("id",2294).
        when().delete("/api/spartans/{id}").
        then().statusCode(204);


        // GET REQUEST to see 404 NOT FOUND

        given().accept(ContentType.JSON)
                .pathParam("id",2294).
        when().get("/api/spartans/{id}").
        then().statusCode(404);



    }
}
