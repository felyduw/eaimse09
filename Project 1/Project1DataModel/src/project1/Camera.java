/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.jdom.*;

/**
 *
 * @author csimoes
 */
public class Camera {

	public String Model;
	public String Description;
	public Date Date;
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

	/**
	 * Serializes the Camera to a xml DOM structure.
	 * @return	Xml with the Camera.
	 */
	public Element getDomDoc() {
		Element  cameraElem = new Element("Camera");
		// add Model element
		addNonEmptyContent(cameraElem, "Model", Model);
		// add Description element
		addNonEmptyCdata(cameraElem, "Description", Description);
		// add Date element
		if (Date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			addNonEmptyContent(cameraElem, "Date", formatter.format(Date));
		}
		// add ImageRatio element
		addNonEmptyContent(cameraElem, "ImageRatio", ImageRatio);
		// add EffectivePixels element
		addNonEmptyContent(cameraElem, "EffectivePixels", EffectivePixels);
		// add SensorSize element
		addNonEmptyContent(cameraElem, "SensorSize", SensorSize);
		// add MinShutterSpeed element
		addNonEmptyContent(cameraElem, "MinShutterSpeed", MinShutterSpeed);
		// add MaxShutterSpeed element
		addNonEmptyContent(cameraElem, "MaxShutterSpeed", MaxShutterSpeed);
		// add DepthReviewUrl element
		addNonEmptyContent(cameraElem, "DepthReviewUrl", DepthReviewUrl);
		// add PictureUrl element
		addNonEmptyContent(cameraElem, "PictureUrl", PictureUrl);
		// add IsoRatings element
		if (IsoRatings != null) {
			Element isoRatingsElem = new Element("IsoRatings");
			Iterator<String> iteratorIsoRatings = IsoRatings.iterator();
			while (iteratorIsoRatings.hasNext()) {
				String isoRating = iteratorIsoRatings.next();
				isoRatingsElem.addContent(createSimpleStringElement("IsoRating", isoRating));
			}
			cameraElem.addContent(isoRatingsElem);
		}
		// add MaxResolutions element
		if (MaxResolutions != null) {
			cameraElem.addContent(createResolutionsElement("MaxResolutions", "MaxResolution", MaxResolutions));
		}
		// add LowerResolutions element
		if (LowerResolutions != null) {
			cameraElem.addContent(createResolutionsElement("LowerResolutions", "LowerResolution", LowerResolutions));
		}
		return cameraElem;
	}

	private Element createSimpleStringElement(String name, String content) {
		Element elem = new Element(name);
		elem.addContent(content);
		return elem;
	}

	private Element createCdataElement(String name, String content) {
		Element elem = new Element(name);
		CDATA cdataElem = new CDATA(content);
		elem.addContent(cdataElem);
		return elem;
	}

	private Element createResolutionsElement(String nameRootElem,
					String nameEachElem, List<Resolution> resolutionsList) {
		Iterator<Resolution> iterator = resolutionsList.iterator();
		Element rootElem = new Element(nameRootElem);
		while (iterator.hasNext()) {
			Resolution resolution = iterator.next();
			Element singleResElem = new Element(nameEachElem);
			singleResElem.addContent(createSimpleStringElement("Horiz", (new Integer(resolution.Horizontal)).toString()));
			singleResElem.addContent(createSimpleStringElement("Vert", (new Integer(resolution.Vertical)).toString()));
			singleResElem.addContent(createSimpleStringElement("NumberPixels", (new Long(resolution.NumberPixels)).toString()));
			rootElem.addContent(singleResElem);
		}
		return rootElem;
	}

	private void addNonEmptyContent(Element elem, String nodeName, String text) {
		if (text != null && !text.isEmpty()) {
			elem.addContent(createSimpleStringElement(nodeName, text));
		}
	}

	private void addNonEmptyCdata(Element elem, String nodeName, String text) {
		if (text != null && !text.isEmpty()) {
			elem.addContent(createCdataElement(nodeName, text));
		}
	}
	
}
