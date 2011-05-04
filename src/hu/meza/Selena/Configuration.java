/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.meza.Selena;

/**
 * Configuration Interface.
 * @author meza
 */
public interface Configuration {

    /**
     * getValue.
     * @param key Key
     * @return Value
     */
    String getValue(final String key);

    /**
     * Add Config File.
     * @param filename filename
     */
    void addConfigFile(final String filename);

    /**
     * Add Config File override.
     * @param filename Filename
     * @param required Required or not.
     */
    void addConfigFile(final String filename, final boolean required);

}
