import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

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

    //Verification of search result
    @Test
    public void testPavelSipatySearchResult() {

        driver.get("https://www.webstaurantstore.com/");

        WebElement search = driver.findElement(By.id("searchval"));
        search.sendKeys("stainless work table\n");

        List<WebElement> listOfTableDescriptions = driver.findElements(By.xpath("//a[@data-testid = 'itemDescription']"));
        if (!listOfTableDescriptions.isEmpty()) {
            WebElement element = listOfTableDescriptions.get(listOfTableDescriptions.size() - 1);
        }

        for (int i = 0; i < listOfTableDescriptions.size(); i++) {
            Assert.assertTrue(listOfTableDescriptions.get(i).getText().contains("Table"), "All found goods have word \"Table\" in description");
        }

    }

    //Verification of successful logging in
    @Test
    public void testPavelSipatyLogInSuccess() {

        String expectedUrl = "https://www.saucedemo.com/inventory.html";

        driver.get("https://www.saucedemo.com/");
        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");

        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
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
    public void AlexeyKhomozovRegionListCountTest() {

        driver.get(URL_IK);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-geolocation");

        WebElement changeRegionButton = driver.findElement(By.className("xfnew-header__change-region"));
        changeRegionButton.click();

        List<WebElement> regionsCount = driver.findElements(By.xpath(
                "//div/a[@class=\"xf-popup-polygons__region xf-popup-polygons__region-online\"]"));
        int actualRegionsCount = regionsCount.size();
        int expectedRegionsCount = 55; //ожидаемое на момент реализации теста

        Assert.assertEquals(actualRegionsCount, expectedRegionsCount);
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
    public void testArtsiomAzarankaTextBox() {
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

        Assert.assertEquals(name.getText(), "Name:" + FullName);
        Assert.assertEquals(email.getText(), "Email:" + UserEmail);
        Assert.assertEquals(currentAddress.getText(), "Current Address :" + CurrentAddress);
        Assert.assertEquals(permanentAddress.getText(), "Permananet Address :" + PermanentAddress);
        //тест написан на тот результат, который выдаёт сайт (есть баг - ошибка в слове Permanent на выводе результата)


    }

    @Test
    public void testArtsiomAzarankaButtons() {

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

        Assert.assertEquals(doubleClickMessage.getText(), "You have done a double click");
        Assert.assertEquals(rightClickMessage.getText(), "You have done a right click");
        Assert.assertEquals(dynamicClickMessage.getText(), "You have done a dynamic click");

    }

    @Test
    public void testNataliaSavinovaContactUsButton() {
        driver.get(MAIN_PAGE_URL);
        driver.findElement(By.xpath("//a[@title = 'Contact Us']")).click();

        WebElement result = driver.findElement(By.xpath("//h1[@class = 'page-heading bottom-indent']"));

        Assert.assertEquals(result.getText(), "CUSTOMER SERVICE - CONTACT US");
    }

    @Test
    public void testNataliaSavinovaSignInButton() {
        driver.get(MAIN_PAGE_URL);
        driver.findElement(By.xpath("//a[@title = 'Log in to your customer account']")).click();

        WebElement result = driver.findElement(By.xpath("//h1[@class = 'page-heading']"));

        Assert.assertEquals(result.getText(), "AUTHENTICATION");
    }

    @Test
    public void testNataliaSavinovaSaleLink() {
        driver.get(MAIN_PAGE_URL);
        driver.findElement(By.xpath("//div[@id = 'htmlcontent_top']//li[@class = 'htmlcontent-item-1 col-xs-4']")).click();

        String result = driver.getCurrentUrl();

        Assert.assertEquals(result, "https://www.prestashop.com/en");
    }

    @Test
    public void NadezdhaDekhandLogo() {
        driver.get("https://gb.ru/");

        WebElement link = driver.findElement(By.className("mn-header__logo-link"));
        link.click();
        assertEquals(driver.getCurrentUrl(), "https://gb.ru/");
    }

    @Test
    public void NadezdhaDekhandEnter() {
        driver.get("https://gb.ru/");

        WebElement userPlan = driver.findElement(By.xpath("//div[contains(@class,'mn-header__left')]//a[text()='Мероприятия']"));
        userPlan.click();
        WebElement privat = driver.findElement(By.linkText("Личные консультации"));
        privat.click();
        WebElement form = driver.findElement(By.linkText("Записаться"));
        form.click();
        WebElement name1User = driver.findElement(By.id("full_name-3"));
        WebElement name2User = driver.findElement(By.id("full_name-4"));
        WebElement emailUser = driver.findElement(By.id("email-2"));
        WebElement phoneUser = driver.findElement(By.id("phone-2"));
        WebElement login = driver.findElement(By.cssSelector("#wf-form-email-form > input.submit-button.w-button"));
        name1User.sendKeys("gdhghd");
        name2User.sendKeys("fgdrgtr");
        emailUser.sendKeys("abc@gmail.com");
        phoneUser.sendKeys("9999999999");


        login.click();
        assertEquals(driver.getCurrentUrl(), "https://gb.ru/events/personal-consultation#form");
    }

    @Test
    public void testSearchAndreiShupaev() {
        driver.get("http://automationpractice.com/");
        WebElement input = driver.findElement(By.id("search_query_top"));
        input.sendKeys("dress\n");
        WebElement dress = driver.findElement(By.className("lighter"));

        Assert.assertEquals(dress.getText(), "\"DRESS\"");

    }

    @Test
    public void testContactUsAndreiShupaev() {
        driver.get("http://automationpractice.com/");
        WebElement contactUs = driver.findElement(By.id("contact-link"));
        contactUs.click();

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("email@gmail.com");

        WebElement order = driver.findElement(By.id("id_order"));
        order.sendKeys("35");

        WebElement message = driver.findElement(By.id("message"));
        message.sendKeys("I have a problem!");

        WebElement buttonSend = driver.findElement(By.id("submitMessage"));
        buttonSend.click();

        WebElement error = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/ol/li"));
        Assert.assertEquals(error.getText(), "Please select a subject from the list provided.");

    }
    @Test
    public void testLogInWrongCredentialsAlexKapran () {

        driver.get("https://www.theperfectloaf.com/");
        driver.findElement(By.xpath("//a[@class='tpl-membership__button']" +
                "/span[@class='tpl-membership__arrow']")).click();

        driver.switchTo().frame("memberful-iframe-for-overlay");

        driver.findElement(By.xpath("//div[@class='mt-4 text-center']/button[@class='underline']")).click();
        driver.findElement(By.xpath("//div[@class='mb-3']" +
                "/input[@placeholder='Email']")).sendKeys("markus.lorg@gmail.com");
        driver.findElement(By.xpath("//div[@class='mb-3']" +
                "/input[@placeholder='Password']")).sendKeys("QA_Hamster1!");

        driver.findElement(By.xpath("//div[@data-display-if-target='#session_mode']/input[@value='Sign in']")).click();

        WebElement error = driver.findElement(By.xpath("//div[@class='error_explanation']/p"));
        Assert.assertEquals(error.getText(), "Wrong email or password.");
    }
    @Test
    public void testSearchAlexKapran(){
        driver.get("https://www.theperfectloaf.com/");
        driver.findElement(By.xpath("//a[@class='search_icon']/i[@class='fa fa-search search__icon']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Bread\n");

        List<WebElement> searchList = driver.findElements(By.xpath("//div[@class='ais-hits--item']/" +
                "/h2[@itemprop='name headline']//a[contains(@title,'Bread')]"));
        for (int i = 0; i < searchList.size(); i++){
            Assert.assertTrue(searchList.get(i).getText().toLowerCase().contains("bread"));
        }
    }

    @Test
    public void testErrorPasswordBabkinaKatya(){

        driver.get("https://pravoved.ru/");

        WebElement loginButton = driver.findElement(By.linkText("Войти"));

        loginButton.click();


        WebElement username = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginSubmit = driver.findElement(By.id("loginSubmit"));


        username.sendKeys("abc@gmail.com");
        password.sendKeys("yourPassword");
        loginSubmit.click();

        WebElement error = driver.findElement(By.xpath("//div[@id=\"wrapper\"]//*[contains(@class,\"errors\")]/li[2]"));

        Assert.assertEquals(error.getText(), "Неправильная пара логин-пароль!\n" + "Авторизоваться не удалось.");
    }

    @Test
    public void testSearchBabkinaKatya() {

        driver.get("https://pravoved.ru/");

        WebElement questionButton = driver.findElement(By.xpath("//div[@class='Header_navigation__1am_z']//a[@href='/questions/']"));

        questionButton.click();

        driver.findElement(By.id("questions-page-search")).sendKeys("договор\n");
        List<WebElement> itemList = driver.findElements(By.xpath("//div[contains(@class, 'prvd-questions-list')]//div[@class='divH3']/a"));
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase(Locale.ROOT).contains("договор"));
        }
    }
}