package com.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.testng.Assert;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.Wait;
import com.uielements.LocatorTypes;
import com.uielements.UIElements;

/**
 *
 * @author Brautigam Gergely
 *
 */
public final class Utilities
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
    public static void waitForPage(final Selenium selenium,
            final String urlToAppear, final String timeout)
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
    public static void waitForElement(final Selenium selenium,
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
    public static void waitForNotPresent(final Selenium selenium,
            final String locator, final int timeout)
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
    public static void waitForVisible(final Selenium selenium,
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
    public static void waitForNotVisible(final Selenium selenium,
            final String locator, final int timeout)
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
    public static void waitForTextToAppear(final Selenium selenium,
            final String textToAppear, final long timeout)
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
    public static void waitForTextToChange(final Selenium selenium,
            final String locator, final int timeout)
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
    public static void waitForAttributeToChange(final Selenium selenium,
            final String locator, final String attribute, final int timeout)
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
        final String oldAttribute=actAttribute;

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
    public static String getAttribute(final Selenium selenium,
            final String locator, final String attribute)
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
    public static void captureScreenshot(final Selenium selenium,
            final String filename)
    {
        String javaScriptUserAgent = selenium.getEval(
                "selenium.browserbot.getCurrentWindow().navigator.userAgent");

        Calendar now = Calendar.getInstance();

        String fileNameWithoutExtension =
            filename.substring(0, filename.indexOf('.'));
        String extension =
            filename.substring(filename.indexOf('.'), filename.length());

        String fileNameWithTimeStamp = String.format(
                                            "%s%d%d%d",
                                            fileNameWithoutExtension,
                                            now.get(Calendar.DAY_OF_MONTH),
                                            now.get(Calendar.MONTH) + 1,
                                            now.get(Calendar.YEAR));

        fileNameWithTimeStamp = fileNameWithTimeStamp + extension;

        Reporter.log("Filename will be: " + fileNameWithTimeStamp);

        String runningDir = System.getProperty("user.dir");

        Reporter.log("The running directory is: " + runningDir);

        String savePath ="output/imgs/" + fileNameWithTimeStamp;

        Reporter.log("Save Path: " + savePath);


        if (javaScriptUserAgent.contains("Firefox"))
        {
            try
            {
                selenium.captureEntirePageScreenshot(runningDir + "/"
                        + savePath, "");
            } catch (Exception e)
            {
                Reporter.log("Couldn't use EntirePageScreenshot"
                        + " -> using simple captureScreenshot.");
                selenium.captureScreenshot(runningDir + "\\" + savePath);
            }
        } else
        {
            selenium.captureScreenshot(runningDir + "\\" + savePath);
        }

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
    public static void waitForAjaxRequestDone(final Selenium selenium,
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
    public static void waitForAjaxRequestStart(final Selenium selenium,
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
    public static void waitForAllAjaxRequestsDone(final Selenium selenium,
            final String timeout)
    {
        /**
         * Wait time for a new ajax request to start.
         */
        final int waitForNewRequest=1000;
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
    public static void waitTime(final int timeToWait)
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
    public static ArrayList<String> mergeArrayListsWithNoDuplicate(
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
    public static String generateRandomString(final int length)
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
                    + validCharacters.substring(randIndex, randIndex + 1);
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
     *            UIElements class containing the given element.
     * @param langLocatorType
     *      element locator type addressing the checked language.
     */
    public static void checkElementTextLanguage(final Selenium selenium,
            final String elementName, final UIElements elementContainer,
            final LocatorTypes langLocatorType)
    {
        /**
         * Wait for element default timeout.
         */
        final int elementWaitTimeout = 30000;

        /**
         * The locator string of the element.
         */
        final String elementLocator = elementContainer
                .getElementLocator(elementName);

        /**
         * The expected text of the given element.
         */
        final String expectedText = elementContainer.getElementLocator(
                langLocatorType, elementName);

        Reporter.log("Checking element '" + elementName + "' text is '"
                + expectedText + "'.");
        waitForElement(selenium, elementLocator, elementWaitTimeout);

        /**
         * The actual text of the given element on the page.
         */
        final String actualText = selenium.getText(elementLocator);
        Assert.assertTrue(actualText.equals(expectedText), "'" + elementLocator
                + "' text must be '" + expectedText + "' but it is '"
                + actualText + "'");
    }

    /**
     * Checks whether an element value is written in the appropriate language.
     *
     * @param selenium
     *            The selenium instances currently in work.
     * @param elementName
     *            the name of the element locator.
     * @param elementContainer
     *            UIElements class containing the given element.
     * @param langLocatorType
     *      element locator type addressing the checked language.
     */
    public static void checkElementValueLanguage(final Selenium selenium,
            final String elementName, final UIElements elementContainer,
            final LocatorTypes langLocatorType)
    {
        /**
         * Wait for element default timeout.
         */
        final int elementWaitTimeout = 30000;

        /**
         * The locator string of the element.
         */
        final String elementLocator = elementContainer
                .getElementLocator(elementName);

        /**
         * The expected value of the given element.
         */
        final String expectedValue = elementContainer.getElementLocator(
                langLocatorType, elementName);

        Reporter.log("Checking element '" + elementName + "' value is '"
                + expectedValue + "'.");
        waitForElement(selenium, elementLocator, elementWaitTimeout);

        /**
         * The actual value of the given element on the page.
         */
        final String actualValue = selenium.getAttribute(elementLocator
                + "@value");
        Assert.assertTrue(actualValue.equals(expectedValue), "'"
                + elementLocator + "' value must be '" + expectedValue
                + "' but it is '" + actualValue + "'");

    }


    /**
     * Save the result of the test into an xml file.
     *
     * @param id ID of the test
     * @param steps Steps that passed
     * @param xlsUrl The url to the xls file that contains the covered test
     */
    public static void saveResult(
            final String id,
            final int steps,
            final String xlsUrl)
    {

        // Set output directory.
        String outputDir = "output/xmlresults/";
        Calendar cal = Calendar.getInstance();

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        String fileName = id + "" + day + "" + month + "" + year;

        String runningDir = System.getProperty("user.dir");

        // Create file name with date stamp
        String path = runningDir + "/" + outputDir + "/" + fileName + ".xml";

        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;

        try
        {
            docBuilder = dbfac.newDocumentBuilder();
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }

        Document doc = docBuilder.newDocument();

        //document root element
        Element resultRoot = doc.createElement("result");
        doc.appendChild(resultRoot);

        //test element
        Element testChild = doc.createElement("test");
        resultRoot.appendChild(testChild);

        //the three inner elements
        Element idChild = doc.createElement("id");
        Element urlChild = doc.createElement("url");
        Element stepsChild = doc.createElement("steps");

        testChild.appendChild(idChild);
        testChild.appendChild(urlChild);
        testChild.appendChild(stepsChild);


        Text idText = doc.createTextNode(id);
        Text urlText = doc.createTextNode(xlsUrl);
        Text stepsText = doc.createTextNode(String.valueOf(steps));

        //Add the text to the children
        idChild.appendChild(idText);
        urlChild.appendChild(urlText);
        stepsChild.appendChild(stepsText);

        //create the transformer factory
        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = null;

        try
        {
            trans = transfac.newTransformer();
        } catch (TransformerConfigurationException e2)
        {
            e2.printStackTrace();
        }

        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");

        //create string from xml tree
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(doc);
        try
        {
            trans.transform(source, result);
        } catch (TransformerException e1)
        {
            e1.printStackTrace();
        }

        String xmlString = sw.toString();

        try
        {
            // Create file
            FileWriter fstream = new FileWriter(path, false);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(xmlString);
            //Close the output stream
            out.close();

        } catch (IOException ie){//Catch exception if any
            System.err.println("Error by creating file: " + ie.getMessage());
        }
    }


    /**
     * Private constructor for Utilities class.
     */
    private Utilities()
    {
    }
}
