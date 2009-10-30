package pt.uc.dei.eai.data;

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

public class Settings {

	private String settingsFilename = "config.xml";
	private String CSwsdl = null;
	private String CSnamespace = null;
	private String CSserviceName = null;

	public String getCSwsdl() {
		if (CSwsdl == null || CSwsdl.isEmpty()) {
			try {
				readSettingsFile();
			} catch (ParserConfigurationException e) {
				
				e.printStackTrace();
			} catch (SAXException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return CSwsdl;
	}

	public void setCSwsdl(String swsdl) {
		CSwsdl = swsdl;
	}

	public String getCSnamespace() {
		if (CSnamespace == null || CSnamespace.isEmpty()) {
			try {
				readSettingsFile();
			} catch (ParserConfigurationException e) {
				
				e.printStackTrace();
			} catch (SAXException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return CSnamespace;
	}

	public void setCSnamespace(String snamespace) {
		CSnamespace = snamespace;
	}

	public String getCSserviceName() {
		if (CSserviceName == null || CSserviceName.isEmpty()) {
			try {
				readSettingsFile();
			} catch (ParserConfigurationException e) {
				
				e.printStackTrace();
			} catch (SAXException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return CSserviceName;
	}

	public void setCSserviceName(String sserviceName) {
		CSserviceName = sserviceName;
	}

	public Settings() {}

	private void readSettingsFile() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File(settingsFilename));
		// normalize text representation
		doc.getDocumentElement().normalize();
		setCSnamespace(getNodeTextContent(doc, "CSnamespace"));
		setCSserviceName(getNodeTextContent(doc, "CSserviceName"));
		setCSwsdl(getNodeTextContent(doc, "CSwsdl"));
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
