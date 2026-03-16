package API.tests;

import API.base.BaseTest;
import API.dashboard.TicketPage;
import API.auth.LoginPage;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class TicketTest extends BaseTest {
    TicketPage ticketPage = new TicketPage();
    LoginPage loginPage = new LoginPage();

    @Test
    public void testGetTicketByIdValid() {
        String loginBody = loginPage.loginPayload("admin", "admin");
        String rawToken = given()
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post(loginPage.loginEndpoint)
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        System.out.println("DEBUG: Token berhasil diamankan -> " + rawToken);

        String targetId = "cmmrv38360001kw044gbll5ct";

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + rawToken)

                .cookie("__Secure-next-auth.session-token", rawToken)
                .cookie("next-auth.session-token", rawToken)

                .queryParam("id", targetId)
                .log().all()
                .when()
                .get(ticketPage.ticketByIdEndpoint)
                .then()
                .log().all()
                .statusCode(200);
    }
}