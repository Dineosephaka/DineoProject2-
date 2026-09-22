package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dineo.automation.base.BasePage;

public class HomePage extends BasePage {

    private By signupLoginButton =
            By.xpath("//a[contains(text(),'Signup / Login')]");

    private By productsButton =
            By.xpath("//a[contains(text(),'Products')]");

    private By cartButton =
            By.xpath("//a[contains(text(),'Cart')]");

    private By logoutButton =
            By.xpath("//a[contains(text(),'Logout')]");

    private By loggedInUser =
            By.xpath("//a[contains(.,'Logged in as')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickSignupLogin() {

        click(signupLoginButton);

        return new LoginPage(driver);
    }

    public ProductsPage clickProducts() {

        click(productsButton);

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
}