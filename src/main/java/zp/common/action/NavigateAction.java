package zp.common.action;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import zp.common.driver.DriverManager;

import java.util.concurrent.TimeUnit;

/**
 * Created by AnkitNigam.
 */
@Component
public class NavigateAction {
    private static final Logger logger = LoggerFactory.getLogger(NavigateAction.class);

    //Open URL in browser
    public boolean navigateToPage(String url){
        boolean flag = false;
        try{
            WebDriver driver = DriverManager.getDriver();
            logger.info("Application URL: " + url);
            driver.get(url);
            flag = true;
        }catch (Exception ex){
            logger.error("Error in navigating to URL: "+ url);
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }
}
