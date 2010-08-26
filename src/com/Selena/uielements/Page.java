package com.Selena.uielements;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


/**
 * Page element class.
 *
 * @author hannibal
 *
 */
@Root
public class Page
{

    /**
     * Web Element List.
     */
    @ElementList(name = "Page", inline = true)
    public List<WebElement> webElement;

    /**
     *
     */
    @Attribute(required = false)
    public String name;
}

