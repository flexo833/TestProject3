import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.util.*;

public class Test {



    public static void main(String[] args) throws InterruptedException {
        Products pr = new Products();

        String name = pr.name;
        String nums = pr.nums;
        String price = pr.price;

        System.out.println(name);
        System.out.println(nums);
        System.out.println(price);



        WebDriver driver = GetWebDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        autorize(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#header_logo"))).isDisplayed();

        Actions hover = new Actions(driver);
        WebElement catalogLink = driver.findElement(By.cssSelector("#subtab-AdminCatalog"));
        hover.moveToElement(catalogLink).build().perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#subtab-AdminProducts > a"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#page-header-desc-configuration-add > span"))).click();


        WebElement numOfProdInput = driver.findElement(By.cssSelector("#form_step1_qty_0_shortcut"));
        WebElement priceOfProdInput = driver.findElement(By.cssSelector("#form_step1_price_ttc_shortcut"));
        WebElement saveButton = driver.findElement(By.xpath("//button[@class=\"btn btn-primary js-btn-save\"]"));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#form_step1_name_1")))
                .sendKeys(name);
        numOfProdInput.clear();
        numOfProdInput.sendKeys(nums);
        priceOfProdInput.clear();
        priceOfProdInput.sendKeys(price);




        driver.findElement(By.xpath("//*[@id='form']/div[4]/div[1]/div")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div > div.growl-message")));
        saveButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div > div.growl-message")));
        driver.findElement(By.xpath("//*[@id='growls']/div/div[1]")).click();


//        logOut(driver,wait);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#login_form"))).isDisplayed();
        driver.quit();








        }




    private static void autorize(WebDriver driver) {
        String url = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
        driver.navigate().to(url);

        driver.findElement(By.name("email")).sendKeys("webinar.test@gmail.com");
        driver.findElement(By.name("passwd")).sendKeys("Xcg7299bnSmMuRLp9ITw");
        driver.findElement(By.name("submitLogin")).click();
    }

    private static WebDriver GetWebDriver() {
        System.setProperty("webdriver.chrome.driver", Test.class.getResource("chromedriver.exe").getPath());
        return new ChromeDriver();
    }



    private static void logOut (WebDriver driver, WebDriverWait wait) throws InterruptedException {

        driver.findElement(By.xpath("//div[@class=\"img-circle person\"]/i")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='header_logout']"))).click();
    }



}


