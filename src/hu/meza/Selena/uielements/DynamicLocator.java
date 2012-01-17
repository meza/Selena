package hu.meza.Selena.uielements;

import java.util.ArrayList;

import org.testng.Assert;


/**
 *
 * @author ChaotX
 */
public class DynamicLocator {

    /**
     * The maximum number of segments.
     */
    private final int maxSegments = 64;

    /**
     * Replace locator type, stores the replacable part of the locator.
     */
    private final LocatorTypes replaceType = LocatorTypes.VALUE;

    /**
     * Appendable locator segmen storage, append this get indexed elements.
     */
    private final LocatorTypes appendType = LocatorTypes.CLASS;

    /**
     * white space to divide dynamic locator segments.
     */
    private final String betweenSegments = " ";

    /**
     * The number of segments the dynamic locator is bulid up.
     */
    private int segments = 0;

    /**
     * The number of segments the dynamic locator is bulid up.
     */
    private LocatorTypes baseLocatorType = LocatorTypes.CSS;

    /**
     * The names of the locators.
     */
    private ArrayList<String> locatorNames;

    /**
     * The locators for each segment.
     */
    private ArrayList<String> locatorStrings;

    /**
     * What to replace in the locator.
     */
    private ArrayList<String> whatToReplace;

    /**
     * What to replace in the locator.
     */
    private ArrayList<String> whatToAppend;

    /**
     * Page where the locators are defined.
     */
    private Page pageUI;

    /**
     * Whether the locator is a replaceType or not.
     */
    private boolean[] isReplaceType = new boolean[maxSegments];

    /**
     * Whether the locator is an append type or not.
     */
    private boolean[] isAppendType = new boolean[maxSegments];

    /**
     * Creates the dynamic locator based on a locator name and a Page where it
     * is defined. The dynamic locator is using CSS locators only. Capable to
     * build it from concatenated segments. A segment is capable to:
     * 1. Replace a predefined part (defined as a <value> in ui.xml)
     *    of the locator.
     * 2. With a given integer value it can address nth element of the
     *    same type. This must be achieved by appending n-1 times a next
     *    sibling part (defined as a <class> in ui.xml)
     *    For the 1st element it will append a ':first-child' pseudo class.
     *    For all up coming n-1 elements it will append the defined substring.
     * 3. Without any appended or replaced part.
     *
     * If the locator has a defined <value> or <class> type, it will be
     *  automatically stored as the corresponding dynamic locator.
     * If neither <value> nor <class> is defined for the locator it will be a
     *  static segment.
     * The segments with the actual replaced and/or appended parts can be read
     * with the .getLocator() method.
     *
     * @param locatorName
     *            name of the locator
     * @param locatorType
     *            type of the locator
     * @param pageUIParam
     *            Page elements where the locatorName is defined
     */
    public DynamicLocator(final String locatorName,
            final LocatorTypes locatorType, final Page pageUIParam)
    {
        baseLocatorType = locatorType;

        segments = 1;

        pageUI = pageUIParam;

        locatorNames = new ArrayList<String>();
        locatorStrings = new ArrayList<String>();
        whatToReplace = new ArrayList<String>();
        whatToAppend = new ArrayList<String>();

        isReplaceType = new boolean[maxSegments];
        isAppendType = new boolean[maxSegments];

        locatorNames.add(locatorName);
        locatorStrings.add(getLocatorByType(baseLocatorType, locatorName));
        isReplaceType[0] = false;
        isAppendType[0] = false;
        whatToReplace.add("");
        whatToAppend.add("");
        final boolean hasReplaceType = hasReplaceLocator(locatorName);
        final boolean hasAppendType = hasAppendLocator(locatorName);

        if (hasReplaceType)
        {
            isReplaceType[0] = true;
            whatToReplace.set(0, getLocatorByType(replaceType, locatorName));
        }
        if (hasAppendType)
        {
            isAppendType[0] = true;
            whatToAppend.set(0, getLocatorByType(appendType, locatorName));
        }
    }

    /**
     * Creates an empty DynamicLocator object.
     */
    private DynamicLocator()
    {
        locatorNames = new ArrayList<String>();
        locatorStrings = new ArrayList<String>();
        whatToReplace = new ArrayList<String>();
        whatToAppend = new ArrayList<String>();

        isReplaceType = new boolean[maxSegments];
        isAppendType = new boolean[maxSegments];
    }

    /**
     * Counts the number of true values in a given logic array till segments
     * length.
     *
     * @param logicArray
     *            logic array to count true values
     * @return number of true values in the logicArray
     */
    private int countTrue(final boolean[] logicArray)
    {
        int trueCount = 0;
        for (int i = 0; i < maxSegments; i++)
        {
            if (logicArray[i])
            {
             trueCount++;
            }
        }
        return trueCount;
    }

