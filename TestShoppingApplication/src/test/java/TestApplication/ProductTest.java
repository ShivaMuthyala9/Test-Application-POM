package TestApplication;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestApplication.PageObjects.HomePage;
import TestApplication.PageObjects.LoginPage;

public class ProductTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void prepare() {
        loginPage = new LoginPage(driver);
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
