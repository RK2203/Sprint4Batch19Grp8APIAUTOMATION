package tests;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class ViewRoomByType extends BaseTest{

	@Test
	
	public void testRoomByType() {
		
		String roomType ="SINGLE";
		Response response = given().
				accept(ContentType.JSON).queryParam("roomType",roomType).when()
				.get("/viewRoomByType").then().log().all().statusCode(200).extract().response();
		
	
		 Assert.assertEquals(response.statusCode(), 200, "Status Code Check");
		 
		    List<String> roomTypes = response.jsonPath().getList("roomType");
		    
		    
		    for(String anString : roomTypes) {
		    	Assert.assertEquals(roomType,anString);
		    }
//		 
		
	}
	
	
}
