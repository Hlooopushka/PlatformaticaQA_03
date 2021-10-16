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

public class GroupJavaWestCoast {

    private WebDriver driver;

    private static final By SCOREBUTTON = By.xpath("//div[@class='ml-10px is-inline']");
    private static final By HONOR = By.xpath("//b[text()='Honor:']/..");
    private static final By RANKFIELD = By.xpath("//b[text()=\"Rank:\"]/..");
    private static final By OVERALLFIELD = By.xpath("//b[text()=\"Overall:\"]/..");
    private static final By LOGINBUTTON = By.xpath("//a[text()='Log In']");
    private static final By EMAILFIELD = By.id("user_email");
    private static final By PASSWORDFIELD = By.id("user_password");
    private static final By SIGNINBUTTON = By.xpath("//button[text()='Sign in']");

    public void signInMethodIliaP(){
        driver.findElement(LOGINBUTTON).click();
        driver.findElement(EMAILFIELD).sendKeys("testing@testing.com");
        driver.findElement(PASSWORDFIELD).sendKeys("Test88");
        driver.findElement(SIGNINBUTTON).click();
    }

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void setDown(){
        driver.quit();
    }


    @Test
    public void testIliaP() {

        driver.get("https://www.codewars.com/");

        signInMethodIliaP();

        WebElement scoreButton = driver.findElement(SCOREBUTTON);
        String actualValue = "Honor:" + scoreButton.getText();
        scoreButton.click();

        WebElement honor = driver.findElement(HONOR);
        String expectedValue = honor.getText();

        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void testIliaPTwo(){

        driver.get("https://www.codewars.com/");

        signInMethodIliaP();

        driver.findElement(SCOREBUTTON).click();

        WebElement rankField = driver.findElement(RANKFIELD);
        String expectedValue = rankField.getText();
        String resultExpected = "";
        for(int i = 0;i<expectedValue.length();i++){
            if (expectedValue.charAt(i)==':'){
                resultExpected += expectedValue.charAt(i+1);
            }
        }
        WebElement overallField = driver.findElement(OVERALLFIELD);
        String actualValue = overallField.getText();
        String resultActual = "";
        for(int i = 0;i<actualValue.length();i++){
            if (actualValue.charAt(i)==':'){
                resultActual += actualValue.charAt(i+1);
            }
        }
        Assert.assertEquals(resultActual,resultExpected);
    }
    @Test
    public void testYelenaAnderson() throws InterruptedException {

        String expectedResult = "https://www.asos.com/us/";

        driver.get("https://www.asos.com/us/");

        Thread.sleep(3000);

        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testYelenaAnderson1() {

        driver.get("https://www.asos.com/us/");

        WebElement searchBar = driver.findElement(By.xpath("//input[@type='search']"));

        searchBar.sendKeys("skirt\n");

        WebElement confirmText = driver.findElement(By.xpath("//p[contains(text(),'skirt')]"));
        String confirmTextText = confirmText.getText();
        Assert.assertEquals(confirmTextText, "\"Skirt\"");
    }
}



