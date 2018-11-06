package pages;

import config.BaseConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasePage {
    private final int MAX_SECONDS_WAIT = 10;
    private final WebDriver driver;
    private final WebDriverWait wait;

    BasePage() {
        driver = BaseConfig.getDriver();
        wait = new WebDriverWait(driver, MAX_SECONDS_WAIT);
        PageFactory.initElements(driver, this);
    }

    void waitIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    void waitIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    boolean waitIsToBeTextPresent(WebElement element, String str) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, str));
    }

}
