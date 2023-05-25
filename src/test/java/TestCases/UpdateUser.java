package TestCases;

import java.util.*;
import TokenGenerator.generateToken;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;

public class UpdateUser extends generateToken {
    @Test(priority = 1)
    public void search() {
        int id = 590; //change the id according to you
        baseURI = (prop.getProperty("UpdateUrl") + "/" + id);
        boolean tokenValue = true;
        String[] array = {"aws", "fullstack"};
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("first_name", "aftab");
        map.put("last_name", "mulani");
        map.put("email", "aftabmulani001@gmail.com");
        map.put("mobile", "7972281954");
        map.put("dob", "2022-02-09");
        map.put("gender", "male");
        map.put("address", "Hyderabad");
        map.put("country", "india");
        List<String> result = Arrays.stream(array)
                .toList();
        map.put("skills", result);
        JSONObject json = new JSONObject(map);
        Response response = given()
                .header("Authorization", "Bearer " +bearerToken)
                .header("Accept", "application/json")
                .contentType(ContentType.JSON).and().body(json).when()
                .put(baseURI).then().extract().response();
        System.out.println("Message : " + response.jsonPath().getString("message"));
        System.out.println("uesr Id is  : " + response.jsonPath().setRootPath("user").getString("id"));
        Assert.assertTrue(tokenValue);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals("User updated successfully.", response.jsonPath().getString("message"));
    }
}
