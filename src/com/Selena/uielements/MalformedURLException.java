/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Selena.uielements;


/**
 *
 * @author meza
 */
public class MalformedURLException extends RuntimeException
{

    public MalformedURLException(String baseUrl, String path, String cause)
    {
        super(String.format("Bad URL (%s)\nbaseUrl: %s\npath: %s",
                            cause, baseUrl, path));
    }


}

