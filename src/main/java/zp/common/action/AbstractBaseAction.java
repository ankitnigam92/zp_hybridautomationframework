package zp.common.action;

import zp.common.context.IWebPageContext;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by AnkitNigam on 07/04/2018.
 */

@Component
public class AbstractBaseAction {
    @Autowired
    protected IWebPageContext context;
    protected WebDriverWait wait;
    protected WebDriverWait longWaits;

    @PostConstruct
    private void setWait(){
        this.wait = context.getWait();
        this.longWaits = context.getLongWait();
    }
}
