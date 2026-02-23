package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderSuccessPage extends BasePage {
    public OrderSuccessPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[normalize-space()='Orders History Page']")
    WebElement ordersHistoryLink;

    public ViewOrdersHistoryPage viewOrdersHistory() {
        click(ordersHistoryLink);
        return new ViewOrdersHistoryPage(driver);
    }
}
