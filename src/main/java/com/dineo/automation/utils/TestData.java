package com.dineo.automation.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestData {

    public static String generateUniqueEmail() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        String timestamp =
                LocalDateTime.now().format(formatter);

        return "dineo_" + timestamp + "@test.com";
    }

    public static String getPassword() {
        return "Dineo@12345";
    }

    public static String getFirstName() {
        return "Dineo";
    }

    public static String getLastName() {
        return "Tester";
    }

    public static String getCompany() {
        return "Dineo Automation";
    }

    public static String getAddress() {
        return "123 Test Street";
    }

    public static String getState() {
        return "Gauteng";
    }

    public static String getCity() {
        return "Johannesburg";
    }

    public static String getZipCode() {
        return "2000";
    }

    public static String getMobileNumber() {
        return "0712345678";
    }
}