package zp.common.context;

import cucumber.api.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Created by AnkitNigam.
 */
public interface IWebPageContext {
    By getElementLocator(String var1);
    WebDriver getRealDriver();
    WebDriverWait getWait();
    WebDriverWait getLongWait();
    void waitForCurrentPageLoad() throws Exception;
    void setContextCurrentPage(String var1);
}
