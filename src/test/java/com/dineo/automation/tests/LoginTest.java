package com.dineo.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dineo.automation.pages.AccountPage;
import com.dineo.automation.pages.HomePage;
import com.dineo.automation.pages.LoginPage;
import com.dineo.automation.pages.SignupPage;
import com.dineo.automation.utils.TestData;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {

        // Open Signup / Login page
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickSignupLogin();

        // Create a unique account so valid login credentials are available
        String email = TestData.generateUniqueEmail();
        String password = TestData.getPassword();

        loginPage.enterSignupDetails(
                TestData.getFirstName() + " " + TestData.getLastName(),
                email
        );

        // Continue to account information
        SignupPage signupPage = loginPage.startSignup();

        // Enter account information
        signupPage.selectMr();

        signupPage.enterPassword(password);

        signupPage.enterDateOfBirth(
                "10",
                "May",
                "2000"
        );

        signupPage.subscribeToNewsletter();

        signupPage.enterPersonalDetails(
                TestData.getFirstName(),
                TestData.getLastName(),
                TestData.getCompany(),
                TestData.getAddress()
        );

        signupPage.enterLocationDetails(
                TestData.getState(),
                TestData.getCity(),
                TestData.getZipCode(),
                TestData.getMobileNumber()
        );

        // Create the account
        AccountPage accountPage = signupPage.createAccount();

        // Verify account was created
        Assert.assertTrue(
                accountPage.isAccountCreated(),
                "Account was not created successfully."
        );

        // Return to home page
        driver.get("https://automationexercise.com/");

        HomePage loggedInHomePage = new HomePage(driver);

        // Verify user is logged in after registration
        Assert.assertTrue(
                loggedInHomePage.isUserLoggedIn(),
                "User should be logged in after registration."
        );

        // Log out so that we can test the actual login process
        loggedInHomePage.logout();

        // Open Signup / Login page again
        HomePage loggedOutHomePage = new HomePage(driver);
        LoginPage loginPageAgain = loggedOutHomePage.clickSignupLogin();

        // Login using the valid credentials created above
        loginPageAgain.login(email, password);

        // Verify successful login
        Assert.assertTrue(
                loginPageAgain.isLoggedIn(),
                "User should be successfully logged in with valid credentials."
        );

        System.out.println(
                "Positive login test passed successfully."
        );
    }

    @Test
    public void verifyInvalidLogin() {

        // Open the Signup / Login page
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickSignupLogin();

        // Attempt login with invalid credentials
        loginPage.login(
                "invalid@test.com",
                "WrongPassword123"
        );

        // Verify the invalid login error message
        Assert.assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Invalid login error message was not displayed."
        );

        System.out.println(
                "Negative login test passed successfully."
        );
    }
}