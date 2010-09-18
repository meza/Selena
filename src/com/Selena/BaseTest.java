/**
 * This classes purpose is to be the Base for all Test classes.
 * Contains setup and teardown phase.
 * Also it opens and reads the local configuration file.
 *
 * @author Gergely Brautigam
 * @version 1.0
 */
package com.Selena;


import com.Selena.utilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.Reporter;

import com.Selena.utilities.config.SelenaConfiguration;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


/**
 *
 * The Base of all tests.
 *
 * @author Brautigam Gergely
 *
 */
public class BaseTest
{

    /**
     * Default selenium to work with.
     */
    protected Selenium selenium;

    /**
     * Default FireFox webDriver.
     */
    protected FirefoxDriver ffDriver;

    /**
     * Default webDriver.
     */
    protected WebDriver webDriver;

    /**
     * Default selenium server.
     */
    private SeleniumServer server;

    /**
     * Configuration.
     */
    private Configuration config;

    /**
     * Utils.
     */
    private Utils utils;

    /**
     * Default page load timeout.
     */
    protected String defaultPageLoadTimeout = "60000";

    /**
     * Method running before suite. Setting selena configuration and utilities.
     */
    @BeforeClass
    public void beforeSuite()
    {
		this.setConfig(new SelenaConfiguration());
        this.setUtils(new Utilities(this.getConfig()));
    }

    /**
     * Set Utilities.
     * @param utilsParam Utility to set
     */
    public void setUtils(final Utils utilsParam)
    {
        this.utils = utilsParam;
    }

    /**
     * Get Utility.
     * @return Utilitiy
     */
    public Utils getUtils()
    {
        return this.utils;
    }

    /**
     * Set configuration.
     * @param configParam Configuration
     */
    public void setConfig(final Configuration configParam)
    {
        this.config = configParam;
    }

    /**
     * Get Configuration.
     * @return Configuration
     */
    public Configuration getConfig()
    {
        return this.config;
    }

    /**
     * Performing setup for tests. 1. Setup of selenium 2. Starting selenium 3.
     * Opening the set default page 4. Waiting for the page to load
     */
    @BeforeMethod
    public void setUp()
    {
        setUpSelenium();
        startSelenium();
        selenium.open("/", defaultPageLoadTimeout);
        selenium.waitForPageToLoad(defaultPageLoadTimeout);
    }


    /**
     * Performing a tear down after each test 1. Capturing the last screen for
     * loggin 2. Stoping selenium
     */
    @AfterMethod
    public void tearDown()
    {
        Reporter.log("Closing selenium.");
        stopSelenium();
    }


    /**
     * Set a native xpath library if the browser IE does not recognize xpath.
     * @param library Name of the library.
     */
    protected void seleniumUseCustomXpathLibrary(final String library)
    {
        selenium.useXpathLibrary(library);
    }


    /**
     * Stops the selenium server.
     */
    protected void stopSeleniumServer()
    {
        server.stop();
    }


    /**
     * Setup selenium with the settings from config.properties.
     */
    protected void setUpSelenium()
    {
		Reporter.log(String.format(
            "Setting up selenium with data:%s, %d, %s, %s", getConfig().
            getValue(ConfigParams.SELENIUMHOST),
            Integer.parseInt(getConfig().getValue(
            ConfigParams.SELENIUMPORT)),
            getConfig().getValue(ConfigParams.SELENIUMBROWSER),
            getConfig().getValue(ConfigParams.SELENIUMBASEURL)));
        selenium = new DefaultSelenium(
            getConfig().getValue(ConfigParams.SELENIUMHOST),
            Integer.parseInt(getConfig().getValue(ConfigParams.SELENIUMPORT)),
            getConfig().getValue(ConfigParams.SELENIUMBROWSER),
            getConfig().getValue(ConfigParams.SELENIUMBASEURL));
    }


    /**
     * Start selenium.
     */
    protected void startSelenium()
    {
        selenium.start("");
        selenium.windowMaximize();
    }


    /**
     * Setup webDriver backed Selenium.
     */
    protected void setUpWebDriverBackedSelenium()
    {
        selenium = new WebDriverBackedSelenium(ffDriver, getConfig().getValue(
            ConfigParams.SELENIUMBASEURL));
        selenium.start();
    }


    /**
     * Close selenium.
     */
    protected void stopSelenium()
    {
        selenium.stop();
    }


    /**
     * Set up Firefox WebDriver.
     */
    protected void setUpFFWebDriver()
    {
        ffDriver = new FirefoxDriver();
    }


    /**
     * Close Firefox WebDriver.
     */
    protected void closeFFWebDriver()
    {
        ffDriver.close();
    }


}

