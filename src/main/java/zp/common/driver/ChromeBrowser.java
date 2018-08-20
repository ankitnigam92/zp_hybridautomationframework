package zp.common.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zp.common.contants.Constants;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
/**
 * Created by AnkitNigam.
 */
public class ChromeBrowser {
    private static final Logger logger = LoggerFactory.getLogger(ChromeBrowser.class);
    public static WebDriver buildChromeBrowser(){
        //System.setProperty("webdriver.chrome.driver", Paths.get(".").toAbsolutePath().
        //        normalize().toString() + "/drivers/chromedriver/chromedriver.exe");
        WebDriver driver = null;
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("platform", Platform.WIN10);
            driver = new RemoteWebDriver(new URL(System.getProperty("node") + "/wd/hub"), chromeOptions);
            driver.manage().timeouts().implicitlyWait(Constants.SHORT_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } catch (MalformedURLException e) {
            logger.error("Incorrect URL");
            e.printStackTrace();
        }

        return driver;
    }
}
