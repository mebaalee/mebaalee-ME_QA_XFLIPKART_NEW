package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WrapperAction {
    static WebDriver driver;

    public static void openBrowser(String url) {
        System.out.println("Setup Test Case starts - FlipKart webpage navigation checking");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
    }


    //Method to sendKeys
    public static boolean addText(WebDriver driver, By locator,String text) throws InterruptedException {
        boolean success;
        try{
            WebElement inputBox = driver.findElement(locator);
            inputBox.clear();
            inputBox.sendKeys(text);
            inputBox.sendKeys(Keys.ENTER);
            Thread.sleep(5000);
            success = true;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Test Case Failed - Exception occured");
            success = false;
        }
        return success;
    }
    //Method to click
    public static boolean clickElement(WebDriver driver ,By locator ) throws InterruptedException {
boolean success;
try{
    WebElement button = driver.findElement(locator);
    button.click();
    Thread.sleep(5000);
    success = true;
}
catch (Exception e){
    e.printStackTrace();
    System.out.println("Test Case Failed - Exception occured");
    success = false;
}
        return success;
    }
    //Method to get the List
    public static List findAllElements(WebDriver driver , By locator ) {
        try{
            List<WebElement> allElements = driver.findElements(locator);
            Thread.sleep(5000);
            return allElements;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Test Case Failed - Exception occured");
        }
        return null;
    }
}