    /**
     * Retrieve a new Dynamic locator with the second part given as an other
     * dynamic locator.
     *
     * @param dLocator
     *            the DynamicLocator type to append to this one
     * @return the concatenated DynamicLocator
     */
    public DynamicLocator append(final DynamicLocator dLocator)
    {
        DynamicLocator appendedLocator = this.clone();
        appendedLocator.add(dLocator);
        return appendedLocator;
    }

    /**
     * Adds segments defined as a Dynamic locator to the current object.
     *
     * @param dLocator
     *            the DynamicLocator object to append to this one
     */
    private void add(final DynamicLocator dLocator)
    {
        final int firstSegments = segments;
        final int secondSegments = dLocator.segments;
        if (firstSegments + secondSegments > maxSegments)
        {
            Assert.fail("The two dynamic locators segment length ("
                    + firstSegments + "," + secondSegments
                    + ") exceed maximum length (" + maxSegments + ")!");
        }

        if(!baseLocatorType.equals(dLocator.baseLocatorType))
        {
            Assert.fail("The two DLocator has different locator types: '"
                    + baseLocatorType.name() + "' is not '"
                    + dLocator.baseLocatorType.name() + "'");
        }

        if(!pageUI.equals(dLocator.pageUI))
        {
            Assert.fail("The two DLocator has different pageUIs: '"
                    + pageUI.getPath() + "' is not '"
                    + dLocator.pageUI.getPath() + "'");
        }

        segments = firstSegments + secondSegments;
        locatorNames.addAll(dLocator.locatorNames);
        if (dLocator.locatorStrings.get(0).startsWith(
                baseLocatorType.name().toLowerCase() + "="))
        {
            locatorStrings.add(dLocator.locatorStrings.get(0).replace(
                    baseLocatorType.name().toLowerCase() + "=", ""));
        } else
        {
            locatorStrings.add(dLocator.locatorStrings.get(0));
        }
        if (dLocator.locatorStrings.size() > 1)
        {
            locatorStrings.addAll(dLocator.locatorStrings.subList(
                    1, dLocator.locatorStrings.size() - 1));
        }

        for (int i = firstSegments; i < segments; i++)
        {
            isReplaceType[i] = dLocator.isReplaceType[i
                    - firstSegments];
        }

        for (int i = firstSegments; i < segments; i++)
        {
            isAppendType[i] = dLocator.isAppendType[i
                    - firstSegments];
        }

        whatToReplace.addAll(dLocator.whatToReplace);
        whatToAppend.addAll(dLocator.whatToAppend);
    }

    /**
     * Retrieve a new Dynamic locator with the second part given as an other
     * dynamic locator.
     *
     * @param locatorName
     *            name of the locator
     * @return the concatenated DynamicLocator
     *
     */
    public DynamicLocator append(final String locatorName)
    {
        return append(new DynamicLocator(locatorName, this.baseLocatorType,
                this.pageUI));
    }

    @Override
    public DynamicLocator clone()
    {
        DynamicLocator newDLocator =new DynamicLocator();

        newDLocator.pageUI=pageUI;
        newDLocator.baseLocatorType=baseLocatorType;
        newDLocator.segments=segments;
        newDLocator.locatorNames.addAll(locatorNames);
        newDLocator.locatorStrings.addAll(locatorStrings);
        newDLocator.isReplaceType=isReplaceType.clone();
        newDLocator.isAppendType=isAppendType.clone();
        newDLocator.whatToReplace.addAll(whatToReplace);
        newDLocator.whatToAppend.addAll(whatToAppend);
        return newDLocator;
    }

    /**
     * Gets the value of the Locator for a specific set of input arguments. The
     * number of input arguments must match the number of segment modifications
     * needed to generate the dynamic locator.
     * The number of Integer input arguments must match the number of
     *  appendable segments.
     * The number of String input arguments must match the number of
     *  replaceable segments.
     * The order of the input arguments affect the corresponding
     *  segment locator generation.
     * The segments are generated from the beginning till the end
     *  (left to right).
     * Give integer for an append type segment, give String for a replace type
     *  segment.
     *
     * @param args
     *          the changable arguments needed to generate a valid locator
     *
     * @return the value.
     *          the css locator string generated based on the input arguments
     */
    public String getLocator(final Object... args)
    {
        int[] intArgs = new int[maxSegments];
        String[] stringArgs = new String[maxSegments];
        int numOfIntArgs = 0;
        int numOfStringArgs = 0;
        for (int i = 0; i < args.length; i++)
        {
            if(args[i] instanceof Integer)
            {
                intArgs[numOfIntArgs]= (Integer) args[i];
                numOfIntArgs++;
            }
            if(args[i] instanceof String)
            {
                stringArgs[numOfStringArgs]= (String) args[i];
                numOfStringArgs++;
            }
        }

        checkInputArguments(numOfIntArgs, numOfStringArgs);

        int nextIntInput = 0;
        int nextStringInput = 0;
        String fullLocator = "";
        for (int i = 0; i < segments; i++)
        {
            String addThis = locatorStrings.get(i);
            if (isReplaceType[i] || isAppendType[i])
            {
                if (isAppendType[i])
                {
                    addThis = getAppendedLocator(intArgs[nextIntInput], i);
                    nextIntInput++;
                }
                if (isReplaceType[i])
                {
                    addThis = addThis.replace(whatToReplace.get(i),
                            stringArgs[nextStringInput]);
                    nextStringInput++;
                }
            }
            fullLocator += betweenSegments + addThis;
        }
        return fullLocator.substring(1);
    }

