package com.dineo.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dineo.automation.pages.CartPage;
import com.dineo.automation.pages.CheckoutPage;
import com.dineo.automation.pages.ProductDetailsPage;
import com.dineo.automation.pages.ProductsPage;

public class CheckoutTest extends BaseTest {

    @Test
    public void verifyCheckoutAndOrderPlacement() {

        // ==========================================
        // SCENARIO 5 - CHECKOUT
        // ==========================================

        // Customer is assumed to already be logged in.

        // ==========================================
        // 1. Add Products
        // ==========================================

        ProductsPage productsPage =
                homePage.clickProducts();

        // Product 1 - Blue Top
        ProductDetailsPage blueTop =
                productsPage.clickProduct("Blue Top");

        blueTop.addToCart();

        // Return to Products
        driver.get(
                "https://automationexercise.com/products"
        );

        productsPage =
                new ProductsPage(driver);

        // Product 2 - Men Tshirt
        ProductDetailsPage menTshirt =
                productsPage.clickProduct("Men Tshirt");

        menTshirt.addToCart();

        // Return to Products
        driver.get(
                "https://automationexercise.com/products"
        );

        productsPage =
                new ProductsPage(driver);

        // Product 3 - Long Maxi Tulle
        ProductDetailsPage longMaxiTulle =
                productsPage.clickProduct(
                        "Long Maxi Tulle Fancy Dress Up Outfits -Pink"
                );

        longMaxiTulle.addToCart();

        // ==========================================
        // 2. Navigate to Cart
        // ==========================================

        driver.get(
                "https://automationexercise.com/view_cart"
        );

        CartPage cartPage =
                new CartPage(driver);

        Assert.assertTrue(
                cartPage.isCartDisplayed(),
                "Shopping cart should be displayed."
        );

        // ==========================================
        // 3. Proceed to Checkout
        // ==========================================

        cartPage.proceedToCheckout();

        CheckoutPage checkoutPage =
                new CheckoutPage(driver);

        Assert.assertTrue(
                checkoutPage.isCheckoutDisplayed(),
                "Checkout page should be displayed."
        );

        // ==========================================
        // 4. Verify Address
        // ==========================================

        Assert.assertTrue(
                checkoutPage.isDeliveryAddressDisplayed(),
                "Delivery address should be displayed."
        );

        Assert.assertTrue(
                checkoutPage.isBillingAddressDisplayed(),
                "Billing address should be displayed."
        );

        // ==========================================
        // 5. Verify Order Details
        // ==========================================

        Assert.assertTrue(
                checkoutPage.isOrderDetailsDisplayed(),
                "Order details should be displayed."
        );

        // ==========================================
        // Enter Order Comment
        // ==========================================

        checkoutPage.enterOrderComment(
                "Please process this automated test order."
        );

        // ==========================================
        // 6. Enter Payment Information
        // ==========================================

        checkoutPage.placeOrder();

        checkoutPage.enterPaymentDetails(
                "Dineo Tester",
                "4111111111111111",
                "123",
                "12",
                "2030"
        );

        // ==========================================
        // 7. Place Order
        // ==========================================

        checkoutPage.payAndConfirmOrder();

        // ==========================================
        // 8. Verify Order Confirmation
        // ==========================================

        Assert.assertTrue(
                checkoutPage.isOrderConfirmed(),
                "Order should be confirmed successfully."
        );

        System.out.println(
                "Scenario 5 - Checkout completed successfully."
        );
    }
}