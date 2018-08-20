package zp.bdd;

import zp.common.action.ButtonAction;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AnkitNigam on 07/04/2018.
 */
public class ButtonActionSteps {
    @Autowired
    private ButtonAction buttonAction;

    @When("^Use click \"([^\"]*)\"$")
    public void click(String name) throws Exception {
        boolean flag = false;
        try {
            flag = buttonAction.click(name);
            Assert.assertTrue("Failed to click: " + name, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Click operation failed", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in clicking: " + name, ex);
        }
    }
}
