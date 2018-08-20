package zp.common.action;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by AnkitNigam on 07/04/2018.
 */

@Component
public class TypeAction extends AbstractBaseAction implements IInputAction {
    private static final Logger logger = LoggerFactory.getLogger(TypeAction.class);

    @Override
    public boolean setInput(String name, String value) {
        boolean flag = false;
        try{
            logger.info("Enter text " + value + " in field " + name);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));
            element.sendKeys(value);
            flag = true;
            logger.debug("Successfully able to enter value: " + value);
        }catch (TimeoutException ex){
            logger.error("TimeoutException -  Unable to find textbox: " + name);
        }
        catch (Exception ex){
            logger.error("Error in entering a value in textbox: " + name);
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }
}
