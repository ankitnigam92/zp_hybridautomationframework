package zp.bdd;

import zp.common.action.NavigateAction;
import cucumber.api.java.en.Given;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AnkitNigam.
 */
public class NavigationActionStep extends SpringCukesIntegration {

    @Autowired
    private NavigateAction navigateAction;

    @Given("^User navigate to the application home$")
    public void userNavigateToTheApplicationHome() throws Exception {
        boolean flag = false;
        try{
            String url = System.getProperty("applicationURL");
            if (!StringUtils.isEmpty(url)){
                flag = navigateAction.navigateToPage(url);
            }else {
                flag = false;
            }
            Assert.assertTrue("Unable to navigate to URL: " + url, flag);
        }catch (AssertionError assertionError){
            throw new Exception("Navigation to url failed", assertionError);
        }catch (Exception ex){
            throw new Exception("Error in navigating to url", ex);
        }
    }
}
