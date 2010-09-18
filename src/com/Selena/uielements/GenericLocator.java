/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Selena.uielements;

import com.Selena.Locator;

/**
 *
 * @author meza
 */
public class GenericLocator implements Locator{

    /**
     * The type of the locator.
     */
    private final String locType;

    /**
     * The value of the locator.
     */
    private final String locVal;


    /**
     * Creates the locator.
     * @param type The type
     * @param value The value
     */
    public GenericLocator(final String type, final String value)
    {
        this.locType = type;
        this.locVal = value;
    }


    /**
     * Gets the value of the Locator.
     * @return the value.
     */
    public String getValue() {
        return this.getType()+"="+this.locVal;
    }

    /**
     * Gets the type of the Locator.
     * @return the type.
     */
    public String getType() {
        return this.locType;
    }

}
