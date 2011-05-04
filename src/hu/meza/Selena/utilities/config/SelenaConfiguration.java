package hu.meza.Selena.utilities.config;

import hu.meza.Selena.ConfigParams;
import hu.meza.Selena.Configuration;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;


/**
 * Provides access to system properties.
 *
 * @author meza <meza@meza.hu>
 *
 */
public final class SelenaConfiguration implements Configuration
{

    /**
     * Holds the Properties.
     */
    private Properties properties;


    /**
     * Private constructor of SelenaConfiguration class.
     */
    public SelenaConfiguration()
    {
        this.properties = System.getProperties();
    }

    /**
     * Get Full Path Of Config File.
     * @param filename Name of the File
     * @return Path to the Configuration file
     */
    public String getFullPathOfConfigFile(final String filename) {
        String path = this.getValue(
                ConfigParams.CONFIGDIR) + File.separator + filename;
        return path;
    }


    /**
     * Retrieve a Value from the sysprops and asserts that it is not null.
     *
     * @param key The name of the value to look for
     *
     * @return The value of the system property.
     */
    public String getValue(final String key)
    {
        String result = properties.getProperty(key);

        if (null == result) {
            throw new ConfigurationNotFoundException(key);
        }

        return result;
    }

    /**
     * Add Config File.
     * @param filename Name of the Configuration file
     */
    public void addConfigFile(final String filename) {
        try {
            this.properties.load(this.getStream(filename));
        } catch (IOException e) {
            throw new ConfigurationCantBeLoaded(filename);
        }
    }

    /**
     * Add Config File.
     *
     * @param filename Name of the file
     * @param required required or not
     */
    public void addConfigFile(final String filename, final boolean required)
    {
        if (required) {
            addConfigFile(filename);
        } else {
            if(fileExists(filename)) {
                addConfigFile(filename);
            }
        }
    }

    /**
     * Checks if the file exists.
     * @param filename Name of the File
     * @return true if the file exists.
     */
    private boolean fileExists(final String filename)
    {
        File file = new File(getFullPathOfConfigFile(filename));
        return file.exists();
    }

    /**
     * Get the stream.
     * @param filename Name of the file
     * @return Returns a Stream
     */
    private FileInputStream getStream(final String filename)
    {
        String path = getFullPathOfConfigFile(filename);
        try {
            FileInputStream is = new FileInputStream(path);
            return is;
        } catch(FileNotFoundException e) {
            throw new ConfigurationFileNotFound(filename);
        }
    }


}

