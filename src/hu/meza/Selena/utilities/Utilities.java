package hu.meza.Selena.utilities;

import hu.meza.Selena.Configuration;
import hu.meza.Selena.Reporter;
import hu.meza.Selena.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import org.testng.Assert;

import hu.meza.Selena.uielements.LocatorTypes;
import hu.meza.Selena.uielements.Page;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.Wait;


/**
 *
 * @author Brautigam Gergely
 *
 *
 *
 */
public final class Utilities implements Utils
{
    /**
     * Util report verbosity level.
     */
    static final int UTIL_LOG_LEVEL = 1;

    /**
     * A utility that waits for a new window to open up. Requires current
     * selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param actualNumberOfWindows
     *            the number of windows present before the new window opening
     *            action was taken (e.g. selenium.getAllWindowNames().length)
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForNewWindow(final Selenium selenium,
            final int actualNumberOfWindows, final String timeout,
            final int logLevel)
    {
        Reporter.log("Waiting for change in the number of opened windows"
                + " (currently " + actualNumberOfWindows + " present).",
                logLevel);
        new Wait()
        {
            public boolean until()
            {
                return (selenium.getAllWindowNames().length
                                != actualNumberOfWindows)
                        || (selenium.getAllWindowIds().length
                                != actualNumberOfWindows)
                        || (selenium.getAllWindowTitles().length
                                != actualNumberOfWindows);
            }
        }.wait("No new window opened or disapeared within " + timeout
                + " ms. The number of opened widow(s) is "
                + selenium.getAllWindowNames().length + " (by name) or "
                + selenium.getAllWindowIds().length + " (by Id) or "
                + selenium.getAllWindowTitles().length + " (by title).", Integer
                .parseInt(timeout));
    }

    /**
     * A utility that waits for a new window to open up. Requires current
     * selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param actualNumberOfWindows
     *            the number of windows present before the new window opening
     *            action was taken (e.g. selenium.getAllWindowNames().length)
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForNewWindow(final Selenium selenium,
            final int actualNumberOfWindows, final String timeout)
    {
        waitForNewWindow(selenium, actualNumberOfWindows, timeout,
                UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific url to appear in the address bar.
     * Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param urlToAppear
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForPage(final Selenium selenium, final String urlToAppear,
            final String timeout, final int logLevel)
    {
        Reporter.log("Waiting for the page '"
                + Reporter.getHtmlLink(urlToAppear) + "' to appear.", logLevel);
        new Wait()
        {
            public boolean until()
            {
                return urlToAppear.equals(selenium.getLocation());
            }
        }.wait("The page '" + urlToAppear + "' did not appear  within "
                + timeout + " ms. The actual url is: '"
                + selenium.getLocation() + "'.", Integer.parseInt(timeout));
    }

    /**
     * A utility that waits for a specific url to appear in the address bar.
     * Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param urlToAppear
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForPage(final Selenium selenium, final String urlToAppear,
            final String timeout)
    {
        waitForPage(selenium, urlToAppear, timeout, UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific element to appear on a given page.
     * Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForElement(final Selenium selenium, final String locator,
            final int timeout, final int logLevel)
    {
        Reporter.log("Waiting for element '" + locator + "' to appear.",
                logLevel);
        new Wait()
        {
            public boolean until()
            {
                return selenium.isElementPresent(locator);
            }
        }.wait("The element '" + locator + "' did not appear  within "
                   + timeout + " ms.", timeout);
    }

    /**
     * A utility that waits for a specific element to appear on a given page.
     * Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForElement(final Selenium selenium, final String locator,
            final int timeout)
    {
        waitForElement(selenium, locator, timeout, UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific element to disappear on a given page.
     * Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForNotPresent(final Selenium selenium,
            final String locator, final int timeout, final int logLevel)
    {
        Reporter.log("Waiting for element '" + locator + "' to disappear.",
                logLevel);
        new Wait()
        {
            public boolean until()
            {
                return !selenium.isElementPresent(locator);
            }
        }.wait("The element '" + locator + "' did not disappear within "
                   + timeout + " ms.", timeout);
    }

    /**
     * A utility that waits for a specific element to disappear on a given page.
     * Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForNotPresent(final Selenium selenium,
            final String locator, final int timeout)
    {
        waitForNotPresent(selenium, locator, timeout, UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific element to get visible
     *  on a given page. Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForVisible(final Selenium selenium, final String locator,
            final int timeout, final int logLevel)
    {
        Reporter.log("Waiting for element '" + locator + "' to be visible.",
                logLevel);
        new Wait()
        {
            public boolean until()
            {
                if (selenium.isElementPresent(locator))
                {
                    return selenium.isVisible(locator);
                }
                return false;
            }
        }.wait("The element '" + locator + "' did not become visible within "
                   + timeout + " ms.", timeout);
    }

    /**
     * A utility that waits for a specific element to get visible
     *  on a given page. Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForVisible(final Selenium selenium, final String locator,
            final int timeout)
    {
        waitForVisible(selenium, locator, timeout, UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific element to lose visibility
     *  on a given page. Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForNotVisible(final Selenium selenium,
            final String locator, final int timeout, final int logLevel)
    {
        Reporter.log("Waiting for element '" + locator + "' to fade away.",
                logLevel);
        new Wait()
        {
            public boolean until()
            {
                if (selenium.isElementPresent(locator))
                {
                    return !selenium.isVisible(locator);
                }
                return true;
            }
        }.wait("The element '" + locator + "' did not fade away within "
                   + timeout + " ms.", timeout);
    }

    /**
     * A utility that waits for a specific element to lose visibility
     *  on a given page. Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForNotVisible(final Selenium selenium,
            final String locator, final int timeout)
    {
        waitForNotVisible(selenium, locator, timeout, UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific text to appear on a given page.
     * Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param textToAppear
     *            The text that we are waiting for.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     *
     */
    public void waitForTextToAppear(final Selenium selenium,
            final String textToAppear, final long timeout, final int logLevel)
    {
        Reporter.log("Waiting '" + textToAppear +
                "' text to appear.", logLevel);
        new Wait()
        {
            public boolean until()
            {
                return selenium.isTextPresent(textToAppear);
            }
        }.wait("'" + textToAppear + "' text did not appear within " + timeout
                   + " ms.", timeout);
    }

