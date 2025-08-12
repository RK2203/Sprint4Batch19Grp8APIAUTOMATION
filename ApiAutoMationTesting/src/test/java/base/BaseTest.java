package base;
import io.restassured.RestAssured;
import utils.ConfigReader;

import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {
        
        RestAssured.baseURI = ConfigReader.get("baseURI");

        // Timeout from config
        int timeout = Integer.parseInt(ConfigReader.get("timeout"));

        RestAssured.config = RestAssured.config()
                .httpClient(RestAssured.config().getHttpClientConfig()
                        .setParam("http.connection.timeout", timeout)
                        .setParam("http.socket.timeout", timeout));

        System.out.println("Base URI set to: " + RestAssured.baseURI);
    }
}
