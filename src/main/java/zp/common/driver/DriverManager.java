package zp.common.driver;

import cucumber.api.Scenario;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static zp.common.driver.BrowserFactory.getBrowser;

/**
 * Created by AnkitNigam.
 */

public class DriverManager extends EventFiringWebDriver {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final WebDriver REAL_DRIVER;
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.close();
            REAL_DRIVER.quit();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
        try {
            logger.info("Getting the browser driver");
            REAL_DRIVER = getBrowser();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new Error(throwable);
        }
    }

    public DriverManager() {
        super(REAL_DRIVER);
    }

    public static WebDriver getDriver(){
        return REAL_DRIVER;
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("Do not close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    @Before
    public void deleteAllCookies() {
        logger.info("Deleting all cookies");
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if(scenario.isFailed()) {
            try {
                byte[] screenshot = getScreenshotAs(OutputType.BYTES);
                String testName = scenario.getName();
                scenario.embed(screenshot, "image/png");
                scenario.write(testName);
            } catch (WebDriverException ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}
