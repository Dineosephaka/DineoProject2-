package com.dineo.automation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String takeScreenshot(
            WebDriver driver,
            String testName) {

        String folderPath = "screenshots";

        try {
            Files.createDirectories(Paths.get(folderPath));

            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            String fileName =
                    testName + "_" + System.currentTimeMillis() + ".png";

            Path destination =
                    Paths.get(folderPath, fileName);

            Files.copy(
                    source.toPath(),
                    destination
            );

            return destination.toString();

        } catch (IOException e) {

            e.printStackTrace();

            return null;
        }
    }
}