package com.cydeo.apiTests;

import com.cydeo.utilities.NewsAPITestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P14_NewsAPI extends NewsAPITestBase {

    @Test
    public void getNews() {

        Response response = given().log().uri()
                .queryParam("q", "bitcoin")
                .header("x-api-key", "653fa8c0fc50420ca139da8535fd4d2d").
                when().get("/everything").
                then().statusCode(200)
                .extract().response();

        response.prettyPrint();
    }
}
