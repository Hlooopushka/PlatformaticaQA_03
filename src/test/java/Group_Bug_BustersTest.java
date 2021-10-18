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


public class Group_Bug_BustersTest {
    WebDriver driver;
    public final String URL = "https://breadtopia.com/";


    @BeforeMethod
    void start() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @AfterMethod
    void close() {
        driver.quit();
    }

    @Test
    public void NatalliaMarkhotka_ValidateMainPage() {

        List<WebElement> images = driver.findElements(By.xpath("//div[@id='post-53473']/div[1]/ div"));
        int numberOfImages = images.size();
        Assert.assertEquals(numberOfImages, 4);

    }

    @Test
    public void NatalliaMarkhotka_CreateAccount() {

        WebElement myAccount = driver.findElement(By.id("menu-item-261238"));

        myAccount.click();
        driver.navigate().to("https://breadtopia.com/my-account/");


        WebElement username_F = driver.findElement(By.id("username"));
        WebElement password_F = driver.findElement(By.id("password"));
        WebElement login_B = driver.findElement(By.name("login"));

        username_F.sendKeys("snezhnaja10@gmail.com");
        password_F.sendKeys("QweAsd123!@#");
        login_B.click();
        WebElement greetings = driver.findElement(By.xpath("//span[@class='nm-username']"));

        Assert.assertEquals(greetings.getText(), "Hello Katerina");

    }


}
