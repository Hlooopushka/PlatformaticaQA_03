package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Group_eat_and_drink_Java {
    // ----  ARANGE  ----
    private WebDriver driver;
    String MainUrl = "https://askent.ru/";
    String expectedResultURLCorrectItem = "https://askent.ru/cat/sumki/ryukzak_63/";
    String expectedResultURLCabinet = "https://askent.ru/order/";
    String expectedResultSingIn = "Не верный логин или пароль";

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

    @Test(description = "Some description @Test-annotation for practice", priority = 1)
    // Для практики в аннотацию добавлены атрибуты
    public void testFindCorrectItem() {

        driver.get(MainUrl);
        WebElement bags = driver.findElement(By.xpath("//a[@href=\"https://askent.ru/cat/zhenskoe/filter/kollektsiya-is-9b65b8c3-fe04-11e8-80be-18a905775a6f/apply/\"]"));
        bags.click();

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END); // scrollDown page
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // выставляю паузу, т.к. кнопка 'Показать ещё' появляется не сразу.

        WebElement buttonMoreItems = driver.findElement(By.xpath("//a[contains(text(),'Показать ещё')]"));
        buttonMoreItems.click();
        WebElement backPack = driver.findElement(By.xpath("//div[@class=\"productItem__link\"]/a[@href=\"/cat/sumki/ryukzak_63/\"]"));
        backPack.click();

        // ---- ASSERT ----
        Assert.assertEquals(driver.getCurrentUrl(), expectedResultURLCorrectItem);
    }

    @Test(priority = 2) // приоритетность для практики, позволяет выставить очередность тестов.
    public void testItemToBasket() {

        driver.get("https://askent.ru/cat/sumki/ryukzak_63/");

        WebElement blackColor = driver.findElement(By.xpath("//div[@class='productCols']//div[@class='product__colorBlock']//div[3]//div[1]"));
        blackColor.click(); // выбираю товар другого цвета
        WebElement buttonToBasket = driver.findElement(By.xpath("//div[@id='fixed_block']//span[contains(text(),'Добавить в корзину')]"));
        buttonToBasket.click(); // добавляю товар в корзину
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // выставляю паузу т.к. кнопка 'Добавить в корзину'
        // меняется на кнопку 'Перейти в корзину' не сразу
        WebElement buttonGoToBasket = driver.findElement(By.xpath("//div[contains(text(),'Перейти в корзину')]"));
        buttonGoToBasket.click();
        WebElement addOneItem = driver.findElement(By.xpath("//div[@class='basketItem__add']"));
        addOneItem.click(); // в корзине увеличиваю кол-во товаров на 1 больше.
        driver.navigate().refresh(); // на странице баг, чтобы его обойти обновляю страницу.
        WebElement goToOrder = driver.findElement(By.xpath("//button[contains(text(),'Продолжить оформление заказа')]"));
        goToOrder.click();

        // ---- ASSERT ----
        Assert.assertEquals(driver.getCurrentUrl(), expectedResultURLCabinet);
    }

    @Test(priority = 3)
    public void testSingIn() {

        driver.get("https://askent.ru/order/");

        WebElement userLogin = driver.findElement(By.xpath("//div[@class='items_block']//input[@name='USER_LOGIN']"));
        userLogin.sendKeys("ziz@gmail.com");
        WebElement userPassword = driver.findElement(By.xpath("//div[@class='items_block']//input[@name='USER_PASSWORD']"));
        userPassword.sendKeys("1111AAAAaaaa");
        WebElement signIn = driver.findElement(By.xpath("//a[@class='control_link'][contains(text(),'Войти')]"));
        signIn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement message = driver.findElement(By.xpath("//*[contains(text(),'Не верный логин или пароль')]"));

        // ---- ASSERT ----
        Assert.assertEquals(message.getText(), expectedResultSingIn);
    }

    @Test
    public void testOlenaKSearches() {
        driver.get("https://www.kobo.com/");
        String bookName = "Harry Potter";

        WebElement searchField = driver.findElement(By.name("query"));
        searchField.sendKeys(bookName + "\n");

        List<WebElement> itemList = driver.findElements(By.xpath("//h2[@class='title product-field']/child::a"));
        for (WebElement item : itemList) {
            Assert.assertTrue(item.getText().toLowerCase(Locale.ROOT).
                    contains(bookName.toLowerCase(Locale.ROOT)));
        }
    }

    @Test
    public void testOlenaKFindByIsbn() {
        driver.get("https://www.kobo.com/");
        String numberIsbn = "9781781103326";
        String expectedResult = "Harry Potter en de Steen der Wijzen";

        WebElement searchField = driver.findElement(By.name("query"));
        searchField.sendKeys(numberIsbn + "\n");

        WebElement bookTitle = driver.findElement(By.xpath("(//h2[@class = 'title product-field'])[1]"));
        String actualResult = bookTitle.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

}
