import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public  class Test1 {

    protected WebDriver driver;

    Products pr = new Products();



    @Parameters("browser")
    @BeforeTest

    protected WebDriver getDriver(String browser) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", Test1.class.getResource("chromedriver.exe").getPath());
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/home/user/drivers/geckodriver");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();

        return driver;
    }

    @Test(dataProvider = "GetEmailAndPass")
    public void testPage(String email, String password) {

        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("passwd")).sendKeys(password);
        driver.findElement(By.name("submitLogin")).click();
        String currentTitle = driver.getTitle();
        String expectedTitle = "prestashop-automation > Панель администратора (PrestaShop™)";
        Assert.assertEquals(expectedTitle, currentTitle);

    }


    @Test(dependsOnMethods = {"testPage"})
    public void testProducts() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions hover = new Actions(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#header_logo"))).isDisplayed();
        WebElement catalogLink = driver.findElement(By.cssSelector("#subtab-AdminCatalog"));
        hover.moveToElement(catalogLink).build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#subtab-AdminProducts > a"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#main-div > div.header-toolbar > h2")));

        Assert.assertTrue(driver.findElement(By.linkText(pr.name)).isDisplayed());

    }

    @Test(dependsOnMethods = {"testProducts"})
    public void testProductProperties() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.linkText(pr.name)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='form_step1_name_1']")));
        WebElement numOfProdInput = driver.findElement(By.cssSelector("#form_step1_qty_0_shortcut"));
        WebElement priceOfProdInput = driver.findElement(By.cssSelector("#form_step1_price_ttc_shortcut"));
        String checkNum = numOfProdInput.getText();
        String checkPrice =priceOfProdInput.getText();
        Assert.assertEquals(pr.nums,checkNum);
        Assert.assertEquals(pr.nums,checkPrice);
        Assert.
    }

        @DataProvider
        public Object[][] GetEmailAndPass () {
            return new Object[][]{{"webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw"}};
        }


        @AfterTest
        protected void tearDown () {
        System.out.println(pr.name);
        driver.quit();
        }
    }

