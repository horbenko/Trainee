package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
     private final By singInBtn = By.cssSelector("a.icon-lock");

    public void clickSingIn() {
        waitIsClickable(singInBtn);
        driver.findElement(singInBtn).click();
    }

}
