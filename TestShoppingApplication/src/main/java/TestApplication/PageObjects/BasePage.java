package TestApplication.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestApplication.AbstractComponents.ConfigProperties;
import TestApplication.AbstractComponents.DriverManager;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);

    }

    @FindBy(xpath = "//button[@routerlink=\"/dashboard/cart\"]")
    WebElement cartMenu;

    @FindBy(xpath = "//button[@routerlink=\"/dashboard/myorders\"]")
    WebElement ordersHistoryMenu;

    @FindBy(xpath = "//button[@routerlink=\"/dashboard/home\"]")
    WebElement homeMenu;

    @FindBy(xpath = "(//button[@class=\"btn btn-custom\"])[4]")
    WebElement signOutButton;

    public void openApplication() {
        driver = DriverManager.initializeDriver();
        driver.get(ConfigProperties.PAGE_URL);
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    // Wait helpers
    public WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForVisible(WebElement locator) {
        return wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public WebElement waitForClickable(WebElement locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
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

    public void waitForPageLoad(String endPoint) {
        wait.until(ExpectedConditions.urlContains(endPoint));
        waitForSpinnerToDisappear();
    }

    // Basic actions
    public void click(WebElement locator) {
        waitForAngularOverlays();
        waitForClickable(locator).click();
    }

    public void click(By locator) {
        waitForAngularOverlays();
        waitForClickable(locator).click();
    }

    public void type(WebElement locator, String text) {
        WebElement el = waitForVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    public String getText(WebElement locator) {
        return waitForVisible(locator).getText();
    }

    public String getAttribute(WebElement locator, String attribute) {
        return waitForVisible(locator).getAttribute(attribute);
    }

    public void hover(WebElement locator) {
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
        click(cartMenu);
        return new CartPage(driver);
    }

    public ViewOrdersHistoryPage OrdersHistoryMenu() {
        click(ordersHistoryMenu);
        return new ViewOrdersHistoryPage(driver);
    }

    public HomePage HomeMenu() {
        click(homeMenu);
        return new HomePage(driver);
    }

    public LoginPage signOutMenu() {
        click(signOutButton);
        return new LoginPage(driver);
    }

}
