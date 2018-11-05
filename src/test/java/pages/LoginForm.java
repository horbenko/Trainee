package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static config.SiteConfig.*;

public class LoginForm extends BasePage {
    public static WebElement element;
    private final By loginField = By.id("input-name");
    private final By passField = By.id("input-password");
    private final By loginBtn = By.id("login_but");

    public void setUsername() {
        waitIsVisible(loginField);
        driver.findElement(loginField).sendKeys(Config.getUsername());
    }

    public void setPassword() {
        waitIsVisible(passField);
        driver.findElement(passField).sendKeys(Config.getPassword());
    }

    public void clickLogin() {
        waitIsClickable(loginBtn);
        driver.findElement(loginBtn).click();
    }
}
