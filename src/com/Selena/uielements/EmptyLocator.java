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
public class EmptyLocator implements Locator {

    /**
     * The value of the locator.
     */
    private final String locVal;

    /**
     * The type of the locator.
     */
    private final String locType;

    /**
     * Creates the locator.
     *
     * @param type The type
     * @param value The value
     */
    public EmptyLocator(final String type, final String value) {
        this.locVal = value;
        this.locType = type;
    }

    /**
     * Gets the value of the Locator.
     * @return the value.
     */
    public String getValue() {
        return this.locVal;
    }


    /**
     * Gets the type of the Locator.
     * @return the type.
     */
    public String getType() {
        return this.locType;
    }

}
