package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookStoreHomePage {

    WebDriver driver;

    @FindBy(xpath = "//div[@class='main-header']")
    WebElement header;

    @FindBy(xpath = "//input[@placeholder='Type to search']")
    WebElement searchField;

    @FindBy(xpath = "//div[@role='rowgroup']")
    List<WebElement> books;

    @FindBy(xpath = "//option[@value='5']")
    WebElement rows5;

    @FindBy(xpath = "//option[@value='100']")
    WebElement rows100;


    public BookStoreHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String getHeaderText() {
        return header.getText();
    }

    public void setSearchField(String text) {
        searchField.sendKeys(text);
    }

    public List<WebElement> getBooks() {
        return books;
    }

    public void setRowsPerPage(int size) {
        String path = String.format("//option[@value='%s']", size);
        driver.findElement(By.xpath(path)).click();
    }

}
