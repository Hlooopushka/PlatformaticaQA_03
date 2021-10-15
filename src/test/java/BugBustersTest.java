import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BugBustersTest {

    private WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void testAndreyTeterin() {

        final String ADDRESS = "0x141a281889581C97cd7e6D4EC0A9B064B08bb239";

        driver.get("https://ethermine.org/");

        WebElement search = driver.findElement(By.xpath("//input[@class='search-input search-header']"));
        search.sendKeys(ADDRESS);
        search.sendKeys(Keys.ENTER);

        WebElement addressHeader = driver.findElement(By.className("address"));
        wait.until(ExpectedConditions.visibilityOf(addressHeader));
        Assert.assertEquals(addressHeader.getText(), ADDRESS);
    }
}
