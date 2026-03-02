package TestApplication;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestApplication.PageObjects.CartPage;
import TestApplication.PageObjects.CheckoutPage;
import TestApplication.PageObjects.HomePage;
import TestApplication.PageObjects.LoginPage;
import TestApplication.PageObjects.OrderSuccessPage;

public class CheckoutTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void prepare() {
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Verify user can complete checkout and place order")
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
}
