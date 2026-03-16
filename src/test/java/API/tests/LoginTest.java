package API.tests;

import API.auth.LoginPage;
import API.base.BaseTest;
import API.utils.DataUtility;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest {
    LoginPage loginPage = new LoginPage();

    @Test
    public void testLoginSuccess() {
        String body = loginPage.loginPayload("admin", "admin");

        String token = given()
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .post(loginPage.loginEndpoint)
                .then()
                .log().all()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        DataUtility.accessToken = token;
        System.out.println("Login Berhasil, Token: " + token);
    }
}