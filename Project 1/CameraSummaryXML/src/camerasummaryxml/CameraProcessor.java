package camerasummaryxml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.xpath.XPathExpression;

/*
 * Class that is responsible for dealing with the XML processing
 *  of a XML camera search file.
 */
public class CameraProcessor {
	/**
	 * The XML document.
	 */
	public org.w3c.dom.Document document = null;
	public String brandName = null;
	
	/**
	 * Starts the camera processor.
	 * 
	 * @param name the name of the XML file
	 * @throws Exception when an error is detected in the file 
	 */
	public CameraProcessor(String name) throws Exception{
		openXMLFile(name);
	}
	
	/**
	 * Opens XML file for processing.
	 * 
	 * @param name the name of the input file
	 */
	public void openXMLFile (String xmlFileName) throws Exception
    {
        DocumentBuilder docBuilder;
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setIgnoringElementContentWhitespace(true);
        try {
            docBuilder = docBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
        	throw new Exception("Wrong parser configuration: " + e.getMessage());
        }		
		
		File sourceFile = new File(xmlFileName);
		 try {
			// Build document
			document = docBuilder.parse(sourceFile);
        }
        catch (IOException e) {
        	throw new Exception("Error opening file - " + xmlFileName + ").");
        }
    }

	/**
	 * Obtains the list from a XML document using a query.
	 * 
	 * @param xPath the query to obtain the nodes
	 * @return the list of the query results
	 * @throws XPathExpressionException 
	 */
	@SuppressWarnings("unchecked")
	public NodeList getNodesFromXPath(String sXpath) throws XPathExpressionException {
		/*List nodeList = XPath.selectNodes(this.document, sXpath);
		return nodeList;*/
		
	    XPathFactory factory = XPathFactory.newInstance();
	    XPath xpath = factory.newXPath();
	    javax.xml.xpath.XPathExpression expr = xpath.compile(sXpath);

	    Object result = expr.evaluate(document, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;		
		return nodes;
	}
	
	/**
	 * Obtains a single node from a XML document using a query.
	 * 
	 * @param xPath the query to obtain the nodes
	 * @return the list of the query results
	 * @throws XPathExpressionException 
	 * @throws JDOMException  if an error
	 * @throws Exception 
	 */
	public Node getSingleNodeFromXPath(String sXpath) throws XPathExpressionException  {
	    XPathFactory factory = XPathFactory.newInstance();
	    XPath xpath = factory.newXPath();
	    javax.xml.xpath.XPathExpression expr = xpath.compile(sXpath);

	    Object result = expr.evaluate(document, XPathConstants.NODE);
	    Node node = (Node) result;		
		return node;
	}	
	
	/**
     * Write camera summary to XML output file
     * 
     * @param xmlFileName the name of the XML file to write
     * @param myDocument the content of the XML file
     * 
     * @throws IOException if there is an error when trying 
     * to write the XML output file
     */
    public void writeCameraSummary(String xmlFileName) throws IOException {
    	assert document != null;
    	
		/*XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		FileWriter writer = new FileWriter(xmlFileName);
	    outputter.output(document, writer);
	    writer.close();*/
    }
    
    
}
