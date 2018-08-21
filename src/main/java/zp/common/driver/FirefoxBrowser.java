package zp.common.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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

public class FirefoxBrowser {
    private static final Logger logger = LoggerFactory.getLogger(FirefoxBrowser.class);
    public static WebDriver buildFirefoxBrowser(){
        //System.setProperty("webdriver.gecko.driver", Paths.get(".").toAbsolutePath().
        //        normalize().toString() + "/drivers/geckodriver/geckodriver.exe");
        WebDriver driver = null;
        try {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("platform",System.getProperty("platform"));
            driver = new RemoteWebDriver(new URL(System.getProperty("node") + "/wd/hub"), firefoxOptions);
            driver.manage().timeouts().implicitlyWait(Constants.SHORT_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } catch (MalformedURLException e) {
            logger.error("Incorrect URL");
            e.printStackTrace();
        }
        return driver;
    }
}
