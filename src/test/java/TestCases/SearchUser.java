package TestCases;
import TokenGenerator.generateToken;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
public class SearchUser extends generateToken{
    @Test(priority = 1)
    public void search() {
        baseURI = prop.getProperty("SearchUrl");
        boolean tokenValue = true;
        Response response = given()
                .header("Authorization", "Bearer " +bearerToken)
                .when()
                .get(baseURI).then().extract().response();
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
