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

public class QA_Group_Timur_Test{
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
 //       driver.quit();
    }

    @Test
    public void evgenyRogoznev(){
        driver.get("https://hh.ru/");
        WebElement signIn = driver.findElement(By.xpath("//*[@data-qa='login']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();", signIn);
        driver.findElement(By.xpath("//*[@data-qa='account-signup-email']")).sendKeys("falseLogin");
        WebElement submitBtn = driver.findElement(By.xpath("//*[@data-qa='account-signup-submit']"));
        submitBtn.click();
        List<WebElement> errors= driver.findElements(By.xpath("//*[text()='Пожалуйста, укажите email или телефон']"));
        Assert.assertEquals(errors.size(),1,
                "Сообщение с ошибкой \"Пожалуйста, укажите email или телефон\" отсутствует или их несколько.");

    };

}
