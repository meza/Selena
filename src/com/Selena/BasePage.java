/**
 * This is the Base Page class from which every page class is inherited.
 *
 * @author Gergely Brautigam
 */
package com.Selena;

import com.thoughtworks.selenium.Selenium;
import com.Selena.uielements.UIElements;
import com.Selena.uielements.UISerializer;
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
     * UIElements for social logins like google, yahoo.
     */
    protected UIElements elements;

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
        elements = uiSerializer.deserialize(configFile);
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
     * Fetch a locator from the UIElements config and assert that
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

