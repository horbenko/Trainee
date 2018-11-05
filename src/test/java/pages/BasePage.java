package pages;

import config.BaseConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasePage {
    private final int MAX_SECONDS_WAIT = 30;
    final WebDriver driver;
    private final WebDriverWait wait;

    BasePage() {
        driver = BaseConfig.getDriver();
        wait = new WebDriverWait(driver, MAX_SECONDS_WAIT);
    }

    void waitIsClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    void waitIsVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

}
