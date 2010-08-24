package com.uielements;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Web Element object.
 *
 * @author hannibal
 *
 */
@Root(name="WebElement")
public class WebElement
{
    /**
     * Locators.
     */
    @Element(name="Locators")
    public Locators locators;

    /**
     * Name.
     */
    @Attribute
    public String name;
}
