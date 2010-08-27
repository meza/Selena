package com.Selena.uielements;

import com.Selena.utilities.config.Configuration;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


/**
 * The root object for the UIElement xml files.
 *
 * @author Brautigam Gergely
 *
 */
@Root
public class Page
{

    /**
     * Elements list.
     */
    @ElementList(name = "Elements")
    public List<WebElement> elements;

    /**
     * The path of the page
     */
    @Attribute(name = "path", empty = "/", required = false)
    String path;

    /**
     * The path of the page
     */
    @Attribute(name = "url", required = false)
    String url;

    /**
     * Schema location
     */
    @Attribute
    String noNamespaceSchemaLocation;


    /**
     * Get a full url put together by the baseUrl and the path of the page
     * If no path or url is given at the page element, the Configuration's
     * baseUrl will be used
     * 
     * @return
     */
    public URL getUrl()
    {
        String baseUrl = getBaseUrl();
        try
        {
            return new URL(new URL(baseUrl), normalizePath(path));
        } catch (MalformedURLException ex)
        {
            throw new com.Selena.uielements.MalformedURLException(
                baseUrl, path, ex.getMessage());
        }
    }


    private String getBaseUrl()
    {
        if (null == url)
        {
            return Configuration.getValue("selenium.baseUrl");
        }
        return url;
    }


    private String normalizePath(String uriToNormalize)
    {
        while (uriToNormalize.startsWith("/"))
        {
            uriToNormalize = uriToNormalize.replaceFirst("/", "");
        }
        return uriToNormalize;
    }


    /**
     * Get the Locator of a specific element.
     *
     * @param locator
     *            The Type of the Locator like: id, name, value, xpath
     * @param elementName
     *            The Name of the element in the XML file that you are trying to
     *            find
     * @return A string containing the locator searched for
     */
    public String getElementLocator(final LocatorTypes locator,
                                    final String elementName)
    {
        for (WebElement elem : elements)
        {
            if (elem.name.equals(elementName))
            {
                return getLocator(locator, elem);
            }
        }
        return null;
    }


    /**
     * Fetch a locator based on the locator type
     *
     * @param locator The locator to fetch
     * @param elem    The element to fetch from
     *
     * @return
     */
    private String getLocator(final LocatorTypes locator, WebElement elem)
    {
        switch (locator)
        {
            case ID:
                return elem.locators.id;
            case NAME:
                return elem.locators.name;
            case XPATH:
                return elem.locators.xpath;
            case CLASS:
                return elem.locators.className;
            case VALUE:
                return elem.locators.value;
            case LINK:
                return "link=" + elem.locators.link;
            default:
                return null;
        }
    }


    /**
     * Get the Locator of a specific element if only one exists
     * then the second parameter is no longer needed...
     *
     * @param elementName
     *            The Name of the element in the XML file that you are trying to
     *            find
     * @return A string containing the locator searched for
     */
    public String getElementLocator(final String elementName)
    {
        for (WebElement elem : elements)
        {
            if (elem.name.equals(elementName))
            {
                ArrayList<String> locators = new ArrayList<String>();


                locators.add(getLocator(LocatorTypes.CLASS, elem));
                locators.add(getLocator(LocatorTypes.XPATH, elem));
                locators.add(getLocator(LocatorTypes.ID, elem));
                locators.add(getLocator(LocatorTypes.NAME, elem));
                locators.add(getLocator(LocatorTypes.VALUE, elem));
                locators.add(getLocator(LocatorTypes.LINK, elem));

                for (String loc : locators)
                {
                    if (loc != null)
                    {
                        return loc;
                    }
                }
            }
        }

        return null;
    }


}