    /**
     * A utility that waits for a specific text to appear on a given page.
     * Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param textToAppear
     *            The text that we are waiting for.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForTextToAppear(final Selenium selenium,
            final String textToAppear, final long timeout)
    {
     waitForTextToAppear(selenium, textToAppear, timeout, UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific text to be contained in a specified
     * location on the page. Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param textLocator
     *            The locator where the text is waited
     * @param textToAppear
     *            The text that we are waiting for.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForTextToAppear(final Selenium selenium,
            final String textLocator, final String textToAppear,
            final long timeout, final int logLevel)
    {
        Reporter.log("Waiting '" + textToAppear + "' text to appear at '"
                + textLocator + "'.", logLevel);
        new Wait()
        {
            public boolean until()
            {
                return selenium.getText(textLocator).contains(textToAppear);
            }
        }.wait("'" + textToAppear + "' text did not appear at '" + textLocator
                + "'  within " + timeout + " ms.", timeout);
    }

    /**
     * A utility that waits for a specific text to be contained in a specified
     * location on the page. Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param textLocator
     *            The locator where the text is waited
     * @param textToAppear
     *            The text that we are waiting for.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForTextToAppear(final Selenium selenium,
            final String textLocator, final String textToAppear,
            final long timeout)
    {
        waitForTextToAppear(selenium, textToAppear, timeout, UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific element's text field to change its
     * value on a given page. Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForTextToChange(final Selenium selenium,
            final String locator, final int timeout, final int logLevel)
    {
        /**
         * The old text that has to change.
         */
        final String oldText = selenium.getText(locator);

