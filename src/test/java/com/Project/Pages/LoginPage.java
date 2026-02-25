package com.Project.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.*;

public class LoginPage {

    private WebDriver driver;

    // Locators
    private By loginLink = By.xpath("//*[@id=\"root\"]/div/div/div/button");
    private By emailField = By.xpath("/html/body/div/div[2]/div/div/div/div/form/div[1]/input");
    private By passwordField = By.xpath("/html/body/div/div[2]/div/div/div/div/form/div[2]/input");
    private By loginButton = By.xpath("/html/body/div/div[2]/div/div/div/div/form/div[4]/input[2]");
    private By dashboard = By.xpath("/html/body/div/div/div/div/button/p");
    private By enterPanField = By.xpath("//input[@placeholder='Search by PAN']");

    private By entityKeyRows = By.xpath("//span[@class='font-medium']/parent::p");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Click Login Link
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    // Enter Email
    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(emailField);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    // Enter Password
    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordField);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    // Click Login Button
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Click Dashboard
    public void clickOnDashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(dashboard)).click();
    }

    // Enter PAN dynamically
    public void enterPan(String pan) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement panInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(enterPanField)
        );

        panInput.clear();
        panInput.sendKeys(pan);
        panInput.sendKeys(Keys.ENTER);

        // Wait for entity details to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='font-medium']")
        ));
    }

    // Extract Entity Details → Map<Key, Value>
    public Map<String, String> getEntityDetails() {

        Map<String, String> entityMap = new LinkedHashMap<>();

        List<WebElement> rows = driver.findElements(entityKeyRows);

        System.out.println("Rows found: " + rows.size());

        for (WebElement row : rows) {

            String key = row.findElement(By.xpath("./span[1]"))
                    .getText()
                    .replace(":", "")
                    .trim();

            String value = row.findElement(By.xpath("./span[2]"))
                    .getText()
                    .trim();

            entityMap.put(key, value);
        }

        return entityMap;
    }

    // Process multiple PANs
    public Map<String, Map<String, String>> processMultiplePans(List<String> panList) {

        Map<String, Map<String, String>> masterData = new LinkedHashMap<>();

        for (String pan : panList) {

            System.out.println("Processing PAN: " + pan);

            enterPan(pan);

            Map<String, String> entityData = getEntityDetails();

            masterData.put(pan, entityData);
        }

        return masterData;
    }

    // Complete Login + Extract all PAN data
    public void loginAndExtractAllPanData(String email, String password) {

        clickLoginLink();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        clickOnDashboard();

        // Hardcoded PAN list (10 PANs)
        List<String> panList = Arrays.asList(
                "AAACU2414K",
                "AAACQ5619A",
                "AABCR7176C",
                "AAACI0996E",
                "AAACL1799C",
                "AAACO3006F",
                "AABCL5126A",
                "AABCL4101F"
        );

        Map<String, Map<String, String>> allPanData =
                processMultiplePans(panList);

        // Print complete data
        System.out.println("=========== FINAL ENTITY DATA ===========");

        allPanData.forEach((pan, entityMap) -> {
            System.out.println(" ");

            System.out.println("PAN: " + pan + " ");

            entityMap.forEach((key, value) ->
                    System.out.println(key + " : " + value)
            );
        });
    }
}