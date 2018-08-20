package zp.bdd;

import cucumber.api.java.en.When;
import zp.common.action.TypeAction;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AnkitNigam on 07/04/2018.
 */

public class TypeActionSteps {
    @Autowired
    private TypeAction typeAction;

    @When("^User enter \"([^\"]*)\" as \"([^\"]*)\"$")
    public void setInput(String name, String value) throws Exception {
        boolean flag = false;
        try {
            flag = typeAction.setInput(name, value);
            Assert.assertTrue("Failed to enter value: " + value + " in field: " + name, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Text enter operation failed", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in entering the text for object: " + name, ex);
        }
    }
}
