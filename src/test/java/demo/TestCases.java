package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class TestCases {
    static ChromeDriver driver;
    @BeforeSuite(enabled = true, alwaysRun = true)
    public static void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
    }
    @Test(priority = 1)
    public void testCase01(){
        try {
            int count = 0;
            System.out.println("Test Case 01 Starts - Print the count of washing machines having Ratings <=4");
            driver.get("https://www.flipkart.com/");
            //invoke the Method to add the text
            boolean textAdded = WrapperAction.addText(driver, By.xpath("//input[contains(@title,'Search for Products, Brands and More')]"), "washing machine");
            Thread.sleep(10000);
            Assert.assertTrue(textAdded);
            //invoke the Method to click on an Element
            boolean elementClicked = WrapperAction.clickElement(driver, By.xpath("//div[@class ='zg-M3Z' and text()='Popularity']"));
            Assert.assertTrue(elementClicked);
            //Get all the items in a List
            List<WebElement> products = WrapperAction.findAllElements(driver, By.xpath("//div[@class ='XQDdHH']"));
            //iterating the List to get the items with given condition -- rating <=4
            for (WebElement product : products) {
                String popularity = product.getText();
                double rating = Double.parseDouble(popularity);
                if (rating <= 4) {
                    count++;
                }
            }
            System.out.println("Count of Washing Machines :" + count);
            System.out.println("Test Case 01 Passed");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Testcase Failed");
        }
    }

    @Test(priority = 2)
    public void testCase02() throws InterruptedException {
        try {
            System.out.println("Test Case 02 Starts - Print the titles of iphone having discount > 17% ");
            driver.get("https://www.flipkart.com/");
            //invoke the Method to add the text
            boolean textAdded = WrapperAction.addText(driver, By.xpath("//input[contains(@title,'Search for Products, Brands and More')]"), "iphone");
            //Get all the items in a List
            List<WebElement> titles = WrapperAction.findAllElements(driver, By.xpath("//div[@class ='KzDlHZ']"));
            List<WebElement> discounts = WrapperAction.findAllElements(driver, By.xpath("//div[@class ='UkUFwK']"));
            //iterating the List to get the items with given condition -- discount >17
            for (int i = 0; i < discounts.size(); i++) {
                String str1 = discounts.get(i).getText();
                //removing the junk values such as ( / %
                String str2 = (str1.replaceAll("[\\D]", ""));
                //convert the string to integer
                int discount = Integer.parseInt(str2);
                if (discount > 17) {
                    System.out.println(titles.get(i).getText() + " " + str1);
                }
            }
            System.out.println("Test Case 02 Passed");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Testcase Failed");
        }
    }
    @Test(priority = 3)
    public void testCase03() throws InterruptedException {
        try {
            System.out.println("Test Case 03 Starts - Print the titles and image url of Top 5 coffee mugs having highest number of reviews");
            driver.get("https://www.flipkart.com/");
            //invoke the Method to add the text
            boolean textAdded = WrapperAction.addText(driver, By.xpath("//input[contains(@title,'Search for Products, Brands and More')]"), "Coffee Mug");
            Thread.sleep(10000);
            Assert.assertTrue(textAdded);
            //Click the check box
            WebElement checkBoxRatings = driver.findElement(By.xpath("(//div[@class = 'XqNaEv' ])[1]"));
            checkBoxRatings.click();
            Thread.sleep(10000);
            //Get all the items in a List
            List<WebElement> reviews = driver.findElements(By.xpath("//span[@class='Wphh3N']"));
            ArrayList<Integer> reviewsAL = new ArrayList<>();
            //iterating the List to get the items
            for (WebElement review : reviews) {
                String str1 = review.getText();
                //removing the junk values such as ( / %
                String str2 = (str1.replaceAll("[\\D]", ""));
                //convert the string to integer and added it to the ArrayList
                reviewsAL.add(Integer.parseInt(str2));
            }
            //Sort the ArrayList then reverse the ArrayList
            Collections.sort(reviewsAL, Collections.reverseOrder());
            //iterating the List to get the first 5 items
            for (int i = 0; i < 5; i++) {
                //Initialise a DecimalFormat class with a pattern to match with the value in the xpath
                String formattedNumber = "";
                DecimalFormat formatter = new DecimalFormat("#,###");
                //format the value to the req format pattern
                formattedNumber = formatter.format(reviewsAL.get(i));
                System.out.println(formattedNumber);
                Thread.sleep(5000);
                //find the element with the format number with the xpath
                WebElement title = driver.findElement(By.xpath("//span[@class='Wphh3N' and contains(text(),"+ "'"+formattedNumber +"'"+")]/parent::div/preceding-sibling::div/preceding-sibling::a[1]"));
                String productTitle = title.getText();
                Thread.sleep(5000);
                WebElement image = driver.findElement(By.xpath("//span[@class='Wphh3N' and contains(text(),"+ "'"+formattedNumber +"'"+")]/parent::div/preceding-sibling::div/preceding-sibling::a[1]"));
                String productImage = image.getAttribute("href");
                System.out.println("Title of the Product with high reveiws :" + productTitle);
                System.out.println("Image link of the Product with high reveiws :" + productImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Testcase Failed");
        }
    }
}