        Reporter.log("Waiting for '" + oldText + "' to change.", logLevel);
        new Wait()
        {
            public boolean until()
            {
                return oldText.equals(selenium.getText(locator));
            }
        }.wait("'" + oldText + "' text located at '" + locator
                   + "' did not change within " + timeout + " ms.", timeout);
    }

    /**
     * A utility that waits for a specific element's text field to change its
     * value on a given page. Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForTextToChange(final Selenium selenium,
            final String locator, final int timeout)
    {
        waitForTextToChange(selenium, locator, timeout, UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific element's attribute to change.
     * Also sensitive to the disappear or appear of the attribute.
     * Works only with XPATH locators.
     * Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element that may has an attribute.
     *            (e.g. 'userName@class' or '//input[@name='q']')
     * @param attribute
     *            The name of the attribute beginning with an '@' sign.
     *            (e.g. '@value' or '@class')
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForAttributeToChange(final Selenium selenium,
            final String locator, final String attribute, final int timeout,
            final int logLevel)
    {
        final String oldAttribute = getAttribute(selenium, locator, attribute);
        Reporter.log("Waiting for element '" + locator
                     + "' to change it's '" + attribute + "' attribute from '"
                     + oldAttribute + "' to something else.", logLevel);
        new Wait()
        {
            public boolean until()
            {
                try
                {
                    return !oldAttribute.equals(selenium.getAttribute(locator
                            + attribute));
                } catch (Exception e)
                {
                    return !oldAttribute.equals("");
                }
            }
        }.wait("'" + oldAttribute + "', the value of the '" + attribute
                + "' attribute in the element '" + locator
                + "' did not change within " + timeout + " ms.", timeout);
    }

    /**
     * A utility that waits for a specific element's attribute to change.
     * Also sensitive to the disappear or appear of the attribute.
     * Works only with XPATH locators.
     * Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element that may has an attribute.
     *            (e.g. 'userName@class' or '//input[@name='q']')
     * @param attribute
     *            The name of the attribute beginning with an '@' sign.
     *            (e.g. '@value' or '@class')
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForAttributeToChange(final Selenium selenium,
            final String locator, final String attribute, final int timeout)
    {
        waitForAttributeToChange(selenium, locator, attribute, timeout,
                UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific element's attribute to become a
     * specified value. The only drawback of this function is, that in the case
     * when one waits for "" content to appear it can't tell whether the
     * attribute disappeared or it holds the "" content. Works with any kind of
     * locators. Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element that may has an attribute
     * @param attribute
     *            The name of the attribute beginning with an '@' sign. (e.g.
     *            '@value' or '@class')
     * @param attributeValue
     *            The attribute value that one should wait for
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForAttributeValue(final Selenium selenium,
            final String locator, final String attribute,
            final String attributeValue, final int timeout, final int logLevel)
        {
        Reporter.log("Waiting for element '" + locator
                + "' to have an '" + attribute + "' attribute with '"
                + attributeValue + "' value.", logLevel);

        new Wait()
        {
            public boolean until()
            {
                try
                {
                    return selenium.getAttribute(locator + attribute).equals(
                            attributeValue);
                } catch (Exception e)
                {
                    return attributeValue.isEmpty();
                }
            }
        }.wait("The value of the '" + locator + "' element's '" + attribute
                + "' attribute did not became '" + attributeValue + "' within "
                + timeout + " ms.", timeout);
    }

    /**
     * A utility that waits for a specific element's attribute to become a
     * specified value. The only drawback of this function is, that in the case
     * when one waits for "" content to appear it can't tell whether the
     * attribute disappeared or it holds the "" content. Works with any kind of
     * locators. Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element that may has an attribute
     * @param attribute
     *            The name of the attribute beginning with an '@' sign. (e.g.
     *            '@value' or '@class')
     * @param attributeValue
     *            The attribute value that one should wait for
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForAttributeValue(final Selenium selenium,
            final String locator, final String attribute,
            final String attributeValue, final int timeout)
    {
        waitForAttributeValue(selenium, locator, attribute, attributeValue,
                timeout, UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific element's attribute to contain a
     * specified value (substring). Waiting for "" content will result immediate
     * return. Works with any kind of locators. Requires current selenium object
     * to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element that may has an attribute
     * @param attribute
     *            The name of the attribute beginning with an '@' sign. (e.g.
     *            '@value' or '@class')
     * @param attributeValue
     *            The attribute value that one should wait for
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForAttributeContains(final Selenium selenium,
            final String locator, final String attribute,
            final String attributeValue, final int timeout, final int logLevel)
    {
        Reporter.log("Waiting for element '" + locator + "' to have an '"
                + attribute + "' attribute that contains '" + attributeValue
                + "' string.", logLevel);

        new Wait()
        {
            public boolean until()
            {
                try
                {
                    return selenium.getAttribute(locator + attribute).contains(
                            attributeValue);
                } catch (Exception e)
                {
                    return attributeValue.isEmpty();
                }
            }
        }.wait("The value of the '" + locator + "' element's '" + attribute
                + "' attribute (" + selenium.getAttribute(locator + attribute)
                + ") did not contain '" + attributeValue + "' within "
                + timeout + " ms.", timeout);
    }

    /**
     * A utility that waits for a specific element's attribute to contain a
     * specified value (substring). Waiting for "" content will result immediate
     * return. Works with any kind of locators. Requires current selenium object
     * to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element that may has an attribute
     * @param attribute
     *            The name of the attribute beginning with an '@' sign. (e.g.
     *            '@value' or '@class')
     * @param attributeValue
     *            The attribute value that one should wait for
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForAttributeContains(final Selenium selenium,
            final String locator, final String attribute,
            final String attributeValue, final int timeout)
    {
        waitForAttributeContains(selenium, locator, attribute, attributeValue,
                timeout, UTIL_LOG_LEVEL);
    }

    /**
     * A utility that reads a specific element's attribute. Gives "" if the
     * attribute is not present or it has no value. Requires current selenium
     * object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element that may has an attribute. (e.g.
     *            'userName@class' or '//input[@name='q']')
     * @param attribute
     *            The name of the attribute beginning with an '@' sign. (e.g.
     *            '@value' or '@class')
     * @return the value of the located element's attribute, "" if the attribute
     *         is not present or its value is empty.
     */
    public String getAttribute(final Selenium selenium, final String locator,
            final String attribute)
    {
        try
        {
            return selenium.getAttribute(locator + attribute);
        } catch (Exception e)
        {
            return "";
        }
    }

    /**
     * A utility that captures page screenshot from current browser page. The
     * method captures images to the 'execution dir'//output directory
     * with the given filename. Captures entire page if Firefox browser is used,
     * otherwise making a simple screenshot.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param filename
     *            The full name of the captured image (.png extension required).
     */
    public void captureScreenshot(final Selenium selenium,
                                         final String filename)
    {

        String savePath = getImageSavePath(filename);
        Reporter.log("Filename will be: " + filename);
        Reporter.log("Save Path: " + savePath);

        if (isFirefox(selenium) || isInternetExplorer(selenium))
        {
            try
            {
                selenium.captureEntirePageScreenshot(savePath, "");
            } catch (Exception e)
            {
                Reporter.log("Couldn't use EntirePageScreenshot"
                             + " -> using simple captureScreenshot.");
                selenium.captureScreenshot(savePath);
            }
        } else
        {
            selenium.captureScreenshot(savePath);
        }

    }

    /**
     * Determines weather the browser is firefox or not.
     *
     * @param selenium The selenium instance to use
     *
     * @return true on firefox
     */
    private boolean isFirefox(final Selenium selenium)
    {
        String javaScriptUserAgent = selenium.getEval(
            "selenium.browserbot.getCurrentWindow().navigator.userAgent");
        return javaScriptUserAgent.contains("Firefox");
    }

    /**
     * Determines weather the browser is firefox or not.
     *
     * @param selenium
     *            The selenium instance to use
     *
     * @return true on firefox
     *
     * */
    private boolean isInternetExplorer(final Selenium selenium)
    {
        String javaScriptUserAgent = selenium.getEval(
                "selenium.browserbot.getCurrentWindow().navigator.userAgent");
        return javaScriptUserAgent.contains("MSIE");
    }

    /**
     * Get the full path of the image.
     *
     * @param filename The filename to save
     *
     * @return String the full path
     */
    private String getImageSavePath(final String filename)
    {
        String fileNameWithoutExtension =
               filename.substring(0, filename.indexOf('.'));
        String extension =
               filename.substring(filename.indexOf('.'), filename.length());

        String fileNameWithTimeStamp =
            fileNameWithoutExtension + now("_mmHHddMMyy") + extension;

        String imageBaseDir = getConfig().getValue("image.dir");

        return imageBaseDir+File.separator+fileNameWithTimeStamp;
    }

    /**
     * A utility that waits for an ajax request to complete.
     *  Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForAjaxRequestDone(final Selenium selenium,
            final String timeout)
    {
        new Wait()
        {
            public boolean until()
            {
                return selenium.getEval(
                        "selenium.browserbot.getCurrentWindow().jQuery.active")
                        .equals("0");
            }
        }.wait("The ajax request did not completed within " + timeout + " ms.",
                Integer.parseInt(timeout));
//        selenium.waitForCondition(
//                "selenium.browserbot.getCurrentWindow().jQuery.active == 0",
//                timeout);

    }

    /**
     * A utility that waits for a page load to complete. Requires current
     * selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForPageOnLoadStart(final Selenium selenium,
            final String timeout)
    {
        new Wait()
        {
            public boolean until()
            {
                return !selenium
                        .getEval(
              "selenium.browserbot.getCurrentWindow().document.readyState")
                        .equals("complete");
            }
        }.wait("The document ready state still 'complete' after "
                + timeout + " ms.", Integer.parseInt(timeout));
    }

    /**
     * A utility that waits for a page load to complete. Requires current
     * selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForPageOnLoadFinish(final Selenium selenium,
            final String timeout)
    {
        new Wait()
        {
            public boolean until()
            {
                return selenium
                        .getEval(
              "selenium.browserbot.getCurrentWindow().document.readyState")
                        .equals("complete");
            }
        }.wait("The document ready state did not become 'complete' within "
                + timeout + " ms.", Integer.parseInt(timeout));
    }

    /**
     * A utility that waits for an ajax request to start.
     *  Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForAjaxRequestStart(final Selenium selenium,
            final String timeout)
    {
        selenium.waitForCondition(
            "selenium.browserbot.getCurrentWindow().jQuery.active != 0",
            timeout);
    }

    /**
     * A utility that waits for all ajax request to complete.
     * If a request is active waits for that to finish,
     *  than waits a predefined amount time whether a new request is started.
     * This loops until no active request found after the predefined wait time.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void waitForAllAjaxRequestsDone(final Selenium selenium,
            final String timeout, final int logLevel)
    {
        /**
         * Wait time for a new ajax request to start.
         */
        final int waitForNewRequest = 1000;
        while (!selenium.getEval(
            "selenium.browserbot.getCurrentWindow().jQuery.active").equals(
            "0"))
        {
            Reporter.log("Waiting for ajax request to finish.", logLevel);
            waitForAjaxRequestDone(selenium, timeout);
            Reporter.log("Waiting for new ajax request to begin...", logLevel);
            waitTime(waitForNewRequest);
        }
    }

    /**
     * A utility that waits for all ajax request to complete.
     * If a request is active waits for that to finish,
     *  than waits a predefined amount time whether a new request is started.
     * This loops until no active request found after the predefined wait time.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param timeout
     *            The maximum amount of time to wait in milliseconds.
     */
    public void waitForAllAjaxRequestsDone(final Selenium selenium,
            final String timeout)
    {
        waitForAllAjaxRequestsDone(selenium, timeout, UTIL_LOG_LEVEL);
    }

    /**
     * A utility that waits for a specific amount of time.
     *
     * @param timeToWait
     *            Time to wait in ms.
     */
    public void waitTime(final int timeToWait)
    {
        try
        {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e)
        {
            Reporter.log(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * A utility that merges two ArrayLists (of type String) and avoiding
     * duplicate entries. The "intoThis" ArrayList is not checked for
     * duplications that are already exists.
     *
     * @param mergeThis
     *            the list to be merged into an other list.
     * @param intoThis
     *            the list that will contain all elements.
     * @param logLevel
     *            is the integer value of the actual log level
     * @return intoThis after the merge is done or Assert failure occurs.
     */
    public ArrayList<String> mergeArrayListsWithNoDuplicate(
            final ArrayList<String> mergeThis,
            final ArrayList<String> intoThis, final int logLevel)
    {
        for (int i = 0; i < mergeThis.size(); i++)
        {

            if (intoThis.contains(mergeThis.get(i)))
            {
                Assert.fail(
                    "The following element is duplicate in the merged list: '"
                    + mergeThis.get(i) + "'");
            } else
            {
                Reporter.log("'" + mergeThis.get(i).toString()
                        + "' added to list items.", logLevel);
                intoThis.add(mergeThis.get(i));
            }
        }
        return intoThis;
    }

    /**
     * A utility that merges two ArrayLists (of type String) and avoiding
     * duplicate entries. The "intoThis" ArrayList is not checked for
     * duplications that are already exists.
     *
     * @param mergeThis
     *            the list to be merged into an other list.
     * @param intoThis
     *            the list that will contain all elements.
     * @return intoThis after the merge is done or Assert failure occurs.
     */
    public ArrayList<String> mergeArrayListsWithNoDuplicate(
            final ArrayList<String> mergeThis,
            final ArrayList<String> intoThis)
    {
        return mergeArrayListsWithNoDuplicate(mergeThis, intoThis,
                UTIL_LOG_LEVEL);
    }

    /**
     * A utility that generates random String with a given length of characters.
     * duplicate entries. The random string lowercase characters and numbers.
     *
     * @param length
     *            the generated String's length
     * @return the generated random String
     */
    public String generateRandomString(final int length)
    {
        /**
         * the list of characters that the random string can contains
         */
        final String validCharacters =
             "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        /**
         * random generator to generate random indexes in a range
         */
        final Random randomGenerator = new Random();
        /**
         * random index to pick a character from valid characters
         */
        int randIndex = 0;
        /**
         * the random string for return
         */
        String randomString = "";

        for (int i = 0; i < length; i++)
        {
            randIndex = randomGenerator.nextInt(validCharacters.length());
            randomString = randomString
                           + validCharacters.substring(randIndex,
                                   randIndex + 1);
        }
        return randomString;
    }

    /**
     * Checks whether an element text is written in the appropriate language.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param elementName
     *            the name of the element locator.
     * @param elementContainer
     *            Page class containing the given element.
     * @param langLocatorType
     *      element locator type addressing the checked language.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void checkElementTextLanguage(final Selenium selenium,
            final String elementName, final Page elementContainer,
            final LocatorTypes langLocatorType, final int logLevel)
    {
        /**
         * Wait for element default timeout.
         */
        final int elementWaitTimeout = 30000;

        /**
         * The locator string of the element.
         */
        final String elementLocator = elementContainer.getElementLocator(
            elementName);

        /**
         * The expected text of the given element.
         */
        final String expectedText = elementContainer.getElementLocator(
            langLocatorType, elementName);

        Reporter.log("Checking element '" + elementName + "' text is '"
                     + expectedText + "'.", logLevel);
        waitForElement(selenium, elementLocator, elementWaitTimeout, logLevel);

        /**
         * The actual text of the given element on the page.
         */
        final String actualText = selenium.getText(elementLocator);
        Assert.assertTrue(actualText.equals(expectedText),
                "'" + elementLocator
                + "' text must be '" + expectedText + "' but it is '"
                + actualText + "'");
    }

    /**
     * Checks whether an element text is written in the appropriate language.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param elementName
     *            the name of the element locator.
     * @param elementContainer
     *            Page class containing the given element.
     * @param langLocatorType
     *      element locator type addressing the checked language.
     */
    public void checkElementTextLanguage(final Selenium selenium,
            final String elementName, final Page elementContainer,
            final LocatorTypes langLocatorType)
    {
        checkElementTextLanguage(selenium, elementName, elementContainer,
                langLocatorType, UTIL_LOG_LEVEL);
    }

    /**
     * Checks whether an element value is written in the appropriate language.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param elementName
     *            the name of the element locator.
     * @param elementContainer
     *            Page class containing the given element.
     * @param langLocatorType
     *      element locator type addressing the checked language.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void checkElementValueLanguage(final Selenium selenium,
            final String elementName, final Page elementContainer,
            final LocatorTypes langLocatorType, final int logLevel)
    {
        /**
         * Wait for element default timeout.
         */
        final int elementWaitTimeout = 30000;

        /**
         * The locator string of the element.
         */
        final String elementLocator = elementContainer.getElementLocator(
            elementName);

        /**
         * The expected value of the given element.
         */
        final String expectedValue = elementContainer.getElementLocator(
            langLocatorType, elementName);

        Reporter.log("Checking element '" + elementName + "' value is '"
                     + expectedValue + "'.", logLevel);
        waitForElement(selenium, elementLocator, elementWaitTimeout, logLevel);

        /**
         * The actual value of the given element on the page.
         */
        final String actualValue = selenium.getAttribute(elementLocator
                                                         + "@value");
        Assert.assertTrue(actualValue.equals(expectedValue),
                          "'"
                          + elementLocator + "' value must be '" + expectedValue
                          + "' but it is '" + actualValue + "'");
    }

    /**
     * Checks whether an element value is written in the appropriate language.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param elementName
     *            the name of the element locator.
     * @param elementContainer
     *            Page class containing the given element.
     * @param langLocatorType
     *      element locator type addressing the checked language.
     */
    public void checkElementValueLanguage(final Selenium selenium,
            final String elementName, final Page elementContainer,
            final LocatorTypes langLocatorType)
    {
        checkElementValueLanguage(selenium, elementName, elementContainer,
                langLocatorType, UTIL_LOG_LEVEL);
    }

    /**
     * Returns the integer value of the elements' left border position.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param elementLocator
     *            the locator of the element.
     * @return the elements' left side pixel position
     */
    public int getLeftPosition(final Selenium selenium,
            final String elementLocator)
    {
       return selenium.getElementPositionLeft(elementLocator).intValue();
    }

    /**
     * Returns the integer value of the elements' right border position.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param elementLocator
     *            the locator of the element.
     * @return the elements' right side pixel position
     */
    public int getRightPosition(final Selenium selenium,
            final String elementLocator)
    {
        return selenium.getElementPositionLeft(elementLocator).intValue()
                + selenium.getElementWidth(elementLocator).intValue();
    }

    /**
     * Returns the integer value of the elements' upper border position.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param elementLocator
     *            the locator of the element.
     * @return the elements' top side pixel position
     */
    public int getTopPosition(final Selenium selenium,
            final String elementLocator)
    {
        return selenium.getElementPositionTop(elementLocator).intValue();
    }

    /**
     * Returns the integer value of the elements' lower border position.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param elementLocator
     *            the locator of the element.
     * @return the elements' bottom side pixel position
     */
    public int getBottomPosition(final Selenium selenium,
            final String elementLocator)
    {
        return selenium.getElementPositionTop(elementLocator).intValue()
                + selenium.getElementHeight(elementLocator).intValue();
    }

    /**
     * Returns the integer value of the elements' vertical (|) middle position.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param elementLocator
     *            the locator of the element.
     * @return the elements' center pixel position in the vertical (|) direction
     */
    public int getVerticalMiddlePosition(final Selenium selenium,
            final String elementLocator)
    {
        return Math.round((getBottomPosition(selenium, elementLocator) +
                            getTopPosition(selenium, elementLocator)) / 2);
    }

    /**
     * Returns the integer value of the elements' horizontal (-) middle
     * position.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param elementLocator
     *            the locator of the element.
     * @return the elements' center pixel position in the horizontal (-)
     *         direction
     */
    public int getHorizontalMiddlePosition(final Selenium selenium,
            final String elementLocator)
    {
        return Math.round((getLeftPosition(selenium, elementLocator) +
                            getRightPosition(selenium, elementLocator)) / 2);
    }

    /**
     * Checks the relative layout between two UI elements. The the right element
     * left border must be on the right of the left element's right border.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param leftElementLocator
     *            the left element locator.
     * @param rightElementLocator
     *            the right element locator.
     */
    public void checkLayoutPositionOnTheRight(final Selenium selenium,
            final String leftElementLocator, final String rightElementLocator)
    {
        Assert.assertTrue(getRightPosition(selenium, leftElementLocator) <=
                            getLeftPosition(selenium, rightElementLocator),
                        "Left to right layout check fail. (left element: '"
                                + leftElementLocator + "', right element: '"
                                + rightElementLocator + "')");
    }

    /**
     * Checks the relative layout between two UI elements. The the upper element
     * lower border must be over of the lower element's upper border.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param upperElementLocator
     *            the lower element locator.
     * @param lowerElementLocator
     *            the right element locator.
     */
    public void checkLayoutPositionUnder(final Selenium selenium,
            final String upperElementLocator, final String lowerElementLocator)
    {
        Assert.assertTrue(getBottomPosition(selenium, upperElementLocator) <=
                            getTopPosition(selenium, lowerElementLocator),
                        "Upper under lower layout check fail. (upper element: '"
                                + upperElementLocator + "', lower element: '"
                                + lowerElementLocator + "')");
    }

    /**
     * Checks the relative layout between two UI elements. The the outer
     * elements' borders must cover the inner elements' borders.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param outerElementLocator
     *            the outer element locator.
     * @param innerElementLocator
     *            the inner element locator.
     */
    public void checkLayoutPositionCover(final Selenium selenium,
            final String outerElementLocator, final String innerElementLocator)
    {
        Assert.assertTrue(getBottomPosition(selenium, outerElementLocator) >=
                          getBottomPosition(selenium, innerElementLocator) &
                           getRightPosition(selenium, outerElementLocator) >=
                           getRightPosition(selenium, innerElementLocator) &
                            getTopPosition(selenium, outerElementLocator) <=
                            getTopPosition(selenium, innerElementLocator) &
                             getLeftPosition(selenium, outerElementLocator) <=
                             getLeftPosition(selenium, innerElementLocator),
                      "Outer covers inside layout check fail. (outer element: '"
                                + outerElementLocator + "', inner element: '"
                                + innerElementLocator + "')");
    }

    /**
     * Gives the actual time in the specified format by using SimpleDateFormat
     * and Calendar.
     *
     * @param dateFormat
     *            the format string of the returned time text.
     *            (e.g. "yyyy-MM-dd HH:mm:ss")
     *
     * @return The current date formatted according to the given dateFormat.
     */
    public String now(final String dateFormat)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    /**
     * Add time to the actual time in the specified format by using
     * SimpleDateFormat and Calendar.
     *
     * @param dateFormat
     *            the format string of the returned time text.
     *            (e.g. "yyyy-MM-dd HH:mm:ss")
     * @param calendarField
     *            The calendar field (e.g. Calendar.Day, Calendar.Hour)
     *            which increase the value.
     * @param valueToAdd
     *            the number to Add the selected calendar field.
     * @return The date formatted according to the given dateFormat
     */
    public String nowPlusTime(final String dateFormat, final int calendarField,
            final int valueToAdd)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar cal = Calendar.getInstance();
        cal.add(calendarField, valueToAdd);
        return sdf.format(cal.getTime());
    }

    /**
     * Kill a running process.
     *
     * @param processName
     *            Name of the process to kill.
     * @param logLevel
     *            is the integer value of the actual log level
     */
    public void killProcess(final String processName, final int logLevel)
    {
        Reporter.log("Detecting operating system...", logLevel);
        String opSystem = System.getProperty("os.name");
        Reporter.log("Op system is: " + opSystem, logLevel);

        if (opSystem.toLowerCase().contains("win"))
        {
            Reporter.log("Executing win kill...", logLevel);
            try
            {
                Runtime.getRuntime().exec("pskill " + processName);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Kill a running process.
     *
     * @param processName
     *            Name of the process to kill.
     */
    public void killProcess(final String processName)
    {
        killProcess(processName, UTIL_LOG_LEVEL);
    }

    /**
     * Configuration.
     */
    private Configuration config;

    /**
     * Private constructor for Utilities class.
     * @param configParam Configuration
     */
    public Utilities(final Configuration configParam)
    {
        this.config = configParam;
    }

    /**
     * Get Configuration.
     * @return Configuration
     */
    protected Configuration getConfig()
    {
        return this.config;
    }
}
