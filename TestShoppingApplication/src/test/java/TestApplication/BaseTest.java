package TestApplication;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import TestApplication.AbstractComponents.DriverManager;

public abstract class BaseTest {
    protected WebDriver driver;

    @Parameters({ "baseUrl" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(String baseUrl) {
        // create new browser instance for every test
        driver = DriverManager.initializeDriver();
        // navigate to application
        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // ensure driver is quit even if test failed or setup threw an exception
        if (driver != null) {
            DriverManager.quitDriver(driver);
            driver = null;
        }
    }
}
