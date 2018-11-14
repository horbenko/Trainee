package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountArea extends BasePage {

    @FindBy(css = "a.icon-lock")
    private WebElement iconLockBtn;

    @FindBy(className = "cl_name")
    private WebElement accountFullName;

    private final String accountFullNameCheckStr = "Фамилия Имя Отчество";

    public AccountArea() {
        super();
        PageFactory.initElements(driver, this);
    }

    public boolean isAccountArea() {
       return (driver.getTitle().equals("Личный Кабинет"));
    }

    public boolean isCorrectAccountFullName() {
        waitIsVisible(accountFullName);
        return (accountFullName).getText().equals(accountFullNameCheckStr);
    }

    public boolean isExitTextPresent() {
        return waitIsToBeTextPresent(iconLockBtn, "Выйти");
    }

    @Override
    public String toString() {
        return "AccountArea{" +
                ", iconLockBtn=" + iconLockBtn +
                ", accountFullName=" + accountFullName +
                ", accountFullNameCheckStr='" + accountFullNameCheckStr + '\'' +
                '}';
    }
}
