package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenuElements extends BasePage {

    @FindBy(css = "div[itemprop='itemListElement']")
    private List<WebElement> itemsOfTheCategorie;

    @FindBy(linkText = "Купить")
    private WebElement buyBtn;

    public List<WebElement> getElements() {
        waitIsAllVisible(itemsOfTheCategorie);
        return itemsOfTheCategorie;
    }

    public DialogBasketMenu addToCart() {
        buyBtn.click();
        return new DialogBasketMenu();
    }
}
