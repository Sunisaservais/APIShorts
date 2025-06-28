package com.cydeo.apiTests;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P09_SpartanPOST extends SpartanTestBase {
    /*
     Given accept type is JSON
     And Content type is JSON
     And request json body is:
     {
     "gender":"Male",
     "name":"John",
     "phone":1234567890
     }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     verify the success value is A Spartan is Born!
     "name": "John",
     "gender": "Male",
     "phone": 1234567890
     */

    @Test
    public void spartanAsString() {

        String requestBody="{\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"John\",\n" +
                "     \"phone\":1234567890\n" +
                "     }";

JsonPath jsonPath = given().accept(ContentType.JSON) // API I need response in json
                            .contentType(ContentType.JSON) // API I am sending data in json
                            .body(requestBody)
                    .when().post("/api/spartans")
                           .prettyPeek()
                    .then()
                            .statusCode(201)
                            .contentType("application/json")
                            .extract().jsonPath();


        String expectedMessage="A Spartan is Born!";

        /*
        {
            "success": "A Spartan is Born!",
            "data": {
                "id": ???,
                "name": "John",
                "gender": "Male",
                "phone": 1234567890
                }
        }
         */


        assertEquals(expectedMessage,jsonPath.getString("success"));
        assertEquals("John",jsonPath.getString("data.name"));
        assertEquals("Male",jsonPath.getString("data.gender"));
        assertEquals(1234567890l,jsonPath.getLong("data.phone"));

        // Get me id that we POST as Spartan
        System.out.println(jsonPath.getInt("data.id"));


    }

    @Test
    public void spartanAsMap() {

        Map<String,Object> spartanMap=new LinkedHashMap<>();
        spartanMap.put("name","John MAP");
        spartanMap.put("gender","Male");
        spartanMap.put("phone",1231231231l);

//if deleted create new spartan for P03
//        Map<String,Object> spartanMap=new LinkedHashMap<>();
//        spartanMap.put("name","Janette");
//        spartanMap.put("gender","Female");
//        spartanMap.put("phone",1231231241l);

//if deleted create new spartan for P04, P05, P06, P07, P08:
//        Map<String,Object> spartanMap=new LinkedHashMap<>();
//        spartanMap.put("name","Fidole");
//        spartanMap.put("gender","Male");
//        spartanMap.put("phone",6105035231l);



        JsonPath jsonPath = given().accept(ContentType.JSON) // API I need response in json
                .contentType(ContentType.JSON) // API I am sending data in json
                .body(spartanMap) // serialization --> JAVA(Collections/POJO) to JSON
                .when().post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json").log().body()
                .extract().jsonPath();


        String expectedMessage="A Spartan is Born!";

        /*
        {
            "success": "A Spartan is Born!",
            "data": {
                "id": 101,
                "name": "John",
                "gender": "Male",
                "phone": 1234567890
                }
        }
         */


        assertEquals(expectedMessage,jsonPath.getString("success"));
        assertEquals(spartanMap.get("name"),jsonPath.getString("data.name"));
        assertEquals(spartanMap.get("gender"),jsonPath.getString("data.gender"));
        assertEquals(spartanMap.get("phone"),jsonPath.getLong("data.phone"));

        // Get me id that we POST as Spartan
        System.out.println(jsonPath.getInt("data.id"));

    }

    @Test
    public void spartanAsPOJO() {

        Spartan spBody=new Spartan();
        spBody.setName("John POJO");
        spBody.setGender("Male");
        spBody.setPhone(3213213213l);

        System.out.println("spBody = " + spBody);


        JsonPath jsonPath =
                given().log().body().accept(ContentType.JSON) // API I need response in json
                       .contentType(ContentType.JSON) // API I am sending data in json
                       .body(spBody). // serialization --> JAVA(Collections/POJO) to JSON
                when().post("/api/spartans").
                then()
                    .statusCode(201)
                    .contentType("application/json")
                    .extract().jsonPath();

        String expectedMessage="A Spartan is Born!";


        assertEquals(expectedMessage,jsonPath.getString("success"));
        assertEquals(spBody.getName(),jsonPath.getString("data.name"));
        assertEquals(spBody.getGender(),jsonPath.getString("data.gender"));
        assertEquals(spBody.getPhone(),jsonPath.getLong("data.phone"));

        // Get me id that we POST as Spartan
        System.out.println(jsonPath.getInt("data.id"));

    }
}
