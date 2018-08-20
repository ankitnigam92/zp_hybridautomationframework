package zp.common.context;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import zp.common.contants.Constants;
import zp.common.driver.DriverManager;
import zp.common.repository.DataNotFoundInRepoExecption;
import zp.common.repository.PageElement;
import zp.common.repository.RepositoryContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AnkitNigam on 07/04/2018.
 */
@Component
public class WebPageContext implements IWebPageContext {
    private static final Logger logger = LoggerFactory.getLogger(WebPageContext.class);

    @Autowired
    private RepositoryContext repository;
    private static WebDriverWait wait;
    private static WebDriverWait longWait;

    private ExpectedCondition<Boolean> document_readyState_toBeComplete =
            webDriver -> Boolean.valueOf(((JavascriptExecutor)DriverManager.getDriver()).executeScript("return document.readyState", new Object[0]).toString().equalsIgnoreCase("complete"));

    @Override
    public By getElementLocator(String elementName) {
        By locElement = null;
        try {
            PageElement element = this.repository.getElement(elementName);
            locElement = this.getLocator(element);
        } catch (DataNotFoundInRepoExecption dataNotFoundInRepoExecption) {
            dataNotFoundInRepoExecption.printStackTrace();
        }finally {
            return locElement;
        }
    }

    @Override
    public WebDriver getRealDriver() {
        return DriverManager.getDriver();
    }

    @Override
    public WebDriverWait getWait() {
        if (wait==null){
            wait = new WebDriverWait(DriverManager.getDriver(), Constants.WAIT_TIMEOUT);
        }
        return wait;
    }

    @Override
    public WebDriverWait getLongWait() {
        if (longWait==null){
            longWait = new WebDriverWait(DriverManager.getDriver(), Constants.LONG_TIMEOUT);
        }
        return longWait;
    }

    @Override
    public void waitForCurrentPageLoad() throws Exception {
        logger.debug("Waiting to load page");
        this.wait.until(this.document_readyState_toBeComplete);
    }

    @Override
    public void setContextCurrentPage(String pageName) {
        logger.debug("Set context as " + pageName);
        this.repository.setContextCurrentPage(pageName);
    }

    private By getLocator(PageElement element) throws DataNotFoundInRepoExecption {
        logger.debug("Locating element: " + element);
        if (element.getLocatorType().equalsIgnoreCase("name")){return By.name(element.getLocator());}
        else if (element.getLocatorType().equalsIgnoreCase("id")){return By.id(element.getLocator());}
        else if (element.getLocatorType().equalsIgnoreCase("xpath")){return By.xpath(element.getLocator());}
        else {throw new DataNotFoundInRepoExecption("Locator not found for webelement " + element);}
    }
}
