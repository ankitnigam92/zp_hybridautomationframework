package zp.common.repository;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * Created by AnkitNigam on 07/04/2018.
 */
@Data
@XmlRootElement(name="uiobject")
@XmlAccessorType(XmlAccessType.FIELD)
public class PageElement {
    @XmlAttribute(name="name")
    private String elementName;
    @XmlAttribute(name="type")
    private String locatorType;
    @XmlElement(name="locator")
    private String locator;
}
