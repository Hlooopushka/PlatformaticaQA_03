import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class JavaHamsters {

    private final String URL_IK = "https://www.vprok.ru/";
    private static final String MAIN_PAGE_URL = "http://automationpractice.com/index.php";

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
    @Test
    public void ArtsiomAzarankaTestTextBox() {
        driver.get("https://demoqa.com/text-box");

        String FullName = "Marko Polo";
        String UserEmail = "test@test.com";
        String CurrentAddress = "street";
        String PermanentAddress = "ulica";


        driver.findElement(By.xpath("//input[@placeholder='Full Name']")).sendKeys(FullName);
        driver.findElement(By.xpath("//input[@placeholder='name@example.com']")).sendKeys(UserEmail);
        driver.findElement(By.xpath("//textarea[@placeholder='Current Address']")).sendKeys(CurrentAddress);
        driver.findElement(By.xpath("//textarea[@id='permanentAddress']")).sendKeys(PermanentAddress);

        driver.findElement(By.xpath("//button[@id='submit']")).click();

        WebElement name = driver.findElement(By.xpath("//p[@id='name']"));
        WebElement email = driver.findElement(By.xpath("//p[@id='email']"));
        WebElement currentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']"));
        WebElement permanentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']"));

        Assert.assertEquals(name.getText(),"Name:"+ FullName);
        Assert.assertEquals(email.getText(),"Email:"+ UserEmail);
        Assert.assertEquals(currentAddress.getText(),"Current Address :"+ CurrentAddress);
        Assert.assertEquals(permanentAddress.getText(),"Permananet Address :"+ PermanentAddress);
        //тест написан на тот результат, который выдаёт сайт (есть баг - ошибка в слове Permanent на выводе результата)



    }

    @Test
    public void ArtsiomAzarankaTestButtons(){

        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/buttons");


        WebElement doubleclick = driver.findElement(By.xpath("//button[@id='doubleClickBtn']"));
        actions.doubleClick(doubleclick).perform();

        WebElement rightclick = driver.findElement(By.xpath("//button[@id='rightClickBtn']"));
        actions.contextClick(rightclick).perform();

        WebElement oneclicl = driver.findElement(By.xpath("//button[text()='Click Me']"));
        actions.click(oneclicl).perform();

        WebElement doubleClickMessage = driver.findElement(By.xpath("//p[@id='doubleClickMessage']"));
        WebElement rightClickMessage = driver.findElement(By.xpath("//p[@id='rightClickMessage']"));
        WebElement dynamicClickMessage = driver.findElement(By.xpath("//p[@id='dynamicClickMessage']"));

        Assert.assertEquals(doubleClickMessage.getText(),"You have done a double click");
        Assert.assertEquals(rightClickMessage.getText(),"You have done a right click");
        Assert.assertEquals(dynamicClickMessage.getText(),"You have done a dynamic click");

    }

    @Test
    public void testSavinovaContactUsButton() {
        driver.get(MAIN_PAGE_URL);
        driver.findElement(By.xpath("//a[@title = 'Contact Us']")).click();

        WebElement result = driver.findElement(By.xpath("//h1[@class = 'page-heading bottom-indent']"));

        Assert.assertEquals(result.getText(),"CUSTOMER SERVICE - CONTACT US");
    }

    @Test
    public void testSavinovaSignInButton() {
        driver.get(MAIN_PAGE_URL);
        driver.findElement(By.xpath("//a[@title = 'Log in to your customer account']")).click();

        WebElement result = driver.findElement(By.xpath("//h1[@class = 'page-heading']"));

        Assert.assertEquals(result.getText(),"AUTHENTICATION");
    }

    @Test
    public void testSavinovaSaleLink() {
        driver.get(MAIN_PAGE_URL);
        driver.findElement(By.xpath("//div[@id = 'htmlcontent_top']//li[@class = 'htmlcontent-item-1 col-xs-4']")).click();

        String result = driver.getCurrentUrl();

        Assert.assertEquals(result,"https://www.prestashop.com/en");
    }
}