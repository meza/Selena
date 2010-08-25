/**
 * This classes purpose is to be the Base for all Test classes.
 * Contains setup and teardown phase.
 * Also it opens and reads the local configuration file.
 *
 * @author Gergely Brautigam
 * @version 1.0
 */
package com.Selena;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.Reporter;

import com.Selena.utilities.config.Configuration;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.testng.annotations.AfterMethod;
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
     * Default page load timeout.
     */
    protected final String defaultPageLoadTimeout = "60000";

    /**
     * Default step counter for spreadsheet reports.
     */
    protected int steps = 0;

    /**
     * TestId used to identify the appropriate row in a spreadsheet.
     */
    protected String testId = "";

    /**
     * Url to the google document that contains the appropriate testId.
     */
    protected String spreadSheetURL = "";


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
     * Starts the selenium server.
     */
    protected void startSeleniumServer()
    {
        // Creating a template on certificates to not have to self
        // accept every certificate. This template will still be
        // clean but the certificates there will be imported.
        RemoteControlConfiguration rcc = new RemoteControlConfiguration();
        rcc.setFirefoxProfileTemplate(new File("pathToProfile"));
        try
        {
            server = new SeleniumServer(rcc);
            server.start();
        } catch (Exception e)
        {
            Reporter.log(e.getMessage());
            e.printStackTrace();
        }
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
            "Setting up selenium with data:%s, %d, %s, %s", Configuration.
            getValue("selenium.host"), Integer.parseInt(Configuration.getValue(
            "selenium.port")), Configuration.getValue("selenium.browser"),
            Configuration.getValue("selenium.baseUrl")));
        selenium = new DefaultSelenium(
            Configuration.getValue("selenium.host"),
            Integer.parseInt(Configuration.getValue("selenium.port")), 
            Configuration.getValue("selenium.browser"),
            Configuration.getValue("selenium.baseUrl")
        );
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
        selenium = new WebDriverBackedSelenium(ffDriver, Configuration.getValue(
            "selenium.baseUrl"));
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

