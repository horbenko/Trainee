package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private static WebElement element;
    private final By loginBtn = By.cssSelector("a.icon-lock");

    public WebElement login() {
        waitIsClickable(loginBtn);
        driver.findElement(loginBtn);
        return element;
    }

}
