package TestApplication.PageObjects;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ViewOrdersHistoryPage extends BasePage {
    public ViewOrdersHistoryPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getOrderIds() {
        List<WebElement> orderElements = findElements(By.xpath("//table[contains(@class,'table-hover')]/tbody/tr"));
        List<String> orderIDList = new ArrayList<>();
        for (WebElement element : orderElements) {
            orderIDList.add(element.getText());
        }
        return orderIDList;
    }
}
