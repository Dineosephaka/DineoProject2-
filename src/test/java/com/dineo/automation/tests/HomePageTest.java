package com.dineo.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyAutomationExerciseWebsite() {

        String pageTitle = driver.getTitle();

        System.out.println("Page Title: " + pageTitle);

        Assert.assertTrue(
                pageTitle.contains("Automation Exercise"),
                "Page title does not contain 'Automation Exercise'"
        );
    }
}