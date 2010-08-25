/**
 * Custom annotation. Used to mark a test as draft so that we are able to
 * search for it, and distinguish between live and draft tests.
 *
 * @author Brautigam Gergely
 */
package com.Selena.annotation;


/**
 *
 * Draft annotation type. Used to mark a test as draft.
 *
 * @author Brautigam Gergely
 *
 */
public @interface Draft
{

    /**
     * Author.
     * @return
     */
    String author();


    /**
     * Version.
     * @return
     */
    String version();


    /**
     * Short description.
     * @return
     */
    String shortDescription();


}

