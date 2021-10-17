import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bug_Buster_Test {

    private WebDriver driver;


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }


    @Test
    public void testSearch1(){ //Nelya Luchynets
        driver.get("https://www.walgreens.com/");

        WebElement input = driver.findElement(By.xpath("//input[@id='ntt-placeholder']"));
        input.sendKeys("vitamin\n");

        WebElement result = driver.findElement(By.xpath("//h1[@class='h1__page-title']"));
        Assert.assertEquals(result.getText(),"Vitamins and Supplements");
    }

}


