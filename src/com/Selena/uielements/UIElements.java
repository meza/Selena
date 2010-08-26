package com.Selena.uielements;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


/**
 * The root object for the UIElement xml files.
 *
 * @author Brautigam Gergely
 *
 */
@Root
public class UIElements
{

    /**
     * Page element list.
     */
    @ElementList(name = "Elements")
    public List<Page> div;


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
        for (Page divs : this.div)
        {
            for (WebElement elem : divs.webElement)
            {
                if (elem.name.equals(elementName))
                {
                    return getLocator(locator, elem);
                }
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

        for (Page divs : div)
        {
            for (WebElement elem : divs.webElement)
            {
                if (elem.name.equals(elementName))
                {
                    ArrayList<String> locators = new ArrayList<String>();

                    locators.add(elem.locators.className);
                    locators.add(elem.locators.xpath);
                    locators.add(elem.locators.id);
                    locators.add(elem.locators.name);
                    locators.add(elem.locators.value);

                    for (String loc : locators)
                    {
                        if (loc != null)
                        {
                            return loc;
                        }
                    }
                }
            }
        }

        return null;
    }


}

