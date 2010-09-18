/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Selena.utilities.config;

/**
 * Configuration Not Found Exception.
 * @author meza
 */
public class ConfigurationFileNotFound extends RuntimeException{

    /**
     * Default Serial Version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Configuration Not Found Exception.
     * @param filename Key
     */
    public ConfigurationFileNotFound(final String filename) {
        super(String.format("Configuration file not found: %s",
                            filename));
    }

}
