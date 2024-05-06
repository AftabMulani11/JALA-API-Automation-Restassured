package TestCases;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
public class DeleteUserWithId extends UpdateUser{
    @Test(priority = 3)
    public void delete(){
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