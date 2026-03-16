package API.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "https://resonance.dibimbing.id";
    }
}