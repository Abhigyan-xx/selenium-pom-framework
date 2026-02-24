package com.Project.base;

import com.Project.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {

        String browser = com.project.utils.ConfigReader.getProperty("browser");
        String url = com.project.utils.ConfigReader.getProperty("url");

        driver = DriverFactory.initDriver(browser);
        driver.get(url);
    }

//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}