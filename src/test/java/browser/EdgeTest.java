package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EdgeTest {
    @Test
    void openWithDefaultMode(){
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.selenium.dev");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }

    @Test
    void openWithHeadlessMode(){
         EdgeOptions edgeOptions = new EdgeOptions();
         edgeOptions.addArguments("--headless");
         WebDriver driver = new EdgeDriver(edgeOptions);
         driver.get("https://www.selenium.dev");
         Assert.assertEquals(driver.getTitle(),"Selenium");
         driver.quit();
    }
}
