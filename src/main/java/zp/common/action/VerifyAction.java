package zp.common.action;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zp.common.utils.ZPHelper;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by AnkitNigam.
 */

@Component
public class VerifyAction extends AbstractBaseAction {
    private static final Logger logger = LoggerFactory.getLogger(VerifyAction.class);
    @Autowired
    private ZPHelper zpHelper;

    public boolean verifyObjectExists(String name){
        boolean flag = false;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));
            logger.info("Object found: " + name);
            flag = true;
        }catch (TimeoutException e){
            logger.error("TimeoutException - Object not found: " + name);
        }catch (Exception ex){
            logger.error("Error in finding object " + name);
        }finally {
            return flag;
        }
    }


    public boolean verifyTextDisplayingAction(String name, String expectedValue) {
        boolean flag = false;
        String actualValue = null;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));
            logger.info("Textbox found: " + name);
            actualValue = element.getText().trim();
            if (actualValue.equals(expectedValue)){
                logger.debug("Expected text found");
                flag = true;
            }else {
                logger.info("Expected text not found on the page");
                flag = false;
            }
        }catch (TimeoutException e){
            logger.error("TimeoutException - Object not found: " + name);
        }catch (Exception ex){
            logger.error("Error in finding object " + name);
        }finally {
            return flag;
        }
    }

    public boolean verifyPageAndSetContext(String pageName) {
        boolean flag = false;
        try {
            logger.info("Waiting of page to load...");
            context.waitForCurrentPageLoad();
            logger.info("Setting the page context as " + pageName);
            context.setContextCurrentPage(pageName);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(pageName)));
            logger.info("Page found: " + pageName);
            flag = true;
        }catch (TimeoutException e){
            logger.error("TimeoutException - Page not found: " + pageName);
            e.printStackTrace();
        }catch (Exception ex){
            logger.error("Error in finding Page " + pageName);
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }

    public boolean verifyValuesInList(String object, String valuesAsList) {
        boolean flag = false;
        String[] values;
        List<String> unMatchItems;
        try {
            if(zpHelper.waitTillSpinnerDisable("Spinner")) {


                List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(context.getElementLocator(object)));
                List<String> elementValues = elements.stream().map(x -> x.getText().trim()).collect(Collectors.toList());

                logger.info("List element count " + elements.size());
                values = valuesAsList.split(";");
                if (elements.size() == values.length) {
                    unMatchItems = Arrays.stream(values).filter(x -> !elementValues.contains(x)).collect(Collectors.toList());
                    if (unMatchItems.size() == 0) {
                        logger.info("All selected filters matched");
                        flag = true;
                    } else {
                        logger.info("Filters matching failed");
                        logger.info("Unamtched items: " + unMatchItems.toString());
                        flag = false;
                    }
                } else {
                    logger.info("Difference in count of filters selected and values expected");
                    flag = false;
                }
            }else {
                logger.info("Spinner is not closed");
                flag = false;
            }
        } catch (TimeoutException e) {
            logger.error("TimeoutException - Object not found: " + object);
        } catch (Exception ex) {
            logger.error("Error in finding object " + object);
        } finally {
            return flag;
        }
    }

    public boolean verifyBGColor(String object, String expectedBgColor) {
        boolean flag = false;
        String actualBgColor;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(object)));
            logger.info("Object found: " + object);
            actualBgColor = Color.fromString(element.getCssValue("background-color")).asHex();
            if (actualBgColor.equals(expectedBgColor)){
                logger.debug("Background highlighted");
                flag = true;
            }else {
                logger.info("Background is not highlighted");
                flag = false;
            }
        }catch (TimeoutException e){
            logger.error("TimeoutException - Object not found: " + object);
        }catch (Exception ex){
            logger.error("Error in finding object " + object);
        }finally {
            return flag;
        }
    }

    public boolean verifyValuesInURLQueryParameters(String valuesAsList) {
        boolean flag = false;
        String[] values;
        List<String> unMatchItems;
        try {
            String uri = context.getRealDriver().getCurrentUrl();
            List<NameValuePair> queryParameters = new URIBuilder(uri).getQueryParams();
            logger.info("Query Parameter count"+ queryParameters.size());

            List<String> removeValues = Arrays.asList("","relevance","flavour","type_toy");

            List<String> actualFilters = Pattern.compile(":")
                    .splitAsStream(URLDecoder.decode(queryParameters.get(0).getValue(),"UTF-8"))
                    .filter(x->!removeValues.contains(x))
                    .collect(Collectors.toList());


            values = valuesAsList.split(";");
            if(actualFilters.size()==values.length){
                unMatchItems = Arrays.stream(values).filter(x->!actualFilters.contains(x)).collect(Collectors.toList());
                if (unMatchItems.size()==0){
                    logger.info("All selected filters matched in URL");
                    flag = true;
                }else {
                    logger.info("Filters matching failed");
                    logger.info("Unamtched items: " + unMatchItems.toString());
                    flag = false;
                }
            }else {
                logger.info("Difference in count of filters selected and values expected");
                flag = false;
            }
        } catch (Exception ex) {
            logger.error("Error in finding query parameters");
            ex.getMessage();
        } finally {
            return flag;
        }
    }
}
