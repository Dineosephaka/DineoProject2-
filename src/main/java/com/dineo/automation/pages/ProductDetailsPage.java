package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dineo.automation.base.BasePage;

public class ProductDetailsPage extends BasePage {

    private By productName =
            By.xpath("//div[@class='product-information']//h2");

    private By productPrice =
            By.xpath("//div[@class='product-information']//span/span");

    private By availability =
            By.xpath("//div[@class='product-information']//p[contains(text(),'Availability')]");

    private By condition =
            By.xpath("//div[@class='product-information']//p[contains(text(),'Condition')]");

    private By brand =
            By.xpath("//div[@class='product-information']//p[contains(text(),'Brand')]");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public String getAvailability() {
        return getText(availability);
    }

    public String getCondition() {
        return getText(condition);
    }

    public String getBrand() {
        return getText(brand);
    }
}