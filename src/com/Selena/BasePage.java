/**
 * This is the Base Page class from which every page class is inherited.
 *
 * @author Gergely Brautigam
 */
package com.Selena;

import com.thoughtworks.selenium.Selenium;
import com.Selena.uielements.Page;
import com.Selena.uielements.UISerializer;
import com.Selena.utilities.config.Configuration;
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
    protected final int defaultElementTimeOut = 30000;


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

        en_US, ja_JP
    };


    /**
     * Constructor for Social Pages.
     *
     * @param sel        Selenium instance to work with
     * @param configFile The UIElementConfig to use
     */
    public BasePage(final Selenium sel, final String configFile)
    {
        this.selenium = sel;
        UISerializer uiSerializer = new UISerializer();
        elements = uiSerializer.deserialize(Configuration.getValue("uixmls.dir")
                                            + "/" + configFile);
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
    protected String getElementLocator(final String elementName)
    {
        String elementLocator = elements.getElementLocator(elementName);
        Assert.assertNotNull(
            elementLocator,
            elementName + " element not found in UIELEMENTS config file");

        return elementLocator;

    }


}

