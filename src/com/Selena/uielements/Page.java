package com.Selena.uielements;

import com.Selena.Locator;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * The root object for the UIElement xml files.
 *
 * @author Brautigam Gergely
 *
 */
public class Page {

    /**
     * Selenium Base URL.
     */
    private String seleniumBaseUrl;
    /**
     * The path of the page.
     */
    String path;
    /**
     * The path of the page.
     */
    String url;

    /**
     * The WebElement objects that are on the Page.
     */
    private ArrayList<WebElement> elements = new ArrayList<WebElement>();


    /**
     * Get a full url put together by the baseUrl and the path of the page
     * If no path or url is given at the page element, the SelenaConfiguration's
     * baseUrl will be used.
     *
     * @return URL
     */
    public URL getUrl() {
        String baseUrl = getBaseUrl();
        System.out.println(String.format("Base path is: %s",normalizePath(path)));
        try {
            return new URL(new URL(baseUrl), normalizePath(path));
        } catch (MalformedURLException ex) {
            throw new com.Selena.uielements.MalformedURLException(
                    baseUrl, path, ex.getMessage());
        }
    }

    /**
     * Returns the base URL.
     * @return Base of the URL
     */
    private String getBaseUrl() {
        if (url.isEmpty()) {
            return this.seleniumBaseUrl;
        }
        return url;
    }

    /**
     * Set Selenium Base URL.
     * @param urlParam Url
     */
    public void setSeleniumBaseUrl(final String urlParam) {
        this.seleniumBaseUrl = urlParam;
    }

    /**
     * Normalizes a Path.
     * @param uriToNormalize The URL to normalize.
     * @return Return the URL in a normalized format.
     */
    private String normalizePath(final String uriToNormalize) {

        String theNormalizedURI = uriToNormalize;
        while (theNormalizedURI.startsWith("/")) {
            theNormalizedURI = uriToNormalize.replaceFirst("/", "");
        }
        return theNormalizedURI;
    }


    /**
     * Adds a WebElement to the Page.
     * @param elem The WebElement to add
     */
    public void addElement(final WebElement elem) {
        this.elements.add(elem);
    }


    /**
     * Fetch a locator based on the locator type.
     *
     * @param locator The locator to fetch
     * @param elem    The element to fetch from
     *
     * @return Locator.
     */
    private String getLocator(
            final String locator,
            final WebElement elem) {
        List<Locator> locators = elem.getLocators();

        int css = -1;
        int xpath = -1;
        int id = -1;
        int index = 0;
        for (Locator elemLoc : locators) {
            if (elemLoc.getType().equals("css")) {
                css = index;
            }
            if (elemLoc.getType().equals("xpath")) {
                xpath = index;
            }
            if (elemLoc.getType().equals("id")) {
                id = index;
            }
            if(!locator.isEmpty()) {
                if (elemLoc.getType().equals(locator)) {
                    return elemLoc.getValue();
                }
            }
            index++;
        }

        if (-1 < css) {
            return locators.get(css).getValue();
        }
        if (-1 < id) {
            return locators.get(id).getValue();
        }
        if (-1 < xpath) {
            return locators.get(xpath).getValue();
        }

        if (locators.size() > 0) {
            return locators.get(0).getValue();
        }

        throw new LocatorNotFoundException(elem.getName(), locator);
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
    public String getElementLocator(final String elementName) {
        for (WebElement elem : this.elements) {
            if (elem.getName().equals(elementName)) {
                return this.getLocator("", elem);
            }
        }

        throw new LocatorNotFoundException(elementName);
    }

}
