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

    private void setUsername() {
        waitIsVisible(inputName);
        inputName.sendKeys(Config.getUsername());
    }

    private void setPassword() {
        waitIsVisible(inputPassword);
        inputPassword.sendKeys(Config.getPassword());
    }

    private void clickLoginBtn() {
        waitIsClickable(loginBtn);
        loginBtn.click();
    }

    public void loginUser() {
        setUsername();
        setPassword();
        clickLoginBtn();
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "inputName=" + inputName +
                ", loginBtn=" + loginBtn +
                '}';
    }
}
