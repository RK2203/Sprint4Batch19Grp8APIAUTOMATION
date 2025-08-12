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

public class AddRoomTest extends BaseTest {

    @DataProvider(name = "roomData")
    public Object[][] roomData() {
        return new Object[][]{
                {150, "H2005", "DELUXE", "AVAILABLE", 2500.0}
               
        };
    }

    @Test(dataProvider = "roomData")
    public void testAddRoomWithAuthentication(int roomId, String hotelId, String roomType, String roomStatus, double roomPrice) {

        RequestSpecification request = given()
                .contentType("application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + AuthTokenProvider.getAccessToken())
                .formParam("roomId", roomId)
                .formParam("hotelId", hotelId)
                .formParam("roomType", roomType)
                .formParam("roomStatus", roomStatus)
                .formParam("roomPrice", roomPrice)
                .log().all();

        Response response = request
                .when()
                .post("/addRoom")
                .then()
                .log().all()
                .statusCode(200) // success
                .extract().response();

        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message != null && message.contains("Room added"),
                "Expected success message not found in response");
    }

    @Test(dataProvider = "roomData")
    public void testAddRoomWithoutAuthentication(int roomId, String hotelId, String roomType, String roomStatus, double roomPrice) {

        RequestSpecification request = given()
                .contentType("application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
                .formParam("roomId", roomId)
                .formParam("hotelId", hotelId)
                .formParam("roomType", roomType)
                .formParam("roomStatus", roomStatus)
                .formParam("roomPrice", roomPrice)
                .log().all();

        Response response = request
                .when()
                .post("/addRoom")
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
                "Negative test failed: Room was added unexpectedly");
    }
}
