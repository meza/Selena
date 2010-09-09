package com.Selena.uielements;

import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


/**
 * Web Element object.
 *
 * @author Brautigam Gergely
 *
 */
@Root(name = "Group")
public class Group
{

    @ElementList(name = "WebElement", inline=true)
    public List<WebElement> elements;

    /**
     * <WebElement name="xy"/>
     */
    @Attribute(name="name", required=false)
    public String name;
}

