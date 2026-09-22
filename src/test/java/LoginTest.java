
package com.dineo.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dineo.automation.pages.HomePage;
import com.dineo.automation.pages.LoginPage;

public class LoginTest extends BaseTest {

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
    }
}


