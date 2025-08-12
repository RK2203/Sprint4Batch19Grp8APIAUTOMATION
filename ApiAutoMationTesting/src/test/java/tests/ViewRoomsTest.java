package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.response.Response;
import utils.AuthTokenProvider;

import static io.restassured.RestAssured.given;

public class ViewRoomsTest extends BaseTest{

	  @Test
	    public void testViewRoomList() {
		  
		  
		  String tokenString = AuthTokenProvider.getAccessToken();
		  System.out.println(tokenString);
	   
	        Response response =
	            given()
	                .header("Accept", "application/json")
	            .when()
	                .get("/viewRoomList")
	            .then()
	                .log().all()
	                .extract().response();

	      
	        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK");
	        String body = response.asString();
	        Assert.assertTrue(body != null && !body.isEmpty(), "Response body is empty");
	        Assert.assertTrue(body.contains("roomId") || body.contains("roomList"),
	                "Response does not contain expected fields (roomId/roomList)");
	    }
	
}
