package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dineo.automation.base.BasePage;

public class CartPage extends BasePage {

    private By cartTable =
            By.id("cart_info_table");

    private By checkoutButton =
            By.xpath("//a[contains(text(),'Proceed To Checkout')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartDisplayed() {

        return isDisplayed(cartTable);
    }

    public CheckoutPage proceedToCheckout() {

        click(checkoutButton);

        return new CheckoutPage(driver);
    }
}