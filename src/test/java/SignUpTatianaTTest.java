import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SignUpTatianaTTest {
    @BeforeClass
    public void before() {
        WebDriverManager.chromedriver().setup();
    }

    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void testRegistration() throws InterruptedException {
        driver.get("https://humans.net/");
        WebElement signUp = driver.findElement
                (By.xpath("//a[text()='Sign up']"));
        signUp.click();
        Thread.sleep(3000);

        WebElement userName = driver.findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys("8883468487");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("humans");

        WebElement joinNow = driver.findElement(By.id("reg-step-1"));
        joinNow.click();

        WebElement codeBox1 = driver.findElement(By.name("digit0"));
        codeBox1.sendKeys("1");

        WebElement codeBox2 = driver.findElement(By.name("digit1"));
        codeBox2.sendKeys("2");

        WebElement codeBox3 = driver.findElement(By.name("digit2"));
        codeBox3.sendKeys("3");

        WebElement codeBox4 = driver.findElement(By.name("digit3"));
        codeBox4.sendKeys("4");

        WebElement continueButton = driver.findElement(By.xpath("//button[text()='Continue']"));
        continueButton.click();

        WebElement error = driver.findElement(By.xpath("//div[text()='Incorrect verification code']"));

        Assert.assertEquals(error.getText(), "Incorrect verification code");
    }

    @Test
    public void testLogInIncorrectValues() throws InterruptedException {
        driver.get("https://humans.net/registration");
        WebElement logIn = driver.findElement(By.xpath("//span[text()='Log in']"));
        logIn.click();
        Thread.sleep(3000);

        WebElement userName = driver.findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys("8883468487");

        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("humans");

        WebElement continueButton = driver.findElement(By.xpath("//button[@type='submit']/span"));
        continueButton.click();

        WebElement error = driver.findElement(By.xpath("//span[text()='Incorrect login or password']"));

        Assert.assertEquals(error.getText(), "Incorrect login or password");

    }
}
