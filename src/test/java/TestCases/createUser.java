package TestCases;
import java.util.*;
import TokenGenerator.generateToken;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;

public class createUser extends generateToken {
    @Test(priority = 1)
    public void createUser() {
        baseURI = prop.getProperty("UpdateUrl");
        boolean tokenValue = true;
        Assert.assertTrue(tokenValue);
        String[] array = {"aws", "fullstack"};
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("first_name", "Aftab2");
        map.put("last_name", "Mulani2");
        map.put("email", "aftab.m443@gmail.com");
        map.put("mobile", "7897485594");
        map.put("dob", "2001-09-11");
        map.put("gender", "male");
        map.put("address", "Maharashtra");
        map.put("country", "india");
        List<String> result = Arrays.stream(array)
                .toList();
        map.put("skills", result);
        String jsonString = JSONObject.toJSONString(map);
        Response response = given()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Accept", "application/json")
                .contentType(ContentType.JSON).and()
                .body(jsonString).when()
                .post(baseURI).then().extract().response();
        System.out.println("Message : " + response.jsonPath().getString("message"));
        id = Integer.parseInt(response.jsonPath().setRootPath("user").getString("id"));
        System.out.println("uesr Id is  : " + id);

        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals("User inserted successfully.", response.jsonPath().getString("message"));
    }
}
