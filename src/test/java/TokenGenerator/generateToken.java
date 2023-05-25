package TokenGenerator;
import java.io.IOException;
import java.util.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.*;
import BaseTest.BaseTest;

import static io.restassured.RestAssured.*;

public class generateToken extends BaseTest {
    public static String bearerToken;

    @BeforeMethod
    public  void setupProperties() {
        BaseTest test=new BaseTest();
        try {
            test.setup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test(priority = 0)
    public void generatetoken() throws IOException {
        baseURI = prop.getProperty("LoginUrl");
        Map<String, String> map = new HashMap<String, String>();
        map.put("email", prop.getProperty("username"));
        map.put("password", prop.getProperty("password"));
        JSONObject json = new JSONObject(map);
        Response response = given()
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .and()
                .body(json.toString()).when()
                .post(baseURI).then()
                .extract()
                .response();
        int statusCode = response.statusCode();
        if (statusCode == 200) {

            bearerToken = response.jsonPath().getString("token");
            System.out.println("Token Value :" + bearerToken);
        }
        else {
            System.out.println("Check Your Credentials");
        }
    }

}
