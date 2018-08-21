package zp.common.action;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zp.common.utils.ZPHelper;


/**
 * Created by AnkitNigam.
 */
@Component
public class CheckboxAction extends AbstractBaseAction implements IClickAction {
    private static final Logger logger = LoggerFactory.getLogger(CheckboxAction.class);
    @Autowired
    private ZPHelper zpHelper;

    public boolean clickMultiCheckboxes(String objects) {
        boolean flag = false;
        try {
            logger.info("Checking objects " + objects);
            String[] arrObjects = objects.split(";");
            for (String obj:arrObjects){
                flag = click(obj);
                if (!flag ){
                    logger.info("Unable to check object: " + obj);
                    break;
                }
            }
        } catch (Exception ex) {
            logger.error("Error in checking the objects");
            ex.printStackTrace();
        } finally {
            return flag;
        }
    }

    @Override
    public boolean click(String name) {
        WebElement element = null;
        //Long startTime = System.currentTimeMillis();
        boolean flag = false;
        try {
            //To solve org.openqa.selenium.WebDriverException: unknown error - element is not clickable at point
            logger.info("Getting element");
            element = wait.ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.elementToBeClickable(context.getElementLocator(name)));
            logger.info("Object " + name + " found");

            JavascriptExecutor jse2 = (JavascriptExecutor)context.getRealDriver();
            jse2.executeScript("arguments[0].scrollIntoView()", element);

            logger.info("Clicking the checkbox");
            jse2.executeScript("arguments[0].click();", element);
            if(zpHelper.waitTillSpinnerDisable("Spinner")){
                flag = true;
            }
//            while ((System.currentTimeMillis() - startTime) < Constants.STALE_ELEMENT_WAIT_TIMEOUT * 1000L) {
//                try {
//                    element = wait.until(ExpectedConditions.elementToBeClickable(context.getElementLocator(name)));
//                    ((JavascriptExecutor) context.getRealDriver()).executeScript("window.scrollTo(0,"+ element.getLocation().x+")");
//                    if (element.isEnabled() && element.isDisplayed()) {
//                        Highlight.elementHighlight(context.getRealDriver(), element);
//                        logger.info("Object " + name + " found");
//                        element.click();
//                        logger.info("Object " + name + " clicked");
//                        flag = true;
//                        break;
//                    } else {
//                        element = null;
//                        logger.debug("Waiting for the " + name + " to load...");
//                    }
//                } catch (StaleElementReferenceException e) {
//                    logger.debug("Stale Exception: Trying to recover it...");
//                } catch (TimeoutException e) {
//                    logger.debug("Timeout Exception: Unable to find object: " + name);
//                }
//            }
        }catch (TimeoutException ex){
            logger.debug("Timeout Exception: Unable to find object: " + name);
            ex.printStackTrace();
        }catch (Exception ex) {
            logger.error("Error in clicking the object: " + name);
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }
}
