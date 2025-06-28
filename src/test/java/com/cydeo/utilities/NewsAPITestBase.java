package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class NewsAPITestBase {

    @BeforeAll
    public static void init(){

        baseURI= "https://newsapi.org/v2";
    }
}
