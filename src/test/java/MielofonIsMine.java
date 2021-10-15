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

public class MielofonIsMine {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void anastasia_kaz_Search() {

        driver.get("https://rp5.by");

        WebElement search = driver.findElement(By.xpath("//*[@class='search ac_input']"));
        WebElement button = driver.findElement(By.xpath("//*[@class='searchButton']"));

        search.sendKeys("лида");
        button.click();

        WebElement text = driver.findElement(By.xpath("//*[@id='leftNavi']/span/h1"));
        Assert.assertEquals(text.getText(), "Результат поиска");
    }

    @Test
    public void anastasia_kaz_Logotip() {

        driver.get("https://rp5.by");

        WebElement search = driver.findElement(By.xpath("//*[@class='search ac_input']"));
        WebElement button = driver.findElement(By.xpath("//*[@class='searchButton']"));

        search.sendKeys("лида");
        button.click();

        WebElement logotip = driver.findElement(By.xpath("//*[@src='/images/ru/logo.png']"));
        Assert.assertTrue(logotip.isDisplayed());
    }

    @Test
    public void anastasia_kaz_FAQ() {
        driver.get("https://rp5.by");

        WebElement question = driver.findElement(By.xpath("//div[@id='topMenuContent']/a[5]"));
        question.click();

        WebElement lastQuestion = driver.findElement(By.xpath("//ol/li[9]/b"));
        Assert.assertEquals(lastQuestion.getText(), "Что означает определение \"обложной\" во фразах \"обложной дождь\" или \"обложной снег\"?");
    }
}
