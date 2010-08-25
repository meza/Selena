package com.Selena.utilities.config;

import java.util.Properties;
import org.testng.Assert;


/**
 * Provides access to system properties
 *
 * @author meza <meza@meza.hu>
 *
 */
public final class Configuration
{

    /**
     * Holds the Properties.
     */
    private static Properties properties;


    /**
     * Private constructor of Configuration class.
     */
    private Configuration()
    {
    }


    /**
     * Retrieve a Value from the sysprops and asserts that it is not null.
     *
     * @param value The name of the value to look for
     *
     * @return
     */
    public static String getValue(final String value)
    {
        properties = System.getProperties();
        String result = properties.getProperty(value);

        Assert.assertNotNull(
            result,
            "The requested configuration " + value + " is not found");
        return result;
    }


}

