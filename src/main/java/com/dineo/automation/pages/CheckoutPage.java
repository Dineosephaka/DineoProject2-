package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dineo.automation.base.BasePage;

public class CheckoutPage extends BasePage {

    private By placeOrderButton =
            By.xpath("//a[contains(text(),'Place Order')]");

    private By orderConfirmation =
            By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void placeOrder() {

        click(placeOrderButton);
    }

    public boolean isOrderConfirmed() {

        return isDisplayed(orderConfirmation);
    }
}