package UI.Dashboard;

import UI.core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TicketPage extends BasePage {

    public TicketPage(WebDriver driver) {
        super(driver);
    }

    // Locator
    @FindBy(id = "btn-create-ticket")
    private WebElement btnGoToCreateForm;

    @FindBy(id = "input-ticket-title")
    private WebElement txtTitle;

    @FindBy(id = "textarea-ticket-description")
    private WebElement txtDescription;

    @FindBy(id = "checkbox-ticket-public")
    private WebElement radioPublic;

    @FindBy(id = "checkbox-ticket-private")
    private WebElement radioPrivate;

    @FindBy(id = "btn-submit-ticket")
    private WebElement btnSubmit;

    // Actions
    public void clickCreateTicketButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnGoToCreateForm)).click();
    }

    public void fillTicketIssue(String title, String desc) {
        WebElement titleField = wait.until(ExpectedConditions.visibilityOf(txtTitle));
        WebElement descField = wait.until(ExpectedConditions.visibilityOf(txtDescription));

        // JavaScript Executor untuk handling emoticon
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String jsScript =
                "var el = arguments[0], val = arguments[1];" +
                        "if (el.tagName === 'INPUT') {" +
                        "   Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set.call(el, val);" +
                        "} else if (el.tagName === 'TEXTAREA') {" +
                        "   Object.getOwnPropertyDescriptor(window.HTMLTextAreaElement.prototype, 'value').set.call(el, val);" +
                        "} else {" +
                        "   el.innerText = val;" +
                        "}" +
                        "el.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "el.dispatchEvent(new Event('change', { bubbles: true }));";

        js.executeScript(jsScript, titleField, title);
        js.executeScript(jsScript, descField, desc);

        titleField.sendKeys(" ");
        descField.sendKeys(" ");
    }

    public void selectPrivateTicket() {
        wait.until(ExpectedConditions.elementToBeClickable(radioPrivate)).click();
    }

    public void submitTicket() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
    }
}