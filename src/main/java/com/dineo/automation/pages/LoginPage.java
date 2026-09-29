package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dineo.automation.base.BasePage;

public class LoginPage extends BasePage {

    private final By emailField =
            By.xpath("//input[@data-qa='login-email']");

    private final By passwordField =
            By.xpath("//input[@data-qa='login-password']");

    private final By loginButton =
            By.xpath("//button[@data-qa='login-button']");

    private final By loginErrorMessage =
            By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");

    private final By loggedInUser =
            By.xpath("//a[contains(.,'Logged in as')]");

    private final By signupNameField =
            By.xpath("//input[@data-qa='signup-name']");

    private final By signupEmailField =
            By.xpath("//input[@data-qa='signup-email']");

    private final By signupButton =
            By.xpath("//button[@data-qa='signup-button']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HomePage login(String email, String password) {

        type(emailField, email);
        type(passwordField, password);
        click(loginButton);

        return new HomePage(driver);
    }

    public boolean isLoginErrorDisplayed() {

        return isDisplayed(loginErrorMessage);
    }

    public boolean isLoggedIn() {

        return isDisplayed(loggedInUser);
    }

    public void enterSignupDetails(String name, String email) {

        type(signupNameField, name);
        type(signupEmailField, email);
    }

    public SignupPage startSignup() {

        click(signupButton);

        return new SignupPage(driver);
    }
}