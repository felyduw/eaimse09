/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project1;

import org.jdom.*;

/**
 *
 * @author csimoes
 */
public class Camera {

	public Element getDomDoc() {
    Element  pubElement = new Element("publications");
    Document myDocument = new Document(pubElement);

    Element autor = new Element("author");
    autor.addContent("Henrique Madeira");
    pubElement.addContent(autor);

		return pubElement;
	}
	


}
