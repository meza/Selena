/**
 * This is the deserializer for the UI elements
 *
 * @author Gergely Brautigam
 */
package hu.meza.Selena.uielements;

import hu.meza.Selena.ConfigParams;
import hu.meza.Selena.Configuration;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * UI Serializer object.
 *
 * @author Brautigam Gergely
 *
 */
public class UISerializer {

    /**
     * Configuration.
     */
    private Configuration config;

    /**
     * The loocator factory to use.
     */
    private LocatorFactory locFactory;


    /**
     * UISerializer.
     * @param configParam Configuration
     * @param factory The LocatorFactory to use
     */
    public UISerializer(
        final Configuration configParam,
        final LocatorFactory factory
    ) {
        this.config = configParam;
        this.locFactory = factory;
    }

    /**
     * Deserializer for the xmlUI elements.
     *
     * @param filename The xml to deserialize
     * @throws UIXmlReadErrorException
     * @return An serialized UIElement instance
     */
    public Page deserialize(final String filename) {

        Page page = new Page();
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            Element pageElement = doc.getDocumentElement();

            page.url = pageElement.getAttribute("url");
            page.path = pageElement.getAttribute("path");
            page.setSeleniumBaseUrl(
                this.config.getValue(ConfigParams.SELENIUMBASEURL)
            );

            NodeList webElements = doc.getElementsByTagName("WebElement");

            for (int s = 0; s < webElements.getLength(); s++) {

                Node webElementNode = webElements.item(s);

                if (webElementNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element webElementElement = (Element) webElementNode;
                    String id = webElementElement.getAttribute("name");
                    WebElement webElement = new WebElement(id);
                    Node locator = webElementElement.getElementsByTagName(
                        "Locators"
                    ).item(0);
                    NodeList locators = locator.getChildNodes();

                    for (int j = 0; j < locators.getLength(); j++) {
                        Node loc = locators.item(j);
                        if (loc.getNodeType() == Node.ELEMENT_NODE) {
                            String tag = loc.getNodeName();
                            String value = loc.getTextContent();
                            webElement.addLocator(
                                this.locFactory.getLocator(tag, value)
                            );
                        }
                    }
                    page.addElement(webElement);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return page;
    }
}
