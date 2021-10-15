import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MielofonIsMine extends BaseTest {

    @Test
    public void testVitalyZverevFirst()  {
        getDriver().get("https://www.vw.com/");
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
}
