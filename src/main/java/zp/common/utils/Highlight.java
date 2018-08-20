package zp.common.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by AnkitNigam on 07/04/2018.
 */
public class Highlight {
    public static void elementHighlight(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 3px solid red;");
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
    }
}
