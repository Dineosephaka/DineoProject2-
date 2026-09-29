package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dineo.automation.base.BasePage;

public class HomePage extends BasePage {

    private final By signupLoginButton =
            By.xpath("//a[contains(text(),'Signup / Login')]");

    private final By productsButton =
            By.xpath("//a[contains(text(),'Products')]");

    private final By cartButton =
            By.xpath("//a[contains(text(),'Cart')]");

    private final By logoutButton =
            By.xpath("//a[contains(text(),'Logout')]");

    private final By deleteAccountButton =
            By.xpath("//a[contains(text(),'Delete Account')]");

    private final By loggedInUser =
            By.xpath("//a[contains(.,'Logged in as')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickSignupLogin() {
        click(signupLoginButton);
        return new LoginPage(driver);
    }

    public ProductsPage clickProducts() {
        driver.get("https://automationexercise.com/products");
        return new ProductsPage(driver);
    }

    public CartPage clickCart() {
        click(cartButton);
        return new CartPage(driver);
    }

    public void logout() {
        click(logoutButton);
    }

    public boolean isUserLoggedIn() {
        return isDisplayed(loggedInUser);
    }

    public AccountPage deleteAccount() {
        click(deleteAccountButton);
        return new AccountPage(driver);
    }

    public boolean isHomePageDisplayed() {
        return driver.getCurrentUrl().equals(
                "https://automationexercise.com/"
        );
    }
}