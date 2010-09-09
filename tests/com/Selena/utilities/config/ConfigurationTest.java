/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Selena.utilities.config;

import org.junit.Rule;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;


/**
 *
 * @author meza
 */
public class ConfigurationTest
{

    @Before
    public void setUp()
    {
        Properties props = new Properties();
        props.setProperty("a", "b");
        System.setProperties(props);
    }


    /**
     * Test of getValue method, of class Configuration.
     */
    @Test
    public void testGetValue()
    {
        String value = "a";
        String expResult = "b";
        String result = Configuration.getValue(value);
        assertEquals("The resulted property does not match the expected",
                     expResult, result);
    }


    /**
     * Test exception when no property found
     */
    @Test
    public void testGetValueException()
    {
        String key = "not existing value";
        String expectedMessage = "Configuration not found: " + key;
        try
        {
            String result = Configuration.getValue(key);
            fail("Expected exception not thrown");
        } catch (ConfigurationNotFoundException e)
        {
            assertEquals(
                "Exception message doesn't match the expected value",
                expectedMessage,
                e.getMessage());
        }

    }


}

