package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductCart extends BasePage {

    @FindBy(xpath = "//table[contains(@class, \"table-striped\")]/tbody/tr[not (@class)]")
    public List<WebElement> listOfElements;

    @FindBy(css = "div.right > div > strong")
    private WebElement totalPriceValue;

    public ProductCart() {
        super();
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getListOfElementsInCart() {
        waitIsAllVisible(listOfElements);
        return listOfElements;
    }

    public String getTotalPriceValue() {
        //waitIsVisible(totalPriceValue);
        return totalPriceValue.getText().replaceAll("\\D+","");
    }

    public String getProductName(WebElement element) {
        return element.findElement(By.cssSelector("text-left name")).getText();
    }
}
