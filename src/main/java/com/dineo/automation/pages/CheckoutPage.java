package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dineo.automation.base.BasePage;

public class CheckoutPage extends BasePage {

    // Checkout page
    private final By checkoutTitle =
            By.xpath("//h2[contains(text(),'Address Details')]");

    // Delivery address
    private final By deliveryAddress =
            By.id("address_delivery");

    // Billing address
    private final By billingAddress =
            By.id("address_invoice");

    // Order review
    private final By orderReview =
            By.id("cart_info");

    // Order comment
    private final By orderComment =
            By.name("message");

    // Place order
    private final By placeOrderButton =
            By.xpath("//a[contains(text(),'Place Order')]");

    // Payment page
    private final By nameOnCard =
            By.name("name_on_card");

    private final By cardNumber =
            By.name("card_number");

    private final By cvc =
            By.name("cvc");

    private final By expiryMonth =
            By.name("expiry_month");

    private final By expiryYear =
            By.name("expiry_year");

    private final By payAndConfirmButton =
            By.id("submit");

    // Order confirmation
    private final By orderConfirmation =
            By.xpath(
                    "//p[contains(text(),'Congratulations! Your order has been confirmed!')]"
            );

    public CheckoutPage(WebDriver driver) {
        super(driver);

        waitForElement(checkoutTitle);
    }

    /**
     * Verify that the Checkout page is displayed.
     */
    public boolean isCheckoutDisplayed() {

        return isDisplayed(checkoutTitle);
    }

    /**
     * Verify that the delivery address is displayed.
     */
    public boolean isDeliveryAddressDisplayed() {

        return isDisplayed(deliveryAddress);
    }

    /**
     * Verify that the billing address is displayed.
     */
    public boolean isBillingAddressDisplayed() {

        return isDisplayed(billingAddress);
    }

    /**
     * Verify that the order review section is displayed.
     */
    public boolean isOrderDetailsDisplayed() {

        return isDisplayed(orderReview);
    }

    /**
     * Enter an optional order comment.
     */
    public void enterOrderComment(String comment) {

        type(orderComment, comment);
    }

    /**
     * Proceed to the payment page.
     */
    public void placeOrder() {

        click(placeOrderButton);
    }

    /**
     * Enter payment information.
     */
    public void enterPaymentDetails(
            String cardName,
            String cardNumberValue,
            String cvcValue,
            String expiryMonthValue,
            String expiryYearValue) {

        type(nameOnCard, cardName);
        type(cardNumber, cardNumberValue);
        type(cvc, cvcValue);
        type(expiryMonth, expiryMonthValue);
        type(expiryYear, expiryYearValue);
    }

    /**
     * Submit the payment.
     */
    public void payAndConfirmOrder() {

        click(payAndConfirmButton);
    }

    /**
     * Verify successful order confirmation.
     */
    public boolean isOrderConfirmed() {

        return isDisplayed(orderConfirmation);
    }
}