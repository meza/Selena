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
public class SelenaLocatorFactory implements LocatorFactory {

    /**
     * Theuser defined factory.
     */
    private LocatorFactory customFactory;


    /**
     * Creates the factory with a decorator.
     * @param userFactory The user defined extension.
     */
    public SelenaLocatorFactory(final LocatorFactory userFactory) {
        this.customFactory = userFactory;
    }


    /**
     * Creates a default factory without any extensions.
     */
    public SelenaLocatorFactory() {
    }


    /**
     * Determines if the instance has a user defined decorator present.
     * @return boolean
     */
    private boolean hasCustomFactory() {
        return this.customFactory != null;
    }


    /**
     * Create a locator by the given parameters.
     *
     * @param type The type of the locator
     * @param value The value of the locator
     * @return The created locator
     */
    public Locator getLocator(final String type, final String value) {
        if (this.hasCustomFactory()) {
            Locator custLoc = this.customFactory.getLocator(type, value);
            if (null != custLoc) {
                return custLoc;
            }
        }
        return new GenericLocator(type, value);
    }
}
