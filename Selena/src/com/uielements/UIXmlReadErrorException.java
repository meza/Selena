package com.uielements;


/**
 * The Exception handles cases when a UIElement config file can not be parsed
 * @author meza <meza@meza.hu>
 */
public class UIXmlReadErrorException extends RuntimeException
{

    public UIXmlReadErrorException(String s)
    {
        super(String.format("The UIXML file could not be parsed. The following"
                            + " error occured: \n%s", s));
    }


}

