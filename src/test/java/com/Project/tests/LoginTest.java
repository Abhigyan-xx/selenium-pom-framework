package com.Project.tests;

import com.Project.base.BaseTest;
import com.Project.Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {

        LoginPage loginPage = new LoginPage(driver);

        System.out.println("Page Title: " + driver.getTitle());

        loginPage.login("abhigyan.shukla@finconic.com", "delhi@12345");

        System.out.println("Login button clicked successfully.");
    }
}