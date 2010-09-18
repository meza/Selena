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
public interface LocatorFactory {


    /**
     * Create a Locator implementation ot of the given parameters.
     * @param type The type of the locator.
     * @param value The value of the locator.
     * @return The generated Locator object.
     */
    Locator getLocator(final String type, final String value);

}
