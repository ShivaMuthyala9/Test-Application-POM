package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderSuccessPage {
    WebDriver driver;

    public OrderSuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    public viewOrdersHistoryPage viewOrdersHistory() {
        driver.findElement(By.xpath("//label[normalize-space()='Orders History Page']")).click();
        return new viewOrdersHistoryPage(driver);
    }
}
