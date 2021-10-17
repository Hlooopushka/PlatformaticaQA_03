import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MarinaHTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown(){
        driver.quit();
    }

    @Test
    public void testMarinaHort() throws InterruptedException {
        String expectedResult = "https://my-learning.w3schools.com/";

        driver.get("https://www.w3schools.com/");

        WebElement logInButton = driver.findElement(By.xpath("//div/a[@id='w3loginbtn']"));
        logInButton.click();

        WebElement emailField = driver.findElement(By.xpath("//input[@class='_ZsgaF undefined']"));
        emailField.sendKeys("marinahortobagyi66@gmail.com");

        WebElement password = driver.findElement(By.xpath("//input[@class='_lEWNL']"));
        password.sendKeys("Mandula66!");

        WebElement logInButton1 = driver.findElement(By.xpath("//button[@class='_1VfsI _OD95i _3_H0V']"));
        logInButton1.click();

        Thread.sleep(7000);
        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
