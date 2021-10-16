import base.BaseTest;
import com.google.common.io.ByteArrayDataInput;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MielofonIsMine extends BaseTest {

    private final static String URL_VW = "https://www.vw.com/";
    private WebDriver driver;

    @Test
    public void testVitalyZverevFirst()  {
        getDriver().get(URL_VW);
        getDriver().findElement(By.xpath("//button[contains(@class, 'StyledTopBarButton')]")).click();
        WebElement menu = getDriver().findElement(By.xpath("//div[contains(@class, 'StyledMainNavigationAreaInner')]/div[contains(@class, 'StyledTextComponent')]"));
        getWait().until(ExpectedConditions.visibilityOf(menu));
        getDriver().findElement(By.xpath("//a[@title='Models']")).click();
        WebElement section = getDriver().findElement(By.id("MOFA"));
        getWait().until(ExpectedConditions.visibilityOf(section));
        List<WebElement> listElementsModels = getDriver().findElements(By.xpath("//h3[contains(@class, 'StyledTextComponent')]"));
        List<String> listModels = new ArrayList<>();

        for (WebElement element : listElementsModels) {
            listModels.add(element.getText());
        }

        Assert.assertTrue(listModels.contains("Passat"));
        Assert.assertEquals(listModels.size(), 15);
    }

    @Test
    public void testVitalyZverevSecond()  {
        getDriver().get(URL_VW);
        getDriver().findElement(By.xpath("//a[@title='Learn More ']")).click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-labelledby='Input_zip_modal']")));

        WebElement zip = getDriver().findElement(By.xpath("//input[@label='Enter your ZIP code']"));
        zip.clear();
        zip.sendKeys("10019\n");

        WebElement firstCost = getDriver().findElement(By.xpath("//div[text()='Starting MSRP']/parent::div/following-sibling::div/div"));
        getWait().until(ExpectedConditions.visibilityOf(firstCost));

        List<WebElement> secondCost = getDriver().findElements(By.xpath("//sup[contains(@class, 'StyledTextComponent')]/parent::div"));
        scroll(getDriver(), secondCost.get(0));

        String first = firstCost.getText().replaceAll("[^0-9]", "").substring(0, 5);
        String second = secondCost.get(0).getText().replaceAll("[^0-9]", "").substring(0, 5);

        Assert.assertEquals(first, second);
    }

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
    public void testSearchAnastasiaKaz() {

        driver.get("https://rp5.by");

        WebElement search = driver.findElement(By.id("searchStr"));
        WebElement button = driver.findElement(By.id("searchButton"));

        search.sendKeys("лида");
        button.click();

        WebElement text = driver.findElement(By.id("leftNavi"));
        Assert.assertEquals(text.getText(), "Результат поиска");
    }

    @Test
    public void testLogotipAnastasiaKaz() {

        driver.get("https://rp5.by");

        WebElement search = driver.findElement(By.id("searchStr"));
        WebElement button = driver.findElement(By.id("searchButton"));

        search.sendKeys("лида");
        button.click();

        WebElement logotip = driver.findElement(By.id("logo"));
        Assert.assertTrue(logotip.isDisplayed());
    }

    @Test
    public void testFAQAnastasiaKaz() {
        driver.get("https://rp5.by");

        WebElement question = driver.findElement(By.xpath("//div[@id='topMenuContent']/a[5]"));
        question.click();

        WebElement lastQuestion = driver.findElement(By.xpath("//ol/li[9]/b"));
        Assert.assertEquals(lastQuestion.getText(), "Что означает определение \"обложной\" во фразах \"обложной дождь\" или \"обложной снег\"?");
    }
}
