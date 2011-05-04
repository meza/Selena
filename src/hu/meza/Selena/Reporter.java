/**
 * Using testNG reporter to log new lines into reports that works
 *  with reportNG as pure xml code.
 */
package hu.meza.Selena;

import org.testng.ITestResult;

/**
 * @author ChaotX
 *
 */
public final class Reporter
{

    /**
     * New line character sequence.
     */
    public static final String NEWLINE = "<br>";

    /**
     * Counter to generate individual id/s for toggle text links.
     */
    private static int textIdCounter = 0;

    /**
     * Main verbose level of logging. If this value is greater or equal then the
     * given input verbosity, the reporter wont print message into the html
     * report.
     * */
    private static final int BASE_VERBOSITY = 1;

    /**
     * Private Constructor.
     */
    private Reporter()
    {

    }

    /**
     * Adds an html new line sign to the end of the input string.
     *
     * @param lineText
     *            any string that will appear in the html report
     * @return lineText appended with a new line sign
     */
    private static String addNewLine(final String lineText)
    {
        return lineText + NEWLINE;
    }

    /**
     * Determines wether the given log level allows to write log text or not.
     *
     * @param level
     *            is the integer value of the actual log level
     * @return true if the given log level is grater or equals then the base log
     *         level, false otherwise
     */
    private static boolean isLogLevel(final int level)
    {
        return BASE_VERBOSITY <= level;
    }

    /**
     * Paste a new line into the html report as xml source, using original
     * testNG reporter class.
     *
     * @param logLineText
     *            xml code of a new line in the report
     */
    public static void log(final String logLineText)
    {
        org.testng.Reporter.log(addNewLine(logLineText));
    }

    /**
     * Paste a new line into the html report as xml source, using original
     * testNG reporter class.
     *
     * @param logLineText
     *            xml code of a new line in the report
     * @param logToStandardOut
     *          Whether to print this string on standard out too
     *
     */
    public static void log(final String logLineText,
            final boolean logToStandardOut)
    {
        if (logToStandardOut)
        {
            System.out.println(logLineText);
        }
        org.testng.Reporter.log(addNewLine(logLineText));
    }

    /**
     * Paste a new line into the html report as xml source, using original
     * testNG reporter class if the current verbosity is equal or greater than
     * the one passed in parameter.
     *
     * @param logLineText
     *            xml code of a new line in the report
     * @param level
     *            the verbosity of this message
     *
     */
    public static void log(final String logLineText, final int level)
    {
        if (isLogLevel(level))
        {
            org.testng.Reporter.log(addNewLine(logLineText));
        }
    }

    /**
     * Paste a new line into the html report as xml source, using original
     * testNG reporter class if the current verbosity is equal or greater than
     * the one passed in parameter.
     *
     * @param logLineText
     *            xml code of a new line in the report
     * @param level
     *            the verbosity of this message
     * @param logToStandardOut
     *          Whether to print this string on standard out too
     */
    public static void log(final String logLineText, final int level,
            final boolean logToStandardOut)
    {
        if (isLogLevel(level))
        {
            if (logToStandardOut)
            {
                System.out.println(logLineText);
            }
            org.testng.Reporter.log(addNewLine(logLineText));
        }
    }

    /**
     * Paste string into the html report as xml source, using original
     * testNG reporter class.
     *
     * @param logLineText
     *            xml code of a new line in the report
     */
    public static void logInLine(final String logLineText)
    {
        org.testng.Reporter.log(logLineText);
    }

    /**
     * Paste string into the html report as xml source, using original
     * testNG reporter class.
     *
     * @param logLineText
     *            xml code of a new line in the report
     * @param logToStandardOut
     *          Whether to print this string on standard out too
     *
     */
    public static void logInLine(final String logLineText,
            final boolean logToStandardOut)
    {
        if (logToStandardOut)
        {
            System.out.println(logLineText);
        }
        org.testng.Reporter.log(logLineText);
    }

