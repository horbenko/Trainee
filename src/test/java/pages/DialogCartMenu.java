package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DialogCartMenu extends BasePage {

    @FindBy(className = "continue_buy")
    private WebElement continueToBuyBtn;

    public DialogCartMenu() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void clickContinueToBuyBtn() {
        waitIsClickable(continueToBuyBtn);
        continueToBuyBtn.click();
    }

}
