package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class singlePage {


    public static void main(String[] args) {

        // step 1 - create driver object
        WebDriver driver = new ChromeDriver();

        // step 2 - open the url website
        driver.get("https://www.corpvue.com/crm/crm");

        // Step 3: Maximize window
        driver.manage().window().maximize();

        // Step 4: Click login button
        driver.findElement(By.xpath("/html/body/div/div/div/div/button")).click();

        // step 5 - print the title of the page
        System.out.println(driver.getTitle());


        // step 6 - close the current open web page
        driver.quit();
    }
}
