package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;
public class ViewRoomById extends BaseTest{

	
	@Test
	
	public void testViewRoomById() {
		
		
		int actualroomId = Integer.parseInt(ConfigReader.get("roomId"));
		
		Response response = given().
				accept(ContentType.JSON).pathParam("roomId", actualroomId)
				.when().get("/viewRoomById/{roomId}").then().log().all().statusCode(200).extract().response();
		
		
		
		int expectedRoomId  = response.jsonPath().getInt("roomId");
		 Assert.assertEquals(response.statusCode(), 200, "Status Code Check");
		 Assert.assertEquals(actualroomId, expectedRoomId, "Room ID in response does not match the requested ID");
	   
		
		
		
		
		
	}
	
	
	
}