    /**
     * Paste string into the html report as xml source, using original
     * testNG reporter class if the current verbosity is equal or greater than
     * the one passed in parameter.
     *
     * @param logLineText
     *            xml code of a new line in the report
     * @param level
     *            the verbosity of this message
     *
     */
    public static void logInLine(final String logLineText, final int level)
    {
        if (isLogLevel(level))
        {
            org.testng.Reporter.log(logLineText);
        }
    }

    /**
     * Paste string into the html report as xml source, using original
     * testNG reporter class if the current verbosity is equal or greater than
     * the one passed in parameter.
     *
     * @param logLineText
     *            xml code of a new line in the report
     * @param level
     *            the verbosity of this message
     * @param logToStandardOut
     *          Whether to print this string on standard out too
     */
    public static void logInLine(final String logLineText, final int level,
            final boolean logToStandardOut)
    {
        if (isLogLevel(level))
        {
            if (logToStandardOut)
            {
                System.out.println(logLineText);
            }
            org.testng.Reporter.log(logLineText);
        }
    }

    /**
     * Get an html link definition from an url string.
     *
     * @param url
     *            the link url
     *
     * @return the html link
     */
    public static String getHtmlLink(final String url)
    {
        return getHtmlLink(url,url);
    }

    /**
     * Get an html link definition from an url string and a displayed text
     * string.
     *
     * @param url
     *            the link url
     *
     * @param text
     *            the link text field to appear
     *
     * @return the html link
     */
    public static String getHtmlLink(final String url, final String text)
    {
        return("<a href=\"" + url
                + "\" target=\"_blank\">" + text + "</a>");
    }

    /**
     * Get a toggle link to be able to hide long output string in the report.
     *
     * @param title
     *            the link title
     *
     * @param text
     *            the text field to hide/appear
     *
     * @return the toggle part html code
     */
    public static String getToogleText(final String title, final String text)
    {
        textIdCounter++;
        final String textClass = "textoutput";
        final String textID = textClass + "-" + textIdCounter;

        return addNewLine(String.format(
                "<a title=\"Click to expand/collapse\"" +
                " href=\"javascript:toggleElement('%s', 'block')\">%s</a>",
                textID, title)) + getDiv(textID,textClass,text);
    }

    /**
     * Get a html div code with a class attributes.
     *
     * @param divClass
     *            the div class attribute
     * @param divText
     *            the div text content
     *
     * @return the toggle part html code
     */
    public static String getDiv(final String divClass,
            final String divText)
    {
        return String.format("<div class=\"%s\">%s</div>", divClass, divText);
    }

    /**
     * Get a html div code with id and class attributes.
     *
     * @param divID
     *            the div id attribute
     * @param divClass
     *            the div class attribute
     * @param divText
     *            the div text content
     *
     * @return the toggle part html code
     */
    public static String getDiv(final String divID, final String divClass,
            final String divText)
    {
        return String.format("<div id=\"%s\" class=\"%s\">%s</div>", divID,
                divClass, divText);
    }

    /**
     * Get an image insertion definition from an image path string.
     *
     * @param filePath
     *            the relative url of the file to the report html
     *
     * @return the image insert html string
     */
    public static String getImageInsert(final String filePath)
    {
        return getImageInsert(filePath, filePath);
    }

    /**
     * Get an image insertion definition from an image path string and a
     * displayed text string.
     *
     * @param filePath
     *            the relative url of the file to the report html
     *
     * @param text
     *            tooltip fot the image
     *
     * @return the image insert html string
     */
    public static String getImageInsert(final String filePath,
            final String text)
    {
        return ("<img title = \"" + text + "\" src=\"file:///" + filePath
                + "\" target=\"_blank\">" + text + "</img>");
    }

    /**
     * Gives the current test result object.
     * @return the instance of the current test result
     */
    public static ITestResult getCurrentTestResult()
    {
       return org.testng.Reporter.getCurrentTestResult();
    }

    /**
     * Sets the current test result object.
     * @param result
     *            the instance of the current test result
     */
    public static void setCurrentTestResult(final ITestResult result)
    {
       org.testng.Reporter.setCurrentTestResult(result);
    }
}
