package TestApplication.PageObjects;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class viewOrdersHistoryPage extends BasePage {
    WebDriver driver;

    public viewOrdersHistoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public List<String> getOrderIds() {
        List<WebElement> orderElements = findElements(By.xpath("//table[contains(@class,'table-hover')]/tbody/tr"));
        List<String> orderIDList = new ArrayList<>();
        for (WebElement element : orderElements) {
            orderIDList.add(getText(element));
        }
        return orderIDList;
    }
}
