package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginDialog extends BasePage {

    @FindBy(id = "input-name")
    private WebElement inputName;

    @FindBy(id = "input-password")
    private WebElement inputPassword;

    @FindBy(id = "login_but")
    private WebElement loginBtn;

    public LoginDialog() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String username) {
        waitIsVisible(inputName);
        inputName.sendKeys(username);
    }

    public void setPassword(String password) {
        waitIsVisible(inputPassword);
        inputPassword.sendKeys(password);
    }

    public void clickLoginBtn() {
        waitIsClickable(loginBtn);
        loginBtn.click();
    }

    public boolean loginUser(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginBtn();
        return isLoginSuccess();
    }

    private boolean isLoginSuccess() {
        String URL_MATCH_LOGIN_SUCCESS = "/index.php?route=account/simple";
        try {
            waitForUrl(URL_MATCH_LOGIN_SUCCESS);
            return true;
        }
        catch (TimeoutException exception) {
            gotoHomePage();
            return false;
        }
    }

    @Override
    public String toString() {
        return "LoginDialog{" +
                "inputName=" + inputName +
                ", loginBtn=" + loginBtn +
                '}';
    }
}
