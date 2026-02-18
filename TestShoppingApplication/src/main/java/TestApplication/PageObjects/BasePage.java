package TestApplication.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestApplication.AbstractComponents.ConfigProperties;
import TestApplication.AbstractComponents.DriverManager;

public abstract class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void openApplication() {
        driver = DriverManager.initializeDriver();
        driver.get(ConfigProperties.PAGE_URL);
    }

    // Wait helpers
    public WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForSpinnerToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".ngx-spinner-overlay")));
    }

    public void waitForAngularOverlays() {
        waitForInvisibility(By.cssSelector(".ta-backdrop"));
        waitForSpinnerToDisappear();
    }

    // Basic actions
    public void click(By locator) {
        waitForAngularOverlays();
        waitForClickable(locator).click();
    }

    public void type(By locator, String text) {
        WebElement el = waitForVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    public String getText(By locator) {
        return waitForVisible(locator).getText();
    }

    public String getAttribute(By locator, String attribute) {
        return waitForPresence(locator).getAttribute(attribute);
    }

    public void hover(By locator) {
        WebElement el = waitForVisible(locator);
        actions.moveToElement(el).perform();
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    // Allow adjusting the explicit wait timeout
    public void setWaitTimeout(long seconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    // Navigation methods
    public CartPage CartMenu() {
        click(By.xpath("//button[@routerlink=\"/dashboard/cart\"]"));
        return new CartPage(driver);
    }

    public ViewOrdersHistoryPage OrdersHistoryMenu() {
        click(By.xpath("//button[@routerlink=\"/dashboard/myorders\"]"));
        return new ViewOrdersHistoryPage(driver);
    }

    public HomePage HomeMenu() {
        click(By.xpath("//button[@routerlink=\"/dashboard/home\"]"));
        return new HomePage(driver);
    }

    public void signOutMenu() {
        click(By.xpath("(//button[@class=\"btn btn-custom\"])[4]"));
    }

}
