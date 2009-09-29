/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project1;

import java.util.List;
import org.jdom.*;

/**
 *
 * @author csimoes
 */
public class Camera {

	public String Model;
	public String Description;
	public String Date;
	public List <Resolution> MaxResolutions;
	public List <Resolution> LowerResolutions;
	public String ImageRatio;
	public String EffectivePixels;
	public String SensorSize;
	public List <String> IsoRatings;
	public String MinShutterSpeed;
	public String MaxShutterSpeed;
	public String DepthReviewUrl;
	public String PictureUrl;

	public Element getDomDoc() {
    Element  pubElement = new Element("publications");
    Document myDocument = new Document(pubElement);

    Element autor = new Element("author");
    autor.addContent("Henrique Madeira");
    pubElement.addContent(autor);

		return pubElement;
	}
	


}
