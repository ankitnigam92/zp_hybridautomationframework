package zp.common.driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by AnkitNigam.
 */

public class BrowserFactory {
    private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class);
    public static WebDriver getBrowser()  {
        String browserName = System.getProperty("browser");
        WebDriver browserDriver = null;
        try {
            switch (browserName) {
                case "chrome":
                    browserDriver = ChromeBrowser.buildChromeBrowser();
                    break;
                case "firefox":
                    browserDriver = FirefoxBrowser.buildFirefoxBrowser();
                    break;
                default:
                    browserDriver = null;
                    logger.info("Please provide valid browser");
                    break;
            }
        }catch (Exception ex){
            logger.error("Error in getting browser driver");
            ex.printStackTrace();
        }
        return browserDriver;
    }
}
