package zp.bdd;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import zp.common.action.SelectAction;

/**
 * Created by AnkitNigam.
 */
public class SelectActionSteps {
    private static final Logger logger = LoggerFactory.getLogger(SelectActionSteps.class);

    @Autowired
    private SelectAction selectAction;

    @When("^User select \"([^\"]*)\" as \"([^\"]*)\"$")
    public void userSelectAsCountry(String name, String value) throws Throwable {
        boolean flag = false;
        try {
            logger.info("Select " + name + " as " + value);
            flag = selectAction.setInput(name, value);
            Assert.assertTrue("Failed to select filter: " + name + " for value: " + value, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Filter operation failed", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in filtering for object: " + name, ex);
        }
    }
}
