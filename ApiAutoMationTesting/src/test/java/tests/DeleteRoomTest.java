package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.AuthTokenProvider;

import static io.restassured.RestAssured.given;

public class DeleteRoomTest extends BaseTest {

    @DataProvider(name = "deleteRoomData")
    public Object[][] deleteRoomData() {
        return new Object[][]{
                {101}
                
        };
    }

    @Test(dataProvider = "deleteRoomData")
    public void testDeleteRoomWithAuthentication(int roomId) {
        Response response =
            given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + AuthTokenProvider.getAccessToken())
                .log().all()
            .when()
                .delete("/deleteRoomById/" + roomId)
            .then()
                .log().all()
                .statusCode(200) // success
                .extract().response();

        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message != null && message.contains("Room deleted"),
                "Expected success message not found");
    }

    @Test(dataProvider = "deleteRoomData")
    public void testDeleteRoomWithoutAuthentication(int roomId) {
        Response response =
            given()
                .accept(ContentType.JSON)
                .log().all()
            .when()
                .delete("/deleteRoomById/" + roomId)
            .then()
                .log().all()
                .statusCode(Matchers.anyOf(Matchers.is(401), Matchers.is(403))) // unauthorized or forbidden
                .extract().response();

        String body = response.asString();
        Assert.assertTrue(body.contains("Unauthorized")
                        || body.contains("Forbidden")
                        || body.contains("Invalid token"),
                "Expected authentication error message not found");

        Assert.assertFalse(body.contains("successfully"),
                "Negative test failed: Room was deleted unexpectedly");
    }
}
