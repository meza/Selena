package com.Selena.uielements;

import com.Selena.Locator;
import java.util.ArrayList;
import java.util.List;


/**
 * Web Element object.
 *
 * @author Brautigam Gergely
 *
 */
public class WebElement
{

    /**
     * The locators of the element.
     */
    private ArrayList<Locator> locators = new ArrayList<Locator>();

    /**
     * The name of the element.
     */
    private String name;


    /**
     * Create a new element.
     * @param id The Name of the element
     */
    public WebElement(final String id) {
        this.name = id;
    }


    /**
     * Get the name of the element.
     * @return The name
     */
    public String getName()
    {
        return this.name;
    }


    /**
     * Add a Locator to the element.
     * @param loc The Locator to add.
     */
    public void addLocator(final Locator loc)
    {
        this.locators.add(loc);
    }


    /**
     * Get the locators of the element.
     * @return The locators
     */
    public List<Locator> getLocators()
    {
        return this.locators;
    }

}

