package zp.bdd;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zp.common.action.VerifyAction;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AnkitNigam on 07/04/2018.
 */
public class VerifyActionSteps {
    private static final Logger logger = LoggerFactory.getLogger(VerifyActionSteps.class);

    @Autowired
    private VerifyAction verifyAction;

    @Given("^\"([^\"]*)\" is displaying")
    public void verifyObjectExistsActionStep(String name) throws Exception {
        boolean flag = false;
        try {
            //Verify object available on page
            flag = verifyAction.verifyObjectExists(name);
            Assert.assertTrue("Unable to find the object: " + name, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Object not available", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in finding object: " + name, ex);
        }
    }


    @Then("^\"([^\"]*)\" as \"([^\"]*)\" displaying$")
    public void verifyTextDisplayingActionStep(String name, String value) throws Exception {
        boolean flag = false;
        try {
            flag = verifyAction.verifyTextDisplayingAction(name, value);
            Assert.assertTrue("Unable to find the find text: " + value, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Text not available", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in finding text: " + value, ex);
        }
    }

    @Given("^User on \"([^\"]*)\" page$")
    public void userOnPage(String pageName) throws Throwable {
        boolean flag = false;
        try {
            logger.info("Verifying the " + pageName);
            flag = verifyAction.verifyPageAndSetContext(pageName);
            Assert.assertTrue("Page not found: " + pageName, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Page not found: " + pageName, assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in finding Page: " + pageName, ex);
        }
    }

    @And("^\"([^\"]*)\" values are displaying as \"([^\"]*)\"$")
    public void valuesAreDisplayingAs(String object, String valuesAsList) throws Throwable {
        boolean flag = false;
        try {
            flag = verifyAction.verifyValuesInList(object, valuesAsList);
            Assert.assertTrue("Unable to find the values in list: " + valuesAsList, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Values not available", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in finding values: " + valuesAsList, ex);
        }
    }

    @And("^\"([^\"]*)\" highlighting with color \"([^\"]*)\"$")
    public void highlightingWithColor(String object, String color) throws Throwable {
        boolean flag = false;
        try {
            flag = verifyAction.verifyBGColor(object, color);
            Assert.assertTrue("Object is not highlighting", flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Object is not highlighting", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in finding object background", ex);
        }
    }

    @And("^Filters in query parameters are displaying as \"([^\"]*)\"$")
    public void filtersInQueryParametersAreDisplayingAs(String valuesAsList) throws Throwable {
        boolean flag = false;
        try {
            flag = verifyAction.verifyValuesInURLQueryParameters(valuesAsList);
            Assert.assertTrue("Unable to find the values in URL: " + valuesAsList, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Values not available in URL", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in finding values: " + valuesAsList, ex);
        }
    }
}

