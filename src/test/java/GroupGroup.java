import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class GroupGroup {

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
    public void NarimanMirzakhanov1() {
        driver.get("https://www.bestbuy.com/");

        WebElement closeButton = driver.findElement(By.xpath("//button[@class='c-close-icon c-modal-close-icon']"));
        closeButton.click();

        String text = "watch";
        driver.findElement(By.id("gh-search-input")).sendKeys(text + "\n");

        List<WebElement> itemList = driver.findElements(By.xpath("//h4[@class='sku-header']/a"));
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains(text));
        }
    }

    @Test
    public void NarimanMirzakhanov2() {
        driver.get("https://www.bestbuy.com/");
        driver.findElement(By.xpath("//button[@class='c-close-icon c-modal-close-icon']")).click();

        String text = "iphone 13 pro max 512 verizon";
        driver.findElement(By.id("gh-search-input")).sendKeys(text);
        driver.findElement(By.xpath("//span[@class='header-search-icon']")).click();

        WebElement productName = driver.findElement(By.xpath("//h4[@class='sku-header']"));
        Assert.assertEquals(productName.getText(), "Apple - iPhone 13 Pro Max 5G 512GB - Graphite (Verizon)");
    }
}
