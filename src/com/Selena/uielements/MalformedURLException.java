/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Selena.uielements;


/**
 * Malformed URL Exception.
 * @author meza
 */
public class MalformedURLException extends RuntimeException
{

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Malformed URL Exception.
     * @param baseUrl Base URL
     * @param path Path
     * @param cause Cause
     */
    public MalformedURLException(
            final String baseUrl,
            final String path,
            final String cause)
    {
        super(String.format("Bad URL (%s)\nbaseUrl: %s\npath: %s",
                            cause, baseUrl, path));
    }


}

