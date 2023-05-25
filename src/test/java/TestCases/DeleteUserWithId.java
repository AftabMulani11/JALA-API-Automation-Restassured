package TestCases;
import TokenGenerator.generateToken;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
public class DeleteUserWithId extends generateToken{
    @Test(priority = 1)
    public void delete(){
        int id = 592; //change the id according to you
        baseURI = (prop.getProperty("UpdateUrl") + "/" + id);
        boolean tokenValue = true;
        Response response = given()
                .header("Authorization", "Bearer " +bearerToken)
                .when()
                .delete(baseURI).then().extract().response();
        System.out.println("Message : " + response.jsonPath().getString("message"));
        Assert.assertTrue(tokenValue);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals("User deleted successfully.", response.jsonPath().getString("message"));
    }
}