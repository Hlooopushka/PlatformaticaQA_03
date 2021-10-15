import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
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
    JavascriptExecutor js;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void testAndreyTeterinAddressCheck() {

        final String ADDRESS = "0x141a281889581C97cd7e6D4EC0A9B064B08bb239";

        driver.get("https://ethermine.org/");

        WebElement search = driver.findElement(By.xpath("//input[@class='search-input search-header']"));
        search.sendKeys(ADDRESS);
        search.sendKeys(Keys.ENTER);

        WebElement addressHeader = driver.findElement(By.className("address"));
        wait.until(ExpectedConditions.visibilityOf(addressHeader));
        Assert.assertEquals(addressHeader.getText(), ADDRESS);
    }

    @Test
    public void testAndreyTeterinNews() {

        driver.get("https://ethermine.org/");

        WebElement twitterButton = driver.findElement(By.className("open-twitter"));
        WebElement twitterWidget = driver.findElement(By.className("twitter-container"));

        twitterButton.click();

        Assert.assertFalse(twitterWidget.isDisplayed());
    }

    @Test
    public void testAndreyTeterinDarkTheme() {

        driver.get("https://ethermine.org/");

        WebElement darkThemeButton = driver.findElement(By.className("theme-switch"));
        WebElement html = driver.findElement(By.xpath("//html"));

        js.executeScript("arguments[0].click();", darkThemeButton);

        Assert.assertEquals(html.getAttribute("data-theme"), "dark");
    }
}
