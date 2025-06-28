package com.cydeo.apiTests;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P10_SpartanPUTPATCH extends SpartanTestBase {

    @Test
    public void spartanPUT() {
        Map<String,Object> putBody=new LinkedHashMap<>();

        putBody.put("name","Rest PUT");
        putBody.put("gender","Male");
        putBody.put("phone",1234512345l);

        System.out.println(putBody);

        given().contentType(ContentType.JSON)
                .pathParam("id",2295)
                .body(putBody).
        when().put("/api/spartans/{id}").
        then().statusCode(204);

    }

    @Test
    public void spartanPATCH() {
        Map<String,Object> patchBody=new LinkedHashMap<>();

        patchBody.put("name","Rest PATCH");

        System.out.println("patchBody = " + patchBody);

        given().contentType(ContentType.JSON)
                .pathParam("id",2295)
                .body(patchBody).
        when().patch("/api/spartans/{id}").
        then().statusCode(204);

    }
}
