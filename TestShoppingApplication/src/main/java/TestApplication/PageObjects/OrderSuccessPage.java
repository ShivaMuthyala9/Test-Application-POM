package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderSuccessPage extends BasePage {
    WebDriver driver;

    public OrderSuccessPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public viewOrdersHistoryPage viewOrdersHistory() {
        click(By.xpath("//label[normalize-space()='Orders History Page']"));
        return new viewOrdersHistoryPage(driver);
    }
}
