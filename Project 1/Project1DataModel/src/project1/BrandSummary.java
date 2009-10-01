package project1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

/**
 * Class to obtain the Summary for each brand.
 */
public class BrandSummary {
	public String name;
	public int numberCameras;
	public List<String> allCameras;
	public List<CameraSummaryDetails> recentCameras; 
	public List<CameraSummaryDetails> oldestCameras; 
	public List<CameraSummaryDetails> maxResCameras; 
	public List<CameraSummaryDetails> minResCameras;
	
	/**
	 * Initialize all variables.
	 */
	public BrandSummary() {
		name = null;
		numberCameras = 0;
		allCameras = new ArrayList<String>();
		recentCameras = new ArrayList<CameraSummaryDetails>(); 
		oldestCameras = new ArrayList<CameraSummaryDetails>(); 
		maxResCameras = new ArrayList<CameraSummaryDetails>();
		minResCameras = new ArrayList<CameraSummaryDetails>();
	}
	
	/**
	 * Serializes the Brands to a XML DOM structure.
	 * @return XML with the Brand.
	 */
	public Element getDomDoc() {
		Element brandElem = new Element("Brand");
		
		// Add Model element
		addNonEmptyContent(brandElem, "Name", name);
		
		// Add number of cameras
		addNonEmptyContent(brandElem, "NumberCameras", Integer.toString(numberCameras));
		
		// Add Announcements
		Element announcElem = new Element("Announcements");
		brandElem.addContent(announcElem);

		// Add Recent Announcements
		announcElem.addContent(createAnnoucemmentsElement("RecentAnnouncements", 
				"RecentAnnouncement", recentCameras));		
		
		// Add Oldest Announcements
		announcElem.addContent(createAnnoucemmentsElement("OldestAnnouncements", 
				"OldestAnnouncement", oldestCameras));		
		
		// Add Resolutions
		Element resolutionElem = new Element("Resolutions");
		brandElem.addContent(resolutionElem);
		
		// Add Maximum Resolution 
		resolutionElem.addContent(createResolutionsElement(true, "MaxResolutions", 
				"MaxResolution", maxResCameras));		
		
		// Add Minimum Resolution 
		resolutionElem.addContent(createResolutionsElement(false, "MinResolutions", 
				"MinResolution", minResCameras));				
		
		// Add all camera model names 
		brandElem.addContent(createModelsElement("Models", 
				"Model", allCameras));			
		
		return brandElem;
	}

	private Element createSimpleStringElement(String name, String content) {
		Element elem = new Element(name);
		elem.addContent(content);
		return elem;
	}

	private Element createModelsElement(String nameRootElem,
			String nameEachElem, List<String> listModels) {

		Iterator<String> iterator = listModels.iterator();
		Element rootElem = new Element(nameRootElem);
		while (iterator.hasNext()) {
			rootElem.addContent(createSimpleStringElement(nameEachElem, (String) iterator.next()));
		}
		return rootElem;
	}	
	
	private Element createAnnoucemmentsElement(String nameRootElem,
					String nameEachElem, List<CameraSummaryDetails> listAnnouncements) {
		
		Iterator<CameraSummaryDetails> iterator = listAnnouncements.iterator();
		Element rootElem = new Element(nameRootElem);
		while (iterator.hasNext()) {
			CameraSummaryDetails announcement = iterator.next();
			Element singleResElem = new Element(nameEachElem);
			singleResElem.addContent(createSimpleStringElement("Date", announcement.date));
			singleResElem.addContent(createSimpleStringElement("Model", announcement.model));
			rootElem.addContent(singleResElem);
		}
		return rootElem;
	}

	private Element createResolutionsElement(boolean blnMaxRes, String nameRootElem,
			String nameEachElem, List<CameraSummaryDetails> listResolutions) {
		Iterator<CameraSummaryDetails> iterator = listResolutions.iterator();
		Element rootElem = new Element(nameRootElem);
		while (iterator.hasNext()) {
			CameraSummaryDetails resolution = iterator.next();
			Element singleResElem = new Element(nameEachElem);
			
			if(blnMaxRes) {
				singleResElem.addContent(createSimpleStringElement("Resolution", resolution.maxResolution));
				singleResElem.addContent(createSimpleStringElement("Model", resolution.model));				
			} else {
				singleResElem.addContent(createSimpleStringElement("Resolution", resolution.minResolution));
				singleResElem.addContent(createSimpleStringElement("Model", resolution.model));	
			}
			
			rootElem.addContent(singleResElem);
		}
		return rootElem;
	}
	
	private void addNonEmptyContent(Element elem, String nodeName, String text) {
		if (text != null && !text.isEmpty()) {
			elem.addContent(createSimpleStringElement(nodeName, text));
		}
	}	
}

