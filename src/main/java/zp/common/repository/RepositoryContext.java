package zp.common.repository;

/**
 * Created by AnkitNigam on 07/04/2018.
 */
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Data
public class RepositoryContext {
    private static final Logger logger = LoggerFactory.getLogger(RepositoryContext.class);
    private String contextCurrentPage = "Home";
    private Jaxb2Marshaller jax2Marshaller;
    private Resource repoPath;
    private WebRepository webRepository;

    public void loadRepository(){
        try {
            logger.debug("Load Repository - Start");
            logger.debug("Repository Path: " + this.repoPath);
            logger.debug("jaxbMarshaller: : " + this.jax2Marshaller);
            StreamSource io = new StreamSource(new FileInputStream(this.repoPath.getFile()));
            this.webRepository = (WebRepository)this.jax2Marshaller.unmarshal(io);
            logger.debug("webRepository: " + this.webRepository);
            logger.debug("Load Repository - End");

        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public WebPage getWebPage(String pageName) throws DataNotFoundInRepoExecption {
        Iterator itr = this.webRepository.getWebPages().iterator();
        WebPage page;
        do{
            if(!itr.hasNext()){
                logger.error(pageName + "page not fount in repository xml");
                throw new DataNotFoundInRepoExecption(pageName + "page not fount in repository xml");
            }

            page = (WebPage)itr.next();
        }while(!page.getPageName().equals(pageName));

        return page;
    }

    public PageElement getElement(String pageName, String elmName){
        PageElement element = null;
        try {
            element = this.getWebElement(pageName, elmName);
            logger.debug("Element " + elmName + " found on page " + pageName);
        }catch (DataNotFoundInRepoExecption ex){
            logger.warn(ex.getMessage());
            logger.warn("Searching element " + elmName + " not found on page " + pageName);
        }
        return element;
    }

    public PageElement getElement(String elementName){
        return this.getElement(this.contextCurrentPage, elementName);
    }

    public PageElement getWebElement(String pageName, String elmName) throws DataNotFoundInRepoExecption {
        Iterator itr = this.getWebPage(pageName).getWebPageElement().getWebElements().iterator();
        PageElement element;

        do{
            if(!itr.hasNext()){
                if(!pageName.equalsIgnoreCase("home")){
                    pageName = "Home";
                    itr = this.getWebPage(pageName).getWebPageElement().getWebElements().iterator();
                }else {
                    logger.error(elmName + " not found in repository");
                    throw new DataNotFoundInRepoExecption(elmName + " not found in repository");
                }
            }
            element = (PageElement)itr.next();
        }while (!element.getElementName().equals(elmName));

        return element;
    }

}