    /**
     * Returns an appended segment locator string.
     * @param appendNum
     *          number of times append should occurs
     * @param segmentNum
     *          index of the segment
     * @return the appended locator string
     */
    private String getAppendedLocator(final int appendNum, final int segmentNum)
    {
        String appenedLocator = locatorStrings.get(segmentNum);
        if (appendNum > 0 && baseLocatorType.equals(LocatorTypes.CSS))
        {
                appenedLocator +=  ":first-child";
        }
        for (int i = 0; i < appendNum - 1; i++)
        {
            appenedLocator += betweenSegments + whatToAppend.get(segmentNum);
        }
        return appenedLocator;
    }

    /**
     * Retrieves the name of the locator for the specific dynamic modifiers.
     * @param args
     *          the changeable arguments needed to generate a valid locator
     *
     * @return the value.
     *          the css locator string generated based on the input arguments
     */
    public String getName(final Object... args)
    {
        int[] intArgs = new int[maxSegments];
        String[] stringArgs = new String[maxSegments];
        int numOfIntArgs = 0;
        int numOfStringArgs = 0;
        for (int i = 0; i < args.length; i++)
        {
            if(args[i] instanceof Integer)
            {
                intArgs[numOfIntArgs]= (Integer) args[i];
                numOfIntArgs++;
            }
            if(args[i] instanceof String)
            {
                stringArgs[numOfStringArgs]= (String) args[i];
                numOfStringArgs++;
            }
        }
        checkInputArguments(numOfIntArgs, numOfStringArgs);

        int nextIntInput = 0;
        int nextStringInput = 0;

        String fullName = "";
        for (int i = 0; i < segments; i++)
        {
            String addThis = locatorNames.get(i);
            if (isReplaceType[i] || isAppendType[i])
            {
                if (isAppendType[i])
                {
                    addThis = intArgs[nextIntInput] + "." + addThis;
                    nextIntInput++;
                }
                if (isReplaceType[i])
                {
                    addThis = addThis + "(" + stringArgs[nextStringInput] + ")";
                    nextStringInput++;
                }
            }
            fullName += betweenSegments + addThis;
        }
        return fullName.substring(1);
    }

    /**
     * Checks the number of input arguments.
     * @param numOfIntArgs
     *          the number of Integer input arguments
     * @param numOfStringArgs
     *          the number of String input arguments
     */
    private void checkInputArguments(final int numOfIntArgs,
            final int numOfStringArgs)
    {
        final int intArgsNeeded = countTrue(isAppendType);
        final int stringArgsNeeded = countTrue(isReplaceType);

        if (stringArgsNeeded != numOfStringArgs)
        {
            Assert.fail(stringArgsNeeded + " String argumets needed, but got "
                    + numOfStringArgs + "!");
        }
        if (intArgsNeeded != numOfIntArgs)
        {
            Assert.fail(intArgsNeeded + " Integer argumets needed, but got "
                    + numOfIntArgs + "!");
        }
    }

    /**
     * Reads a locator from the uixml and throws fail exception if the value is
     * null pointer or empty string.
     *
     * @param locatorType
     *            type of the locator to read
     * @param locatorName
     *            name of the locator
     * @return the locator string read from the .
     */
    private String getLocatorByType(final LocatorTypes locatorType,
            final String locatorName)
    {
        final String locator = pageUI.getElementLocator(locatorType,
                locatorName);
        if (locator == null || locator.isEmpty()
                || locator.equals(locatorType.name().toLowerCase() + "="))
        {
            Assert.fail("The read from '" + locatorType.name() + "' type '"
                    + locatorName + "' locator returnd '" + locator + "'!");
        }
        return locator;
    }

    /**
     * Determines whether the locator has a defined appendType definition.
     *
     * @param locatorName
     *            name of the locator
     * @return true if appendType locator type is defined, false otherwise.
     */
    private boolean hasAppendLocator(final String locatorName)
    {
        try
        {
            if (pageUI.getElementLocator(appendType, locatorName) == null)
            {
                return false;
            }
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * Determines whether the locator has a defined replaceType definition.
     *
     * @param locatorName
     *            name of the locator
     * @return true if replaceType locator type is defined, false otherwise.
     */
    private boolean hasReplaceLocator(final String locatorName)
    {
        try
        {
            if (pageUI.getElementLocator(replaceType, locatorName) == null)
            {
                return false;
            }
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
