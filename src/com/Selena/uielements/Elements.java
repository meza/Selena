package com.Selena.uielements;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import java.util.List;


/**
 * Web Element object.
 *
 * @author Brautigam Gergely
 *
 */
@Root(name = "Elements")
public class Elements
{

    @ElementList(name = "Group", inline=true)
    public List<Group> groups;
//
//    /**
//     * <WebElement name="xy"/>
//     */
//    @Attribute(name="name", required=false)
//    public String name;
}

