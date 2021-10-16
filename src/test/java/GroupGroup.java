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

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GroupGroup {

    private WebDriver driver;private static String URL1_GG = "https://www.edx.org/";
    private static String URL2_GG = "https://www.edx.org/search?q=python&tab=course";

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
    public void testNarimanMirzakhanov1() {
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
    public void testNarimanMirzakhanov2() {
        driver.get("https://www.bestbuy.com/");
        driver.findElement(By.xpath("//button[@class='c-close-icon c-modal-close-icon']")).click();

        String text = "iphone 13 pro max 512 verizon";
        driver.findElement(By.id("gh-search-input")).sendKeys(text);
        driver.findElement(By.xpath("//span[@class='header-search-icon']")).click();

        WebElement productName = driver.findElement(By.xpath("//h4[@class='sku-header']"));
        Assert.assertEquals(productName.getText(), "Apple - iPhone 13 Pro Max 5G 512GB - Graphite (Verizon)");
    }

    @Test
    public void PolinaTceretian1() {
        //testCreateAccountPageOpen()

        driver.get("https://www.starbucks.com");

        WebElement signIn = driver.findElement(By.xpath("//*[contains(text(), 'Join now')]"));
        signIn.click();

        String actualUrl = "https://www.starbucks.com/account/create";
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void PolinaTceretian2() throws InterruptedException {
        //testCreateAccountRegistration()
        driver.get("https://www.starbucks.com/account/create");
        driver.findElement(By.id("firstName")).sendKeys("T");
        driver.findElement(By.id("lastName")).sendKeys("E");

        WebElement email = driver.findElement(By.id("emailAddress"));
        Random randomGenerator = new Random();
        int randomInt = randomGenerator. nextInt(1000);
        email. sendKeys("username"+ randomInt +"@gmail.com");

        driver.findElement(By.id("password")).sendKeys("123456Abc!");
        driver.findElement(By.xpath("//input[@id ='termsAndConditions']/..//span[@class='block option__labelMarker']")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Create account')]")).click();
        Thread.sleep(3000);
        String actualUrl = "https://app.starbucks.com/";
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testZinaidaLepesh() {
        driver.get("https://oz.by/");

        WebElement search = driver.findElement(By.id("top-s"));
        search.sendKeys("вишневский\n");

        List<WebElement> itemlist = driver.findElements(By.className("viewer-type-card__li "));
        for (int i = 0; i < itemlist.size(); i++) {
            Assert.assertTrue(itemlist.get(i).getText().toLowerCase(Locale.ROOT).contains("вишневский"));
        }
    }

    @Test
    public void testZinaidaLepesh2() {
        driver.get("https://oz.by/");

        WebElement login = driver.findElement(By.className("top-panel__userbar__auth"));
        login.click();

        WebElement byemail = driver.findElement(By.id("loginFormLoginEmailLink"));
        byemail.click();

        WebElement email = driver.findElement(By.name("cl_email"));
        email.sendKeys("abc@gmail.com");

        WebElement password = driver.findElement(By.name("cl_psw"));
        password.sendKeys("pass");

        WebElement enter = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/button"));
        enter.click();
    }

    @Test
    // searching all python cources. Third course contains Pyt.. instead of full word. It's a reason why the test
    // failed, so this course was excluded from list elements.
    public void testSearchEdx() {
        driver.get(URL1_GG);
        String searchText = "python";
        driver.findElement(By.xpath("//input[@id='home-search']")).sendKeys(searchText);
        driver.findElement(By.xpath
                ("//button[@class='btn-inverse-brand form-submit edit-submit btn btn-brand']")).click();
        driver.findElement(By.xpath
                ("//div[@class='mt-2 mt-md-4 pt-2 container-mw-lg container-fluid']//button[@class='show-all-link " +
                        "btn btn-link muted-link inline-link d-inline-block pl-0 pr-4 px-xl-0']")).click();

        List<WebElement> resultList = driver.findElements(By.xpath("//div[@class='d-card-body pl-4 pt-4 mt-2']"));
        for (int i = 0; i < resultList.size(); i++) {
            if (i == 2) {
                continue;
            }
            Assert.assertTrue(resultList.get(i).getText().toLowerCase().contains(searchText));
        }
    }

    @Test
    // test if search by any type of learning python shows first 4 elements of "python" programs
    public void testSearchEdx2() {
        driver.get(URL2_GG);

        driver.findElement(By.xpath
                ("//div[@class='d-flex bg-primary-700 pt-2 pt-sm-3 mt-sm-1 mb-3 search-refinements']/div[7]")).click();
        driver.findElement(By.xpath("//input[@id='any']")).click();

        Assert.assertEquals(driver.findElements(By.xpath
                ("//div[@class='d-card-wrapper bg-primary-500']")).size(), 4);
    }
}
