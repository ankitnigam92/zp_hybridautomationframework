package zp.common.action;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by AnkitNigam.
 */
@Component
public class SelectAction extends AbstractBaseAction implements IInputAction {
    private static final Logger logger = LoggerFactory.getLogger(SelectAction.class);

    @Override
    public boolean setInput(String name, String value) {
        boolean flag = false;
        try {
            logger.info("Select dropdown " + name + " as " + value);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));
            Select dropdown = new Select(element);
            dropdown.selectByValue(value);
            flag = true;
            logger.debug("Successfully able to select value: " + value);
        } catch (TimeoutException ex) {
            logger.error("TimeoutException -  Unable to find object: " + name);
        } catch (Exception ex) {
            logger.error("Error in selecting a value from dropdown " + name);
            ex.printStackTrace();
        } finally {
            return flag;
        }
    }

}
