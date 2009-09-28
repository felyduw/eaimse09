package camerasearchxml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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
	public String getListOfBrandsUrl() {
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

	public void Settings() {

	}

	private void readSettingsFile() {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(settingsFilename));
			// normalize text representation
			doc.getDocumentElement().normalize();
			NodeList listOfBrandsUrlNodeList = doc.getElementsByTagName("ListOfBrandsUrl");
			Node listOfBrandsUrlNode = listOfBrandsUrlNodeList.item(0);
      if(listOfBrandsUrlNode.getNodeType() == Node.ELEMENT_NODE) {
				Element listOfBrandsUrlElement = (Element)listOfBrandsUrlNode;
        NodeList textFNList = listOfBrandsUrlElement.getChildNodes();
				setListOfBrandsUrl(((Node)textFNList.item(0)).getNodeValue().trim());
			}
  	} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());
		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	//System.exit (0);
	}
}
