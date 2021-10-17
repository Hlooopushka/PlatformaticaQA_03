import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class QA_Group_Timur {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void ZhmakaAndrey() {
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.xpath
                ("//div[@id='block_top_menu']//a[@title='Women']")).click();
        driver.findElement(By.xpath
                ("//div[@class='product-image-container']/a[@title='Blouse']")).click();
        /*      Нажать на обьект buttonWomen без запуска iframe
         *      JavascriptExecutor jsx = (JavascriptExecutor)driver;
         *      jsx.executeScript("arguments[0].click()", buttonBlouse);
         */

        WebElement frame = driver.findElement(By.xpath
                ("//iframe[contains(@id,'fancybox-frame')]"));
        driver.switchTo().frame(frame);
        driver.findElement(By.xpath
                ("//p[@id='add_to_cart']/button[@class='exclusive']")).click();
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath
                ("//div[@class='button-container']/a[@title='Proceed to checkout']")).click();
        driver.findElement(By.xpath
                ("//div[@id='center_column']/p[contains(@class,cart_navigation)]/a[@title='Proceed to checkout']")).click();
        driver.findElement(By.xpath
                ("//form[@id='login_form']//input[@data-validate='isEmail']")).sendKeys("mail5432@22.com");
        driver.findElement(By.xpath
                ("//form[@id='login_form']//input[@data-validate='isPasswd']")).sendKeys("123456789");
        driver.findElement(By.xpath
                ("//form[@id='login_form']//button[@id='SubmitLogin']")).click();
        driver.findElement(By.xpath
                ("//div[@id='center_column']//button[@name='processAddress']")).click();
        WebElement checkboxTermsOfService = driver.findElement(By.id("cgv"));
        checkboxTermsOfService.click();
        if (checkboxTermsOfService.isSelected()) {
            driver.findElement(By.xpath
                    ("//form[@id='form']//button[@name='processCarrier']")).click();
        }
        driver.findElement(By.xpath
                ("//div[@id = 'HOOK_PAYMENT']//a[@class='bankwire']")).click();
        driver.findElement(By.xpath("//p[@id = 'cart_navigation']/button[@type = 'submit']")).click();
        WebElement orderComplete = driver.findElement(By.xpath("//p[@class = 'cheque-indent']/strong[@class = 'dark']"));
        
        Assert.assertEquals(orderComplete.getText(),"Your order on My Store is complete.");
    }

    @Test
    public void searcheTestKorelov()  {
        driver.get("https://petrovich.ru/");
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("газобетон");

        WebElement find = driver.findElement(By.xpath("//button[text()=\"Найти\"]"));
        find.click();

//        WebElement listList = driver.findElement(By.xpath("//*[@data-test=\"view-list\"]"));
//        listList.click();

        WebElement blockList = driver.findElement(By.xpath("//*[@data-test=\"view-block\"]"));
        blockList.click();

        List<WebElement> itemList = driver.findElements(By.xpath("//*[@data-test=\"product-title\"]"));
        int count = 0;
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains("газобетон"));
            System.out.println(itemList.get(i).getText());
            count++;

        }
        System.out.println(count);
    }

    @Test
    public void resetLoginKorelov() {
        driver.get("https://petrovich.ru/");
        WebElement enterButton = driver.findElement(By.xpath("//*[@data-test=\"login-link\"]"));
        enterButton.click();
        WebElement fogPass = driver.findElement(By.xpath("//a[text()=\"Забыли пароль?\"]"));
        fogPass.click();
        WebElement title = driver.findElement(By.xpath("//*[@class=\"form__title\"]"));
        title.getText();
        System.out.println(title.getText());
        Assert.assertEquals(title.getText(), "Восстановление пароля:");

    }

    @Test
    public void testFelix_IX() throws InterruptedException {
        driver.get("https://www.spirit-of-metal.com/");

        driver.findElement(By.id("search-toggle")).click();
        String text = "skepticism";
        driver.findElement(By.id("GlobalSearchField")).sendKeys(text + "\n");
        driver.findElement(By.xpath("//*[@id=\"actuality\"]/div/div/section[1]/div/ul/a")).click();
        Thread.sleep(500);

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 1430);");

        driver.findElement(By.xpath("//*[@id=\"infinitescroll\"]/div[5]/a")).click();

        WebElement q  = driver.findElement(By.xpath("//*[@id=\"profile\"]/div[2]/span[2]"));

        Assert.assertEquals(q.getText(), "Ordeal");
    }
}
