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
    protected WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected boolean waitForInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Basic actions
    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement el = waitForVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return waitForVisible(element).getText();
    }

    protected String getAttribute(By locator, String attribute) {
        return waitForPresence(locator).getAttribute(attribute);
    }

    protected void hover(By locator) {
        WebElement el = waitForVisible(locator);
        actions.moveToElement(el).perform();
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    // Allow adjusting the explicit wait timeout
    protected void setWaitTimeout(long seconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
}
