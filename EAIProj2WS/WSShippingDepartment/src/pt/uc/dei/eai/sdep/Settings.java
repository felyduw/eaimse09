package pt.uc.dei.eai.sdep;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jboss.system.server.ServerConfigLocator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Settings {

	private String settingsFilename = "wslpco_config.xml";
	private String SDwsdl = null;
	private String SDnamespace = null;
	private String SDserviceName = null;

	public String getSDwsdl() {
		if (SDwsdl == null || SDwsdl.isEmpty()) {
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
		return SDwsdl;
	}

	public void setSDwsdl(String swsdl) {
		SDwsdl = swsdl;
	}

	public String getSDnamespace() {
		if (SDnamespace == null || SDnamespace.isEmpty()) {
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
		return SDnamespace;
	}

	public void setSDnamespace(String snamespace) {
		SDnamespace = snamespace;
	}

	public String getSDserviceName() {
		if (SDserviceName == null || SDserviceName.isEmpty()) {
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
		return SDserviceName;
	}

	public void setSDserviceName(String sserviceName) {
		SDserviceName = sserviceName;
	}

	public Settings() {}

	private void readSettingsFile() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		
		Document doc = docBuilder.parse(new File(ServerConfigLocator.locate().getServerConfigURL() + 
				"/" + settingsFilename));
		
		// normalize text representation
		doc.getDocumentElement().normalize();
		setSDnamespace(getNodeTextContent(doc, "SDnamespace"));
		setSDserviceName(getNodeTextContent(doc, "SDserviceName"));
		setSDwsdl(getNodeTextContent(doc, "SDwsdl"));
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
