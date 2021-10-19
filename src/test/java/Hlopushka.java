import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class Hlópushka {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/aivin/Desktop/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    @Test
    public void naveriskTest() throws InterruptedException {

        driver.get("https://naverisk.com/");
        sleep(5000);
        JavascriptExecutor scrollingPage = (JavascriptExecutor) driver;
        scrollingPage.executeScript("window.scrollBy(0,3970)", "");
        Assert.assertEquals(3970, 3970);
        sleep(1000);

        //Scrolling Up
        scrollingPage.executeScript("scroll(0,-2700);");
        Assert.assertEquals(-2700, -2700);
        sleep(1000);

        //Find and click button in a top bar
        WebElement solutionsBtn = driver.findElement(new By.ByXPath("/html/body/div[1]/header/div[1]/div[2]/nav/ul/li[1]/a"));
        solutionsBtn.click();
        sleep(1000);
        Assert.assertEquals("Solutions", "Solutions");

        //Move to the next page
        driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div[2]/nav/ul/li[1]/ul/li[2]/a")).click();
        sleep(1000);
        scrollingPage.executeScript("window.scrollBy(0,3970)", "");
        sleep(1000);
        driver.findElement(By.linkText("Contact us")).click();
        sleep(1000);
        scrollingPage.executeScript("window.scrollBy(0,500)", "");
        sleep(1000);

        //To get && compare title
        String actualTitle = driver.getTitle();
        String expectedTitle = "24/7 Global Support | Naverisk RMM & PSA Software";
        Assert.assertEquals(actualTitle, expectedTitle);

        //To accept cookies
        WebElement acceptCookie = driver.findElement(By.id("cn-accept-cookie"));
        acceptCookie.click();
        sleep(1000);
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

}
