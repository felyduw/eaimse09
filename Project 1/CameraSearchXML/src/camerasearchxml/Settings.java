package camerasearchxml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Object to read file with settings.
 * @author csimoes
 */
public class Settings {

	private String settingsFilename = "camerasearchSettings.xml";
	private String listOfBrandsUrl = null;

	/**
	 * Get the value of listOfBrandsUrl
	 * @return the value of listOfBrandsUrl
	 */
	public String getListOfBrandsUrl() throws ParserConfigurationException, SAXException, IOException {
		if (listOfBrandsUrl == null || listOfBrandsUrl.isEmpty()) {
			readSettingsFile();
		}
		return listOfBrandsUrl;
	}

	/**
	 * Set the value of listOfBrandsUrl
	 * @param listOfBrandsUrl new value of listOfBrandsUrl
	 */
	public void setListOfBrandsUrl(String listOfBrandsUrl) {
		this.listOfBrandsUrl = listOfBrandsUrl;
	}
	private String siteUrl = null;

	/**
	 * Get the value of siteUrl
	 * @return the value of siteUrl
	 */
	public String getSiteUrl() throws ParserConfigurationException, SAXException, IOException {
		if (siteUrl == null || siteUrl.isEmpty()) {
			readSettingsFile();
		}
		return siteUrl;
	}

	/**
	 * Set the value of siteUrl
	 * @param listOfBrandsUrl new value of siteUrl
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public void Settings() {
	}

	private void readSettingsFile() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File(settingsFilename));
		// normalize text representation
		doc.getDocumentElement().normalize();
		setSiteUrl(getNodeTextContent(doc, "SiteUrl"));
		setListOfBrandsUrl(getNodeTextContent(doc, "ListOfBrandsUrl"));
	}

	private String getNodeTextContent(Document doc, String nodeName) {
		NodeList nodeList = doc.getElementsByTagName(nodeName);
		if (nodeList != null && nodeList.getLength() > 0) {
			Node node = nodeList.item(0);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList textFNList = element.getChildNodes();
				return (((Node) textFNList.item(0)).getNodeValue().trim());
			}
		}
		return null;
	}
}
