package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class FormulaTestBase {

    // /circuits

    @BeforeAll
    public static void init(){

        baseURI= "http://ergast.com/api/f1";
    }
}
