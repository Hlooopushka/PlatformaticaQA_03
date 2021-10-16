import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class JavaHamsters {

    private final String URL_IK = "https://www.vprok.ru/";

    WebDriver driver;

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
    public void IlyaKorolkovPopUpExistsTest() {
        driver.get(URL_IK);

        WebElement popUp = driver.findElement(By.className("fo-cookies-policy"));

        Assert.assertTrue(popUp.isDisplayed());
    }

    @Test
    public void IlyaKorolkovClosePopUpTest() throws InterruptedException {
        driver.get(URL_IK);

        WebElement closePopUpButton = driver.findElement(By.className("fo-cookies-policy__close-btn"));

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", closePopUpButton);

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        Thread.sleep(2000);

        List<WebElement> popUp = driver.findElements(By.className("fo-cookies-policy"));
        Assert.assertTrue(popUp.isEmpty());
    }

    @Test
    public void IlyaKorolkovChangeRegionTest() throws InterruptedException {
        driver.get(URL_IK);

        final String region = "Свердловская обл.";

        WebElement changeRegionLink = driver.findElement(By.className("js-address-data"));
        changeRegionLink.click();

        Thread.sleep(3000);

        WebElement regionToSelect = driver.findElement(By.xpath("//div[@id='form_popup-polygons']//a[contains(text(), '" + region + "')]"));
        regionToSelect.click();

        Thread.sleep(1000);

        Assert.assertEquals(driver.findElement(By.className("js-address-data")).getText().trim(), region);
    }

    @Test
    public void IlyaKorolkovSearchTest() {
        driver.get(URL_IK);

        final String textToSearch = "хамон";

        driver.findElement(By.xpath("//div[@id='main-app']//input[@name='search']")).sendKeys(textToSearch + "\n");

        List<WebElement> searchResults = driver.findElements(By.xpath("//ul[@id='catalogItems']//a[contains(@class, 'xf-product-title__link')]"));

        for (int i = 0; i < searchResults.size(); i++) {
            Assert.assertTrue(searchResults.get(i).getText().toLowerCase().contains(textToSearch));
        }
    }

    @Test
    public void IlyaKorolkovLinkToPromosTest() {
        driver.get(URL_IK);

        String expectedUrl = "https://www.vprok.ru/promos";

        driver.findElement(By.xpath("//a[@target='_self' and contains(text(), 'Акции')]")).click();

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }
}