package pages;

import config.BaseConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

class BasePage {
    private final int MAX_SECONDS_WAIT = 10;
    final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "img.img-responsive")
    private WebElement homeLogo;

    BasePage() {
        driver = BaseConfig.getDriver();
        wait = new WebDriverWait(driver, MAX_SECONDS_WAIT);
        PageFactory.initElements(driver, this);
    }

    public void gotoHomePage() {
        waitIsClickable(homeLogo);
        homeLogo.click();
    }

    void waitIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    void waitIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    void waitIsAllVisible(List<WebElement> list) {
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    void waitForUrl(String url) {
        wait.until(ExpectedConditions.urlContains(url));
    }

    boolean waitIsToBeTextPresent(WebElement element, String str) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, str));
    }


}
