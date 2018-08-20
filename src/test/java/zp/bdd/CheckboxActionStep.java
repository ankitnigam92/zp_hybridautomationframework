package zp.bdd;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zp.common.action.CheckboxAction;

public class CheckboxActionStep {
    private static final Logger logger = LoggerFactory.getLogger(CheckboxActionStep.class);
    @Autowired
    private CheckboxAction checkboxAction;

    @And("^User click \"([^\"]*)\" checkbox$")
    public void userClickCheckbox(String name) throws Throwable {
        boolean flag = false;
        try {
            logger.info("Clicking object " + name);
            flag = checkboxAction.click(name);
            Assert.assertTrue("Failed to Checkbox : " + name, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Checkbox operation failed", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in checking : " + name, ex);
        }
    }

    @And("^User click multiple \"([^\"]*)\" checkboxes$")
    public void userClickMultipleCheckboxes(String objects) throws Throwable {
        boolean flag = false;
        try {
            logger.info("Clicking objects " + objects);
            flag = checkboxAction.clickMultiCheckboxes(objects);
            Assert.assertTrue("Failed to click checkboxes : " + objects, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Checkbox operation failed", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in checking : " + objects, ex);
        }
    }
}
