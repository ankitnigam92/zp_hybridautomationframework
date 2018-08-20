package zp.common.repository;

/**
 * Created by AnkitNigam on 07/04/2018.
 */

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name="pages")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebRepository {
    @XmlElement(name = "page")
    private List<WebPage> webPages;
}
