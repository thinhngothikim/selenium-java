package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormAuthenticationTest {


        /*
            Open browser
            Navigate to https://the-internet.herokuapp.com/login
            Fill in username with tomsmith
            Fill in the password with SuperSecretPassword!
            Click on Login button
            And the home page is appear */
        @Test
        void tc01() {
            WebDriver driver = new ChromeDriver();
            driver.get("https://the-internet.herokuapp.com/login");

            driver.findElement(By.id("username")).sendKeys("tomsmith");

            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");
            driver.quit();

        }
}
