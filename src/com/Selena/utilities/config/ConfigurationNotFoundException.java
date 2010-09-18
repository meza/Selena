/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Selena.utilities.config;

/**
 * Configuration Not Found Exception.
 * @author meza
 */
public class ConfigurationNotFoundException extends RuntimeException{

    /**
     * Default Serial Version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Configuration Not Found Exception.
     * @param key Key
     */
    public ConfigurationNotFoundException(final String key) {
        super(String.format("Configuration not found: %s",
                            key));
    }

}
