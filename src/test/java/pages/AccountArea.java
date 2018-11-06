package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountArea extends BasePage {

    @FindBy(className = "category")
    private WebElement textField;

    @FindBy(css = "a.icon-lock")
    private WebElement iconLockBtn;

    @FindBy(className = "cl_name")
    private WebElement accountFullName;

    private final String checkStr = "Личный кабинет";
    private final String fullNameStr = "Фамилия Имя Отчество";

    public boolean isAccountArea() {
       waitIsVisible(textField);
       return (textField.getText().equals(checkStr));
    }

    public boolean isCorrectAccountFullName() {
        waitIsVisible(accountFullName);
        return (accountFullName).getText().equals(fullNameStr);
    }

    public boolean isExitTextVisible() {
        return waitIsToBeTextPresent(iconLockBtn, "Выйти");
    }
}
