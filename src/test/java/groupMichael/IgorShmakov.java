package groupMichael;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class IgorShmakov {

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
    public void testIgorShmakovSearchResult() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://www.picknpull.com");

        Select chooseMake = new Select(driver.findElement(By.xpath("//select[contains(@class, 'form-control') and contains(@class, 'ng-untouched')]")));
        chooseMake.selectByValue("182");

        Select chooseModel = new Select(driver.findElement(By.xpath("(//select[contains(@class, 'form-control') and contains(@class, 'ng-untouched')])[2]")));
        chooseModel.selectByValue("3626");

        WebElement chooseZipCode = driver.findElement(By.id("tbInvZip"));
        chooseZipCode.sendKeys("95123");

        driver.findElement(By.id("ctl00_ctl00_MasterHolder_ciSearch")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Matching Vehicles')]")));
        Assert.assertEquals(driver.getTitle(), "Pick-n-Pull | Check Inventory");

        WebElement chooseFirstMercedes = driver.findElement(By.xpath("(//td[contains(text(), 'Mercedes-Benz')])[1]"));
        chooseFirstMercedes.click();
        WebElement mercedesVinNumber = driver.findElement(By.xpath("//*[contains(text(), 'VIN')]/following-sibling::span"));
        Assert.assertEquals(mercedesVinNumber.getText().toCharArray().length, 17);

    }
}
