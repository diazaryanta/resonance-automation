package UI.tests;

import UI.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import UI.Login.LoginPage;
import UI.Dashboard.TicketPage;

public class TicketTest extends BaseTest {

    @DataProvider(name = "ticketData")
    public Object[][] getTicketData() {
        return new Object[][] {
                // { Judul, Deskripsi, Tipe Tiket, Expected URL }
                {"lorem", "lorem ipsum", "Public", "ticket"},
                /*{"Describe Null", "  ", "Public", "new"}, // Describe null Bug*/
                /*{" ", "  ", "Public", "new"}, // Blank Space Bug*/
                /*{"\ud83d\ude2d", "\u26d4", "Public", "new"}, // Emoticon Bug
                {"«‘“π…æ¬˚ºåß≤ºåß¡`¡™£¢•¶§∞§º", "Bug", "Public", "new"}, // Input Validation Bug
                {"Success Ticket", "Ini adalah tiket private", "Private", "ticket"}*/
        };
    }

    @Test(dataProvider = "ticketData")
    public void testCreateNewTicket(String title, String desc, String ticketType, String expectedUrl) {
        LoginPage loginPage = new LoginPage(driver);
        TicketPage ticketPage = new TicketPage(driver);

        loginPage.do_login("admin", "admin");

        test.info("Klik tombol Create Ticket");
        ticketPage.clickCreateTicketButton();

        test.info("Mengisi tiket dengan judul: '" + title + "' | Tipe: " + ticketType);
        ticketPage.fillTicketIssue(title, desc);

        if (ticketType.equalsIgnoreCase("Private")) {
            ticketPage.selectPrivateTicket();
        }

        test.info("Klik tombol Submit");
        ticketPage.submitTicket();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Tiket '" + title + "' URL saat ini: " + currentUrl);

        Assert.assertTrue(currentUrl.contains(expectedUrl),
                "Assertion Gagal! Diharapkan pindah ke '" + expectedUrl + "' tapi nyangkut di: " + currentUrl);
    }
}