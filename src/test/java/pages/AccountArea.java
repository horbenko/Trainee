package pages;

import org.openqa.selenium.By;

public class AccountArea extends BasePage {
    private final By textField = By.className("category");
    private final String checkStr = "Личный кабинет";
    private final By iconLockBtn = By.cssSelector("a.icon-lock");
    private final By accountFullName = By.className("cl_name");
    private final String fullNameStr = "Фамилия Имя Отчество";

    public boolean isAccountArea() {
       waitIsVisible(textField);
       return (driver.findElement(textField).getText().equals(checkStr));
    }

    public boolean isCorrectAccountFullName() {
        waitIsVisible(accountFullName);
        return (driver.findElement(accountFullName).getText().equals(fullNameStr));
    }

    public String getIconLockText() {
        waitIsVisible(iconLockBtn);
        return driver.findElement(iconLockBtn).getText();
    }
}
