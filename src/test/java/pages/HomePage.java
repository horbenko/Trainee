package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(linkText = "Войти")
    private WebElement singInBtn;

    @FindBy(css = "img.img-responsive")
    private WebElement homeLogo;

    public void clickSingInBtn() {
        waitIsClickable(singInBtn);
        singInBtn.click();
    }

    public void goHomePage() {
        waitIsClickable(homeLogo);
        homeLogo.click();
    }

}
