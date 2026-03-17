package UI.tests;

import UI.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import UI.Login.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.do_login("admin", "admin");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://resonance.dibimbing.id/"), "Login berhasil!");
    }
}