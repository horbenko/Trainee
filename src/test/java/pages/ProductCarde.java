package pages;

import org.jsoup.Connection;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductCarde extends BasePage {

    @FindBy(linkText = "Купить")
    private WebElement buyBtn;

    public DialogCartMenu clickBuyBtn() {
        waitIsClickable(buyBtn);
        buyBtn.click();
        return new DialogCartMenu();
    }
}
