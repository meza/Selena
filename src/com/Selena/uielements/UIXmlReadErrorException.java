package com.Selena.uielements;


/**
 * The Exception handles cases when a UIElement config file can not be parsed.
 * @author meza <meza@meza.hu>
 */
public class UIXmlReadErrorException extends RuntimeException
{

    /**
     * Default Serial Version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * UIXml Read Error Exxception.
     * @param s The Error.
     */
    public UIXmlReadErrorException(final String s)
    {
        super(String.format("The UIXML file could not be parsed. The following"
                            + " error occured: \n%s", s));
    }


}

