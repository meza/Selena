package hu.meza.Selena.uielements;

import hu.meza.Selena.Locator;

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
    public String path;
    /**
     * The path of the page.
     */
    public String url;

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
        try {
            URL aurl = new URL(baseUrl);
            return new URL(aurl, normalizePath(path));
        } catch (MalformedURLException ex) {
            throw new hu.meza.Selena.uielements.MalformedURLException(
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
        if (uriToNormalize.isEmpty()) {
            return "";
        }
        String theNormalizedURI = uriToNormalize;
        while (uriToNormalize.startsWith("/")) {
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
            final String elementName) {
        for (WebElement elem : elements) {
            if (elem.getName().equals(elementName)) {
                switch (locator) {
                    case CLASS:
                        return getLocator("class", elem);
                    case CSS:
                        return getLocator("css", elem);
                    case ENGLISH:
                        return getLocator("english", elem);
                    case ID:
                        return getLocator("id", elem);
                    case JAPAN:
                        return getLocator("japan", elem);
                    case LINK:
                        return getLocator("link", elem);
                    case NAME:
                        return getLocator("name", elem);
                    case VALUE:
                        return getLocator("value", elem);
                    case XPATH:
                        return getLocator("xpath", elem);
                    default:
                        return getLocator("text", elem);
                }
            }
        }
        return null;
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

        if (locators.isEmpty())
        {
            throw new LocatorNotFoundException(elem.getName(), locator);
        }

        final String[] priorityList = {"id","css","xpath"};

        if (locator.isEmpty())
        {
            for (String actType : priorityList)
            {
                for (Locator elemLoc : locators)
                {
                    if (elemLoc.getType().equals(actType))
                    {
                        return elemLoc.getValue();
                    }
                }
            }
            return locators.get(0).getValue();
        }else
        {
            for (Locator elemLoc : locators)
            {
                if (elemLoc.getType().equals(locator))
                {
                    return elemLoc.getValue();
                }
            }
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

    /**
     * Returns he path of the page.
     *
     * @return String
     */
    public String getPath() {
        return this.path;
    }

}
