/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.meza.Selena.uielements;

/**
 *
 * @author meza
 */
public class LocatorNotFoundException extends RuntimeException{


    /**
     * Throw when a particular locator is requested on an element.
     *
     * @param elementName The WebElement object's name
     * @param locatorType The Locator object's type
     */
    public LocatorNotFoundException(
            final String elementName,
            final String locatorType
    ) {
        super(String.format("Locator type %s not found for element %s in the "
                + "UIXML config",
                locatorType,
                elementName
            )
        );
    }


    /**
     * Throw when only an element is requested.
     * @param elementName The WebElement name.
     */
    public LocatorNotFoundException(
            final String elementName
    ) {
        super(String.format("Locator not found for element %s in the "
                + "UIXML config",
                elementName
            )
        );
    }
}
