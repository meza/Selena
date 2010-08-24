package com.uielements;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Div element class.
 *
 * @author hannibal
 *
 */
@Root
public class Div
{
    /**
     * Web Element List.
     */
    @ElementList(name="Div", inline=true)
    public List<WebElement> webElement;

    /**
     *
     */
    @Attribute(required=false)
    public String name;
}
