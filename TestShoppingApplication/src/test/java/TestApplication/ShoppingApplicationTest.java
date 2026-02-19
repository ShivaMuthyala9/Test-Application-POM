package TestApplication;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestApplication.AbstractComponents.DriverManager;
import TestApplication.PageObjects.CartPage;
import TestApplication.PageObjects.CheckoutPage;
import TestApplication.PageObjects.HomePage;
import TestApplication.PageObjects.LoginPage;
import TestApplication.PageObjects.OrderSuccessPage;
import TestApplication.PageObjects.ViewOrdersHistoryPage;

public class ShoppingApplicationTest {
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

    @Test(priority = 1, description = "Verify user can login with valid credentials")
    public void testUserLogin() {
        homePage = loginPage.loginToApplication();
        homePage.waitForPageLoad("dashboard");
        Assert.assertEquals(homePage.homePageURL, homePage.getPageURL(), "User Login failed");
        System.out.println("User login successful");
    }

    @Test(priority = 2, description = "Verify user can add product to cart by product name")
    public void testAddProductToCart() {
        homePage = loginPage.loginToApplication();
        String productName = "iphone 13 pro"; // Adjust based on actual product names
        homePage.addToCartByProductName(productName);
        System.out.println("Product added to cart: " + productName);
        homePage.waitForSpinnerToDisappear();
    }

    @Test(priority = 3, description = "Verify user can filter products by price")
    public void testFilterByPrice() {
        homePage = loginPage.loginToApplication();
        homePage.setPriceFilter(10000, 50000);
        Assert.assertEquals(homePage.getPriceFilterValues()[0], "10000", "Min price filter value mismatch");
        Assert.assertEquals(homePage.getPriceFilterValues()[1], "50000", "Max price filter value mismatch");
        System.out.println("Price filter applied successfully");
    }

    @Test(priority = 4, description = "Verify user can complete checkout and place order")
    public void testCompleteCheckoutFlow() {
        homePage = loginPage.loginToApplication();

        String productName = "iphone 13 pro";
        homePage.addToCartByProductName(productName);

        CartPage cartPage = homePage.CartMenu();

        CheckoutPage checkoutPage = cartPage.checkOut();
        checkoutPage.fillCardDetails();
        checkoutPage.fillShippingDetails();
        checkoutPage.waitForAngularOverlays();

        OrderSuccessPage orderSuccessPage = checkoutPage.placeOrder();
        Assert.assertNotNull(orderSuccessPage, "Order success page should load after placing order");
        System.out.println("Order placed successfully");
    }

    @Test(priority = 5, description = "Verify user can view order history")
    public void testViewOrderHistory() {
        homePage = loginPage.loginToApplication();

        String productName = "iphone 13 pro";
        homePage.addToCartByProductName(productName);

        CartPage cartPage = homePage.CartMenu();

        CheckoutPage checkoutPage = cartPage.checkOut();
        checkoutPage.fillCardDetails();
        checkoutPage.fillShippingDetails();
        checkoutPage.waitForAngularOverlays();
        OrderSuccessPage orderSuccessPage = checkoutPage.placeOrder();

        ViewOrdersHistoryPage ordersPage = orderSuccessPage.viewOrdersHistory();
        Assert.assertNotNull(ordersPage, "Orders history page should load");
        System.out.println("Order history page accessed successfully");
    }

    @Test(priority = 6, description = "Verify orders are displayed in order history")
    public void testOrderHistoryNotEmpty() {
        homePage = loginPage.loginToApplication();

        homePage.addToCartByProductName("iphone 13 pro");
        CartPage cartPage = homePage.CartMenu();
        CheckoutPage checkoutPage = cartPage.checkOut();
        checkoutPage.fillCardDetails();
        checkoutPage.fillShippingDetails();
        checkoutPage.waitForAngularOverlays();
        OrderSuccessPage orderSuccessPage = checkoutPage.placeOrder();

        ViewOrdersHistoryPage ordersPage = orderSuccessPage.viewOrdersHistory();
        ordersPage.waitForPageLoad("myorders");
        java.util.List<String> orders = ordersPage.getOrderIds();

        Assert.assertNotNull(orders, "Orders list should not be null");
        Assert.assertTrue(orders.size() > 0, "At least one order should be present");
        System.out.println("Order history contains " + orders.size() + " order(s)");
    }
}
