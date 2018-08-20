package zp.common.utils;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import zp.common.action.AbstractBaseAction;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import zp.common.contants.Constants;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by AnkitNigam.
 */
@Component
public class ZPHelper extends AbstractBaseAction {
    private static Logger logger = LoggerFactory.getLogger(ZPHelper.class);

    public boolean waitTillSpinnerDisable(String name){
        boolean flag = false;
        try {
               flag = longWaits.ignoring(StaleElementReferenceException.class)
                       .ignoring(NoSuchElementException.class)
                       .pollingEvery(100,TimeUnit.MILLISECONDS)
                       .until(ExpectedConditions.invisibilityOfElementLocated(context.getElementLocator("Spinner")));
        }catch (TimeoutException ex){
            logger.error("TimeoutException - Spinner is displaying for more than wait time");
        }catch (Exception e){
            logger.error("Error in waiting for spinner to hide");
            e.printStackTrace();
        }
        return flag;
    }
}
