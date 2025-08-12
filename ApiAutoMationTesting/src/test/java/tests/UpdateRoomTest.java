package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.AuthTokenProvider;

import static io.restassured.RestAssured.given;

public class UpdateRoomTest extends BaseTest {

    @DataProvider(name = "priceUpdateData")
    public Object[][] priceUpdateData() {
        return new Object[][]{
                {301, 1500}
        };
    }

    @Test(dataProvider = "priceUpdateData")
    public void testUpdateRoomPriceWithAuthentication(int roomId, double roomPrice) {
        RequestSpecification request = given()
                .contentType("application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + AuthTokenProvider.getAccessToken())
                .formParam("roomId", roomId)
                .formParam("roomPrice", roomPrice)
                .log().all();

        Response response = request
                .when()
                .put("/updateRoomPrice")
                .then()
                .log().all()
                .statusCode(200) // success
                .extract().response();

        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message != null && message.contains("Room price updated"),
                "Expected success message not found");
    }

    @Test(dataProvider = "priceUpdateData")
    public void testUpdateRoomPriceWithoutAuthentication(int roomId, double roomPrice) {
        RequestSpecification request = given()
                .contentType("application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
                .formParam("roomId", roomId)
                .formParam("roomPrice", roomPrice)
                .log().all();

        Response response = request
                .when()
                .put("/updateRoomPrice")
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
                "Negative test failed: Room price was updated unexpectedly");
    }
}
