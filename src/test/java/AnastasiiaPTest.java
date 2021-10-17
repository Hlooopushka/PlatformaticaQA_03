import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AnastasiiaPTest {

        private WebDriver driver;
        WebDriverWait wait;

        @BeforeMethod
        public void setUp() {
            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver();

            driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        @AfterMethod
        public void setDown() {
            driver.close();
            driver.quit();
        }

        @Test
        public void testCurrentAddressTextField() {
            driver.get("https://www.1800flowers.com/");

            WebElement searchField = driver.findElement(By.id("SearchBox_desktop"));
            WebElement submitButton = driver.findElement(By.id("btn-search"));
            String expectedResult = "Roses Delivery";

            searchField.sendKeys("Roses \n");
            WebElement actualResult = driver.findElement(By.xpath("//div[h1]"));

            Assert.assertEquals(actualResult.getText(), expectedResult);
        }

        @Test
        private void testSearchSubjectTextField() {
            driver.get("https://shop.mango.com/us/women");

            WebElement searchButton = driver.findElement(By.id("search_icon_button"));
            searchButton.click();
            WebElement searchField = driver.findElement(By.xpath("//div//input[@class='search-input']"));
            searchField.sendKeys("NY dress\n");

            String expectedResult = "SEARCH RESULTS FOR NY DRESS";
            WebElement actualResult = driver.findElement(By.xpath("//div[@id = 'title']"));

            Assert.assertEquals(actualResult.getText(), expectedResult);
        }
    }


