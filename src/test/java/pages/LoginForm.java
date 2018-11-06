package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static config.SiteConfig.*;

public class LoginForm extends BasePage {

    @FindBy(id = "input-name")
    private WebElement inputName;

    @FindBy(id = "input-password")
    private WebElement inputPassword;

    @FindBy(id = "login_but")
    private WebElement loginBtn;

    public void setUsername() {
        waitIsVisible(inputName);
        inputName.sendKeys(Config.getUsername());
    }

    public void setPassword() {
        waitIsVisible(inputPassword);
        inputPassword.sendKeys(Config.getPassword());
    }

    public void clickLoginBtn() {
        waitIsClickable(loginBtn);
        loginBtn.click();
    }
}
