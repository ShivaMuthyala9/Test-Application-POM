package TestApplication;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestApplication.AbstractComponents.DriverManager;
import TestApplication.PageObjects.HomePage;
import TestApplication.PageObjects.LoginPage;

public class ProductTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.initializeDriver();
        loginPage = new LoginPage(driver);
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            DriverManager.quitDriver(driver);
        }
    }

    @Test(description = "Verify user can add product to cart by product name")
    public void testAddProductToCart() {
        homePage = loginPage.loginToApplication();
        String productName = "iphone 13 pro";
        homePage.addToCartByProductName(productName);
        System.out.println("Product added to cart: " + productName);
        homePage.waitForSpinnerToDisappear();
    }

    @Test(description = "Verify user can filter products by price")
    public void testFilterByPrice() {
        homePage = loginPage.loginToApplication();
        homePage.setPriceFilter(10000, 50000);
        org.testng.Assert.assertEquals(homePage.getPriceFilterValues()[0], "10000", "Min price filter value mismatch");
        org.testng.Assert.assertEquals(homePage.getPriceFilterValues()[1], "50000", "Max price filter value mismatch");
        System.out.println("Price filter applied successfully");
    }
}
