package com.dineo.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dineo.automation.pages.CartPage;
import com.dineo.automation.pages.ProductDetailsPage;
import com.dineo.automation.pages.ProductsPage;

public class CartTest extends BaseTest {

    @Test
    public void verifyShoppingCart() {

        // Navigate to Products
        ProductsPage productsPage = homePage.clickProducts();

        // ==========================================
        // PRODUCT 1 - BLUE TOP
        // ==========================================

        ProductDetailsPage blueTop =
                productsPage.clickProduct("Blue Top");

        blueTop.addToCart();

        // Go directly back to Products
        driver.get("https://automationexercise.com/products");

        productsPage = new ProductsPage(driver);

        // ==========================================
        // PRODUCT 2 - MEN TSHIRT
        // ==========================================

        ProductDetailsPage menTshirt =
                productsPage.clickProduct("Men Tshirt");

        menTshirt.addToCart();

        // Go directly back to Products
        driver.get("https://automationexercise.com/products");

        productsPage = new ProductsPage(driver);

        // ==========================================
        // PRODUCT 3 - LONG MAXI TULLE
        // ==========================================

        ProductDetailsPage longMaxiTulle =
                productsPage.clickProduct(
                        "Long Maxi Tulle Fancy Dress Up Outfits -Pink"
                );

        longMaxiTulle.addToCart();

        // Open Cart directly
        driver.get("https://automationexercise.com/view_cart");

        CartPage cartPage = new CartPage(driver);

        // ==========================================
        // VERIFY CART
        // ==========================================

        Assert.assertTrue(
                cartPage.isCartDisplayed(),
                "Shopping cart should be displayed."
        );

        // Verify all three products
        Assert.assertTrue(
                cartPage.isProductInCart("Blue Top"),
                "Blue Top should be in the cart."
        );

        Assert.assertTrue(
                cartPage.isProductInCart("Men Tshirt"),
                "Men Tshirt should be in the cart."
        );

        Assert.assertTrue(
                cartPage.isProductInCart(
                        "Long Maxi Tulle Fancy Dress Up Outfits -Pink"
                ),
                "Long Maxi Tulle should be in the cart."
        );

        // ==========================================
        // VERIFY QUANTITIES
        // ==========================================

        Assert.assertEquals(
                cartPage.getProductQuantity("Blue Top"),
                "1",
                "Blue Top quantity should be 1."
        );

        Assert.assertEquals(
                cartPage.getProductQuantity("Men Tshirt"),
                "1",
                "Men Tshirt quantity should be 1."
        );

        Assert.assertEquals(
                cartPage.getProductQuantity(
                        "Long Maxi Tulle Fancy Dress Up Outfits -Pink"
                ),
                "1",
                "Long Maxi Tulle quantity should be 1."
        );

        // ==========================================
        // VERIFY PRICES
        // ==========================================

        Assert.assertFalse(
                cartPage.getProductPrice("Blue Top").isEmpty(),
                "Blue Top price should be displayed."
        );

        Assert.assertFalse(
                cartPage.getProductPrice("Men Tshirt").isEmpty(),
                "Men Tshirt price should be displayed."
        );

        Assert.assertFalse(
                cartPage.getProductPrice(
                        "Long Maxi Tulle Fancy Dress Up Outfits -Pink"
                ).isEmpty(),
                "Long Maxi Tulle price should be displayed."
        );

        // ==========================================
        // REMOVE MEN TSHIRT
        // ==========================================

        cartPage.removeProduct("Men Tshirt");

        Assert.assertTrue(
                cartPage.isProductRemoved("Men Tshirt"),
                "Men Tshirt should be removed from the cart."
        );

        // Verify remaining products
        Assert.assertTrue(
                cartPage.isProductInCart("Blue Top"),
                "Blue Top should remain in the cart."
        );

        Assert.assertTrue(
                cartPage.isProductInCart(
                        "Long Maxi Tulle Fancy Dress Up Outfits -Pink"
                ),
                "Long Maxi Tulle should remain in the cart."
        );

        System.out.println(
                "Shopping cart verification passed."
        );
    }
}