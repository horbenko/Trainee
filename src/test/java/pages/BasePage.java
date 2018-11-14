package pages;

import config.BaseConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static config.BaseConfig.getDriver;

public class BasePage {
    private final int MAX_SECONDS_WAIT = 10;
    public final WebDriver driver;
    public final WebDriverWait wait;
    private final String HOME_PAGE_URL = "https://smt.ua";

    @FindBy(css = "#logo")
    private WebElement homeLogo;

    public BasePage() {
        driver = getDriver();
        wait = new WebDriverWait(driver, MAX_SECONDS_WAIT);
        PageFactory.initElements(driver, this);
    }

    public HomePage clickHomePage() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#cart_modal > div")));
        waitIsClickable(homeLogo);
        homeLogo.click();
        return new HomePage();
    }

    public BasePage clickOnLinkByText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
        return new BasePage();
    }

    public void getHomePage() {
        driver.get(HOME_PAGE_URL);
    }

    public String getPageTitle() {
        return driver.getTitle();
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
