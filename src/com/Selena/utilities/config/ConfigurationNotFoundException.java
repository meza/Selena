/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Selena.utilities.config;

/**
 *
 * @author meza
 */
public class ConfigurationNotFoundException extends RuntimeException{

    public ConfigurationNotFoundException(String key) {
        super(String.format("Configuration not found: %s",
                            key));
    }

}
