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

public class GroupJavaWestCoast {

    WebDriver driver;

    public void signInMethodIliaP(){
        WebElement login = driver.findElement(By.xpath("//a[text()='Log In']"));
        login.click();

        WebElement emailField = driver.findElement(By.id("user_email"));
        emailField.sendKeys("testing@testing.com");

        WebElement passwordField = driver.findElement(By.id("user_password"));
        passwordField.sendKeys("Test88");

        WebElement signInButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
        signInButton.click();
    }

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void setDown(){
        driver.quit();
    }


    @Test
    public void testIliaP() {

        driver.get("https://www.codewars.com/");

        signInMethodIliaP();

        WebElement scoreButton = driver.findElement(By.xpath("//div[@class='ml-10px is-inline']"));
        String actualValue = "Honor:" + scoreButton.getText();
        scoreButton.click();

        WebElement honor = driver.findElement(By.xpath("//b[text()='Honor:']/.."));
        String expectedValue = honor.getText();

        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testIliaPTwo(){

        driver.get("https://www.codewars.com/");

        signInMethodIliaP();

        WebElement scoreButton = driver.findElement(By.xpath("//div[@class='ml-10px is-inline']"));
        scoreButton.click();

        WebElement rankField = driver.findElement(By.xpath("//b[text()=\"Rank:\"]/.."));
        String expectedValue = rankField.getText();
        String resultExpected = "";
        for(int i = 0;i<expectedValue.length();i++){
            if (expectedValue.charAt(i)==':'){
                resultExpected += expectedValue.charAt(i+1);
            }
        }
        WebElement overallField = driver.findElement(By.xpath("//b[text()=\"Overall:\"]/.."));
        String actualValue = overallField.getText();
        String resultActual = "";
        for(int i = 0;i<actualValue.length();i++){
            if (actualValue.charAt(i)==':'){
                resultActual += actualValue.charAt(i+1);
            }
        }
        Assert.assertEquals(resultActual,resultExpected);
    }
}

