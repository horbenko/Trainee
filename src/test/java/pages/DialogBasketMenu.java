package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DialogBasketMenu extends BasePage {

    @FindBy(partialLinkText = "< Продолжить покупки")
    private WebElement continueToBuyBtn;

    public void clickContinueToBuyBtn() {
        continueToBuyBtn.click();
    }

}
