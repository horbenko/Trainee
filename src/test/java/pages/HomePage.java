package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(linkText = "Войти")
    private WebElement singInBtn;

    @FindBy(css = "button.icon-cart")
    private WebElement cartBtn;

    @FindBy(className = "categories-block-a")
    private List<WebElement> itemsOfMainMenuCategories;

    public LoginDialog clickSingInBtn() {
        waitIsClickable(singInBtn);
        singInBtn.click();
        return new LoginDialog();
    }

    public void clickCartBtn() {
        cartBtn.click();
    }

    public List getListOfMainMenuCategories() {
        waitIsAllVisible(itemsOfMainMenuCategories);
        return itemsOfMainMenuCategories;
    }

    public MenuElements selectRandomFromMainMenuCategories() {
        List<WebElement> mainMenuCategories = getListOfMainMenuCategories();
        int randomNum = ThreadLocalRandom.current().nextInt(0, mainMenuCategories.size());
        mainMenuCategories.get(randomNum).click();
        return new MenuElements();
    }


}
