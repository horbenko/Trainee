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
    private List<WebElement> itemsOfCategoriesBlockMenu;

    @FindBy(partialLinkText = "Асик майнеры (Asic)")
    private WebElement menuItem1;

    @FindBy(partialLinkText = "Майнинг фермы GPU")
    private WebElement menuItem2;

    @FindBy(partialLinkText = "Видеокарты")
    private WebElement menuItem3;

    @FindBy(partialLinkText = "Крипто кошельки")
    private WebElement menuItem4;

    @FindBy(partialLinkText = "Блоки питания для Asic")
    private WebElement menuItem5;

    @FindBy(partialLinkText = "Комплектующие для Asic")
    private WebElement menuItem6;

    @FindBy(partialLinkText = "Игровые ноутбуки")
    private WebElement menuItem7;

    @FindBy(partialLinkText = "Бюджетные ноутбуки")
    private WebElement menuItem8;

    public LoginForm clickSingInBtn() {
        waitIsClickable(singInBtn);
        singInBtn.click();
        return new LoginForm();
    }

    public void clickCartBtn() {
        cartBtn.click();
    }


    public void clickMenuItem1() {
        waitIsClickable(menuItem1);
        menuItem1.click();
    }

    public void clickMenuItem2() {
        waitIsClickable(menuItem2);
        menuItem2.click();
    }

    public void clickMenuItem3() {
        waitIsClickable(menuItem3);
        menuItem3.click();
    }

    public void clickMenuItem4() {
        waitIsClickable(menuItem4);
        menuItem4.click();
    }

    public void clickMenuItem5() {
        waitIsClickable(menuItem5);
        menuItem5.click();
    }

    public void clickMenuItem6() {
        waitIsClickable(menuItem6);
        menuItem6.click();
    }

    public void clickMenuItem7() {
        waitIsClickable(menuItem7);
        menuItem7.click();
    }

    public void clickMenuItem8() {
        waitIsClickable(menuItem8);
        menuItem8.click();
    }

    public List getCategoriesElements() {
        return itemsOfCategoriesBlockMenu;
    }

    public void selectRandomMenuCategory() {
        goHomePage();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 8 + 1);
        switch (randomNum) {
            case 1:
                clickMenuItem1();
                break;
            case 2:
                clickMenuItem2();
                break;
            case 3:
                clickMenuItem3();
                break;
            case 4:
                clickMenuItem4();
                break;
            case 5:
                clickMenuItem5();
                break;
            case 6:
                clickMenuItem6();
                break;
            case 7:
                clickMenuItem7();
                break;
            case 8:
                clickMenuItem8();
                break;
            default: throw new IllegalArgumentException("No such menu category.");
        }
    }

    @Override
    public String toString() {
        return "HomePage{" +
                "singInBtn=" + singInBtn +
                ", itemsOfCategoriesBlockMenu=" + itemsOfCategoriesBlockMenu +
                ", menuItem1=" + menuItem1 +
                ", menuItem2=" + menuItem2 +
                ", menuItem3=" + menuItem3 +
                ", menuItem4=" + menuItem4 +
                ", menuItem5=" + menuItem5 +
                ", menuItem6=" + menuItem6 +
                ", menuItem7=" + menuItem7 +
                ", menuItem8=" + menuItem8 +
                '}';
    }
}
