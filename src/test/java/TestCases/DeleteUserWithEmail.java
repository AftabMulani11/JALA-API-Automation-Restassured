package TestCases;

import java.util.*;

import TokenGenerator.generateToken;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.baseURI;

public class DeleteUserWithEmail extends generateToken {
    @Test(priority = 1)
    public void delete(){
        baseURI = prop.getProperty("UpdateUrl");
        boolean tokenValue = true;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", "aftab.m111@gmail.com");
        JSONObject json = new JSONObject(map);
        Response response = given()
                .header("Authorization", "Bearer " +bearerToken)
                .header("Accept", "application/json")
                .contentType(ContentType.JSON).
                and()
                .body(json).when()
                .delete(baseURI).then().extract().response();
        System.out.println("Message : " + response.jsonPath().getString("message"));
        Assert.assertTrue(tokenValue);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals("User deleted successfully.", response.jsonPath().getString("message"));
    }
}
