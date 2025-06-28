package com.cydeo.apiTests;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P12_BasicAuth extends SpartanAuthTestBase {


    @Test
    public void GETRequestAsGuestUser() {

        given().accept(ContentType.JSON).
        when().get("/api/spartans").
        then().statusCode(401)
                .body("error", Matchers.is("Unauthorized"));
    }

    @Test
    public void GETRequestAsUSER() {

        given().accept(ContentType.JSON)
                        .auth().basic("user","user").
        when().get("/api/spartans").
        then().statusCode(200);


    }

    @Test
    public void DELETERequestAsUSER() {

        given().pathParam("id",100)
                .auth().basic("user","user").
        when().delete("/api/spartans/{id}").
        then().statusCode(403)
                .body("error",Matchers.is("Forbidden"));


    }
}
