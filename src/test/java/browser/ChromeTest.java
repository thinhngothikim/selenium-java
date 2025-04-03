package browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v133.emulation.Emulation;
import org.openqa.selenium.devtools.v133.network.Network;
import org.openqa.selenium.devtools.v133.network.model.ConnectionType;
import org.openqa.selenium.devtools.v133.performance.Performance;
import org.openqa.selenium.devtools.v133.performance.model.Metric;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChromeTest {
    @Test
    void openWithDefaultMode(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }

    @Test
    void openWithHeadlessMode(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }

    @Test
    void openWithMobileViewPort(){
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 430);
        deviceMetrics.put("height", 932);
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        //driver.quit();
    }

    @Test
    void openWithOldChromeVersion(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("130");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();

    }

    @Test
    void openWithBetaChromeVersion(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("136");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();

    }

    @Test
    void openBrowserWithFakeGeoLocation(){
        WebDriver driver = new ChromeDriver();
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        // Mountain view
        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(37.386052),
                Optional.of(-122.083851),
                Optional.of(1)
        ));
        driver.get("https://the-internet.herokuapp.com/geolocation");
        driver.findElement(By.xpath("//button[.='Where am I?']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#lat-value")).getText(),"37.386052");
        Assert.assertEquals(driver.findElement(By.cssSelector("#long-value")).getText(),"-122.083851");

        driver.quit();
    }

    @Test
    void simulate3GNetworkCondition(){
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        // Enable Network emulation
        devTools.send(Network.enable(Optional.of(100000000),
                Optional.empty(),
                Optional.empty()));

        // Set network conditions to emulate 3G or 4G
        devTools.send(Network.emulateNetworkConditions(
                false,
                100,
                75000,
                25000,
                Optional.of(ConnectionType.CELLULAR3G),
                Optional.of(0),
                Optional.of(0),
                Optional.of(false)
        ));
        driver.get("https://selenium.dev");
    }

    @Test
    void openSeleniumHomePageAndCapturePerformanceMetrics(){
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));
        List<Metric> metricList = devTools.send(Performance.getMetrics());
        driver.get("https://selenium.dev");

        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();

        for(Metric m : metricList) {
            System.out.println(m.getName() + " = " + m.getValue());
        }
    }

    @Test
    void openBraveSiteWithDefaultMode(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://brave.com");
        Assert.assertEquals(driver.getTitle(),"The browser that puts you first | Brave");
        driver.quit();
    }
}
