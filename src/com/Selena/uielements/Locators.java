package com.Selena.uielements;

import org.simpleframework.xml.Element;


/**
 * Locator types.
 *
 * @author hannibal
 *
 */
public class Locators
{

    /**
     * Id.
     */
    @Element(required = false)
    public String id;

    /**
     * Xpath.
     */
    @Element(required = false)
    public String xpath;

    /**
     * Name.
     */
    @Element(required = false)
    public String name;

    /**
     * Class.
     */
    @Element(name = "class", required = false)
    public String className;

    /**
     * Value.
     */
    @Element(required = false)
    public String value;

    /**
     * Japan.
     */
    @Element(required = false)
    public String japan;

    /**
     * English.
     */
    @Element(required = false)
    public String english;
}

