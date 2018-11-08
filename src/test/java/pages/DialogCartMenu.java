package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DialogCartMenu extends BasePage {

    @FindBy(className = "continue_buy")
    private WebElement continueToBuyBtn;

    public void clickContinueToBuyBtn() {
        waitIsClickable(continueToBuyBtn);
        continueToBuyBtn.click();
    }

}
