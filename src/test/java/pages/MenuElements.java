package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MenuElements extends BasePage {

    @FindBy(css = "div[itemprop='itemListElement']")
    private List<WebElement> itemsOfTheCategorie;

    @FindBy(linkText = "Купить")
    private WebElement buyBtn;

    public List<WebElement> getElements() {
        waitIsAllVisible(itemsOfTheCategorie);
        return itemsOfTheCategorie;
    }

    public DialogCartMenu clickBuyBtn() {
        waitIsClickable(buyBtn);
        buyBtn.click();
        return new DialogCartMenu();
    }

    public ProductCarde selectRandomProduct() {
        List<WebElement> list = getElements();
        int randomNum = ThreadLocalRandom.current().nextInt(0, list.size());
        list.get(randomNum).click();
        return new ProductCarde();
    }
}
