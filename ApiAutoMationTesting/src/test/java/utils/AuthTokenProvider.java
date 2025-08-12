package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthTokenProvider {

    private static String accessToken;

    public static String getAccessToken() {
        if (accessToken == null) {
            accessToken = fetchAccessToken();
        }
        return accessToken;
    }

    private static String fetchAccessToken() {
        // Step 1: Login → get auth_code
        Response loginResponse =
            given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .formParam("username", ConfigReader.get("username"))
                .formParam("password", ConfigReader.get("password"))
                .log().all()
            .when()
                .post(ConfigReader.get("loginURI"))
            .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        String authCode = loginResponse.jsonPath().getString("auth_code");

        if (authCode == null || authCode.isEmpty()) {
            throw new RuntimeException("Auth code not found in login response");
        }

        // Step 2: Get access_token using auth_code
        Response tokenResponse =
            given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .formParam("auth_code", authCode)
                .log().all()
            .when()
                .post(ConfigReader.get("tokenURI"))
            .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        String token = tokenResponse.jsonPath().getString("access_token");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Access token not found in token response");
        }

        return token;
    }
}
