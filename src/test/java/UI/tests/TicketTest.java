package UI.tests;

import UI.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import UI.Login.LoginPage;
import UI.Dashboard.TicketPage;
import java.time.Duration;

public class TicketTest extends BaseTest {

    @DataProvider(name = "ticketData")
    public Object[][] getTicketData() {
        return new Object[][] {
                // { Judul, Deskripsi, Tipe Tiket, Kata Kunci URL Diharapkan }
                /*{"Success Ticket Test", "Deskripsi valid", "Private", "new"},
                {" ", "  ", "Private", "new"}, // Blank Space Bug
                {"\ud83d\ude2d", "\u26d4", "Private", "new"}, // Emoticon Bug*/
                {"«‘“π…æ¬˚ºåß≤ºåß¡`¡™£¢•¶§∞§º", "Bug", "Private", "new"}, // Input Validation Bug
                /*{"Success Ticket", "Ini adalah tiket private", "Private", "ticket"}*/
        };
    }

    @Test(dataProvider = "ticketData")
    public void testCreateNewTicket(String title, String desc, String ticketType, String expectedUrl) {
        LoginPage loginPage = new LoginPage(driver);
        TicketPage ticketPage = new TicketPage(driver);

        // Menggantikan Thread.sleep dengan batas tunggu maksimal 15 detik
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage.do_login("user4", "password");

        test.info("Klik tombol Create Ticket");
        ticketPage.clickCreateTicketButton();

        test.info("Mengisi tiket: " + title);
        ticketPage.fillTicketIssue(title, desc);

        if (ticketType.equalsIgnoreCase("Private")) {
            ticketPage.selectPrivateTicket();
        }

        test.info("Klik tombol Submit");
        ticketPage.submitTicket();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(), 'Berhasil Membuat Ticket Issue')]")));
            test.pass("Pesan konfirmasi muncul: Berhasil Membuat Ticket Issue");
        } catch (Exception e) {
            Assert.fail("Gagal! Pesan 'Berhasil Membuat Ticket Issue' tidak muncul di layar.");
        }

        wait.until(ExpectedConditions.urlContains(expectedUrl));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Tiket '" + title + "' URL saat ini: " + currentUrl);

        Assert.assertTrue(currentUrl.contains(expectedUrl),
                "Assertion Gagal! Masih di URL: " + currentUrl);
    }
}
