package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderSuccessPage extends BasePage {
    public OrderSuccessPage(WebDriver driver) {
        super(driver);
    }

    public ViewOrdersHistoryPage viewOrdersHistory() {
        click(By.xpath("//label[normalize-space()='Orders History Page']"));
        return new ViewOrdersHistoryPage(driver);
    }
}
