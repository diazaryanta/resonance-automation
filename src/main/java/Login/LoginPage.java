package Login;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "input-email-login")
    private WebElement usernameTextField;

    @FindBy(id = "input-password-login")
    private WebElement passwordTextField;

    @FindBy(id = "btn-login")
    private WebElement buttonLogin;

    public void do_login(String email, String password) {
        usernameTextField.sendKeys(email);
        passwordTextField.sendKeys(password);
        buttonLogin.click();
    }
}