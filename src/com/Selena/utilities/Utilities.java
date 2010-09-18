package com.Selena.utilities;

import com.Selena.Configuration;
import com.Selena.Utils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import org.testng.Assert;
import org.testng.Reporter;

import com.Selena.uielements.LocatorTypes;
import com.Selena.uielements.Page;
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
    public void waitForPage(final Selenium selenium,
                                   final String urlToAppear,
                                   final String timeout)
    {
        Reporter.log("Waiting for the page '" + urlToAppear + "' to appear.");
        new Wait()
        {

            public boolean until()
            {
                return urlToAppear.equals(selenium.getLocation());
            }


        }.wait("The page '" + urlToAppear + "' did not appear  within "
                   + timeout + " ms.", Integer.parseInt(timeout));
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
    public void waitForElement(final Selenium selenium,
                                      final String locator, final int timeout)
    {
        Reporter.log("Waiting for element '" + locator + "' to appear.");
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
                                         final String locator,
                                         final int timeout)
    {
        Reporter.log("Waiting for element '" + locator + "' to disappear.");
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
    public void waitForVisible(final Selenium selenium,
                                      final String locator, final int timeout)
    {
        Reporter.log("Waiting for element '" + locator + "' to be visible.");
        new Wait()
        {
            public boolean until()
            {
                return selenium.isVisible(locator);
            }
        }.wait("The element '" + locator + "' did not become visible within "
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
                                         final String locator,
                                         final int timeout)
    {
        Reporter.log("Waiting for element '" + locator + "' to fade away.");
        new Wait()
        {
            public boolean until()
            {
                return !selenium.isVisible(locator);
            }
        }.wait("The element '" + locator + "' did not fade away within "
                   + timeout + " ms.", timeout);
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
     *
     */
    public void waitForTextToAppear(final Selenium selenium,
                                           final String textToAppear,
                                           final long timeout)
    {
        Reporter.log("Waiting '" + textToAppear + "' text to appear.");
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
                                           final String locator,
                                           final int timeout)
    {
        /**
         * The old text that has to change.
         */
        final String oldText = selenium.getText(locator);

        Reporter.log("Waiting for '" + oldText + "' to change.");
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
                                                final String locator,
                                                final String attribute,
                                                final int timeout)
    {
        /**
         * Save the actual text that has to change (if element is not found
         * assume empty string).
         */
        String actAttribute;
        Assert.assertTrue(selenium.isElementPresent(locator));
        try
        {
            actAttribute = selenium.getAttribute(locator + attribute);
        } catch (Exception e)
        {
            actAttribute = "";
        }

        /**
         * Save the actual text to a final variable to pass it to the inner
         * class.
         */
        final String oldAttribute = actAttribute;

        Reporter.log("Waiting for element '" + locator
                     + "' to change it's '" + attribute + "' attribute from '"
                     + oldAttribute + "' to something else.");
        new Wait()
        {
            public boolean until()
            {
                Assert.assertTrue(selenium.isElementPresent(locator));
                try
                {
                    return !oldAttribute.equals(selenium.getAttribute(
                        locator
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
     * A utility that waits for a specific element's attribute to become a
     * specified value. Also works with "" content to appear.
     * Works only with XPATH locators.
     * Requires current selenium object to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element that may has an attribute. (e.g.
     *            'userName@class' or '//input[@name='q']')
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
        Reporter.log("Waiting for element '" + locator
                + "' to have an '" + attribute + "' attribute with '"
                + attributeValue + "' value.");

        waitForElement(selenium, locator + "/" + attribute, timeout);
        new Wait()
        {
            public boolean until()
            {
                return attributeValue.equals(getAttribute(selenium, locator,
                        attribute));
            }
        }.wait("The value of the '" + locator + "' element's '" + attribute
                + "' attribute did not became '" + attributeValue + "' within "
                + timeout + " ms.", timeout);
    }

    /**
     * A utility that reads a specific element's attribute. Gives "" if the
     * attribute has no value and throws exception if the attribute is not
     * present.
     * Works only with XPATH locator. Requires current selenium object
     * to work with.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param locator
     *            The locator of the element that may has an attribute. (e.g.
     *            'userName@class' or '//input[@name='q']')
     * @param attribute
     *            The name of the attribute beginning with an '@' sign. (e.g.
     *            '@value' or '@class')
     * @return the value of the located element's attribute.
     */
    public String getAttribute(final Selenium selenium,
                                      final String locator,
                                      final String attribute)
    {
        Assert.assertTrue(selenium.isElementPresent(locator + "/" + attribute),
                          "The attribute '" + locator + "/" + attribute
                          + "' could not found.");
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

        if (isFirefox(selenium))
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
     * Determines wether the browser is firefox or not.
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

        fileNameWithTimeStamp = fileNameWithTimeStamp + extension;

        String imageBaseDir = getConfig().getValue("image.dir");

        return imageBaseDir+"/"+fileNameWithTimeStamp;
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
        selenium.waitForCondition(
            "selenium.browserbot.getCurrentWindow().jQuery.active == 0",
            timeout);
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
     */
    public void waitForAllAjaxRequestsDone(final Selenium selenium,
                                                  final String timeout)
    {
        /**
         * Wait time for a new ajax request to start.
         */
        final int waitForNewRequest = 1000;
        while (!selenium.getEval(
            "selenium.browserbot.getCurrentWindow().jQuery.active").equals(
            "0"))
        {
            Reporter.log("Waiting for ajax request to finish.");
            waitForAjaxRequestDone(selenium, timeout);
            Reporter.log("Waiting for new ajax request to begin...");
            waitTime(waitForNewRequest);
        }
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
     * @return intoThis after the merge is done or Assert failure occurs.
     */
    public ArrayList<String> mergeArrayListsWithNoDuplicate(
        final ArrayList<String> mergeThis,
        final ArrayList<String> intoThis)
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
                             + "' added to list items.");
                intoThis.add(mergeThis.get(i));
            }
        }
        return intoThis;
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
     * Kill a running process.
     * @param processName Name of the process to kill.
     */
    public void killProcess(final String processName)
    {
        Reporter.log("Detecting operating system...");
        String opSystem = System.getProperty("os.name");
        Reporter.log("Op system is: " + opSystem);

        if (opSystem.toLowerCase().contains("win"))
        {
            Reporter.log("Executing win kill...");
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
