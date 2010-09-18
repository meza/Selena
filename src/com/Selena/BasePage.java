/**
 * This is the Base Page class from which every page class is inherited.
 *
 * @author Gergely Brautigam
 */
package com.Selena;

import com.Selena.uielements.LocatorFactory;
import com.thoughtworks.selenium.Selenium;
import com.Selena.uielements.Page;
import com.Selena.uielements.SelenaLocatorFactory;
import com.Selena.uielements.UISerializer;
import java.io.File;
import org.testng.Assert;
import org.testng.Reporter;


/**
 * Base Page for all pages.
 * @author Brautigam Gergely
 *
 */
public class BasePage
{

    /**
     * Selenium instance to work with.
     */
    protected Selenium selenium;

    /**
     * Page for social logins like google, yahoo.
     */
    protected Page elements;

    /**
     * Default time out for Pages.
     */
    protected String defaultPageTimeOut = "90000";

    /**
     * Default time out for Ajax requests.
     */
    protected String defaultAjaxTimeOut = "15000";

    /**
     * Default element wait.
     */
    protected int defaultElementTimeOut = 30000;

    /**
     * The configuration to use.
     */
    private Configuration config;

    /**
     * Utils.
     */
    private Utils utils;

    /**
     * The LocatorFactory instance.
     */
    private LocatorFactory locatorFactory;

    /**
     * Get a URL.
     * @return returns a URL String
     */
    public String getUrl()
    {
        return elements.getUrl().toString();
    }


    /**
     * Open the page.
     */
    public void open()
    {
        this.openWebPage(this.getUrl());
    }


    /**
     * Possible language specification names found in local cookie.
     */
    protected enum Language
    {
        /**
         * English, Japanese.
         */
        en_US, ja_JP
    };


    /**
     * Constructor for Social Pages.
     *
     * @param sel Selenium instance to work with
     * @param configFile The UIElementConfig to use
     * @param configuration Configuration
     * @param utilities Utils
     * @param factory The LocatorFactory instance to use.
     */
    public BasePage(
            final Selenium sel,
            final String configFile,
            final Configuration configuration,
            final Utils utilities,
            final LocatorFactory factory)
    {
        this.config   = configuration;
        this.selenium = sel;
        this.locatorFactory  = factory;
        UISerializer uiSerializer = new UISerializer(
            this.config,
            this.locatorFactory
        );
        elements = uiSerializer.deserialize(
                this.config.getValue(ConfigParams.UIXMLSDIR)
                + File.separator + configFile);
        this.utils = utilities;
    }


    /**
     * Constructor for Social Pages.
     *
     * @param sel Selenium instance to work with
     * @param configFile The UIElementConfig to use
     * @param configuration Configuration
     * @param utilities Utils
     */
    public BasePage(
            final Selenium sel,
            final String configFile,
            final Configuration configuration,
            final Utils utilities)
    {
        this.config   = configuration;
        this.selenium = sel;
        this.locatorFactory  = new SelenaLocatorFactory();
        UISerializer uiSerializer = new UISerializer(
            this.config,
            this.locatorFactory
        );
        elements = uiSerializer.deserialize(
                this.config.getValue(ConfigParams.UIXMLSDIR)
                + File.separator + configFile);
        this.utils = utilities;
    }


    /**
     * Get the serializer instance.
     * @return The serializer to use.
     */
    public UISerializer getSerializer()
    {
        return new UISerializer(
            this.config,
            this.locatorFactory
        );
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
     * Get Utils.
     * @return Util class
     */
    public Utils getUtils()
    {
        return this.utils;
    }


    /**
     * Method to open Web Page.
     *
     * @param webPage the URL of the web page to open.
     */
    protected void openWebPage(final String webPage)
    {
        Reporter.log("Opening page: '" + webPage + "'");
        selenium.open(webPage, defaultPageTimeOut);
        selenium.waitForPageToLoad(defaultPageTimeOut);
    }


    /**
     * Fetch a locator from the Page config and assert that
     * it is not null.
     *
     * @param elementName The element to fetch
     *
     * @return The element locator
     */
    public String getElementLocator(final String elementName)
    {
        String elementLocator = elements.getElementLocator(elementName);
        Assert.assertNotNull(
            elementLocator,
            elementName + " element not found in UIELEMENTS config file");

        return elementLocator;

    }


}

