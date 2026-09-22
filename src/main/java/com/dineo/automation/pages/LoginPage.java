
package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dineo.automation.base.BasePage;

public class LoginPage extends BasePage {

    // Login fields
    private final By emailField =
            By.xpath("//input[@data-qa='login-email']");

    private final By passwordField =
            By.xpath("//input[@data-qa='login-password']");

    private final By loginButton =
            By.xpath("//button[@data-qa='login-button']");

    // Login validation
    private final By loginErrorMessage =
            By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");

    // Logged-in user
    private final By loggedInUser =
            By.xpath("//a[contains(.,'Logged in as')]");

    // Signup fields
    private final By signupNameField =
            By.xpath("//input[@data-qa='signup-name']");

    private final By signupEmailField =
            By.xpath("//input[@data-qa='signup-email']");

    private final By signupButton =
            By.xpath("//button[@data-qa='signup-button']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Logs in using the supplied email and password.
     */
    public HomePage login(String email, String password) {

        type(emailField, email);
        type(passwordField, password);
        click(loginButton);

        return new HomePage(driver);
    }

    /**
     * Verifies that the invalid login error message is displayed.
     */
    public boolean isLoginErrorDisplayed() {

        return isDisplayed(loginErrorMessage);
    }

    /**
     * Verifies that the user is logged in.
     */
    public boolean isLoggedIn() {

        return isDisplayed(loggedInUser);
    }

    /**
     * Enters details required to start registration.
     */
    public void enterSignupDetails(String name, String email) {

        type(signupNameField, name);
        type(signupEmailField, email);
    }

    /**
     * Continues from the signup form to the account creation page.
     */
    public SignupPage startSignup() {

        click(signupButton);

        return new SignupPage(driver);
    }
}

