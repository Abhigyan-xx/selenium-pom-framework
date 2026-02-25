package com.Project.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.*;

public class LoginPage {

    private WebDriver driver;
    private By loginLink = By.xpath("//*[@id=\"root\"]/div/div/div/button");
    private By emailField = By.xpath("/html/body/div/div[2]/div/div/div/div/form/div[1]/input");
    private By passwordField = By.xpath("/html/body/div/div[2]/div/div/div/div/form/div[2]/input");
    private By loginButton = By.xpath("/html/body/div/div[2]/div/div/div/div/form/div[4]/input[2]");
    private By dashboard = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/button[1]/p[1]");
    private By enterPan = By.xpath("//input[@placeholder='Search by PAN']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // this method is connecting the driver to this page behaviour
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickonDashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dashboard)).click();
    }

    public void enterPan() {
        driver.findElement(enterPan).sendKeys("AAACI0996E");
        driver.findElement(enterPan).sendKeys(Keys.ENTER);
    }

    public void login(String email, String password) {
        clickLoginLink();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        clickonDashboard();
        enterPan();
    }
}