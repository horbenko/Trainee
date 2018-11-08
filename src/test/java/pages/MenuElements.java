package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MenuElements extends BasePage {

    @FindBy(css = "div[itemprop='itemListElement']")
    private List<WebElement> itemsOfTheCategorie;

    @FindBy(linkText = "Купить")
    private WebElement buyBtn;

    public MenuElements() {
        super();
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getElements() {
        waitIsAllVisible(itemsOfTheCategorie);
        return itemsOfTheCategorie;
    }

    public DialogCartMenu clickBuyBtn() {
        waitIsClickable(buyBtn);
        buyBtn.click();
        return new DialogCartMenu();
    }

    public MenuElements selectRandomProduct() {
        List<WebElement> list = getElements();
        int randomNum = ThreadLocalRandom.current().nextInt(0, list.size());
        list.get(randomNum).click();
        return new MenuElements();
    }

    public String getProductPrice(WebElement element) {
        return element.findElement(By.cssSelector("span.price.pricecat")).getText().replaceAll("\\D+", "");
    }

    public String getProductName(WebElement element) {
        return element.findElement(By.cssSelector("a[itemprop=\"url\"]")).getText();
    }
}
