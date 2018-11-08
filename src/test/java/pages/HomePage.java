package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(linkText = "Войти")
    private WebElement singInBtn;

    @FindBy(css = "button.icon-cart")
    private WebElement cartBtn;

    @FindBy(className = "categories-block-a")
    private List<WebElement> itemsOfMainMenuCategories;

    public HomePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public LoginDialog clickSingInBtn() {
        waitIsClickable(singInBtn);
        singInBtn.click();
        return new LoginDialog();
    }

    public ProductCart clickCartBtn() {
        waitIsClickable(cartBtn);
        cartBtn.click();
        return new ProductCart();
    }

    public List getListOfMainMenuCategories() {
        waitIsAllVisible(itemsOfMainMenuCategories);
        return itemsOfMainMenuCategories;
    }

    public MenuElements selectRandomMainMenuCategory() {
        int exclude = 6;  // Exclude temporary empty category
        List<WebElement> mainMenuCategories = getListOfMainMenuCategories();
        int randomNum = ThreadLocalRandom.current().nextInt(0, mainMenuCategories.size());
        if (randomNum == exclude) selectRandomMainMenuCategory();
        mainMenuCategories.get(randomNum).click();
        return new MenuElements();
    }

}
