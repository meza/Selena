package com.utilities.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration class. It provides access for java property files.
 *
 * @author Brautigam Gergely
 *
 */
public final class Configuration
{

    /**
     * Private constructor of Configuration class.
     */
    private Configuration()
    {

    }

    /**
     * Properties.
     */
    private static Properties properties;

    static
    {
        properties = new Properties();
        try
        {
            properties.load(new FileInputStream(
                    "conf//userConfiguration.properties"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Get a Value from a configuration file.
     *
     * @param value The name of the value to look for
     * @return The value of the variable looked for
     */
    public static String getValue(final String value)
    {
        return properties.getProperty(value);
    }

}
