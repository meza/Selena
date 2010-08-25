package com.uielements;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


/**
 * Web Element object.
 *
 * @author Brautigam Gergely
 *
 */
@Root(name = "WebElement")
public class WebElement
{

    /**
     * <WebElement><Locators/></WebElement>
     */
    @Element(name = "Locators")
    public Locators locators;

    /**
     * <WebElement name="xy"/>
     */
    @Attribute
    public String name;

}

