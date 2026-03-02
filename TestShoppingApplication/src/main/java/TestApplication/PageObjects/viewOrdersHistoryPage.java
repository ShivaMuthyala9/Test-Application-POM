package TestApplication.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewOrdersHistoryPage extends BasePage {
    public ViewOrdersHistoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table[contains(@class,'table-hover')]/tbody/tr")
    List<WebElement> ordersLocators;

    public List<String> getOrderIds() {
        List<WebElement> orderElements = ordersLocators;
        List<String> orderIDList = new ArrayList<>();
        for (WebElement element : orderElements) {
            orderIDList.add(element.getText());
        }
        return orderIDList;
    }
}
