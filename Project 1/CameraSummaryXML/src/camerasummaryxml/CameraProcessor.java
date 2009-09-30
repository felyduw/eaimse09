package camerasummaryxml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;
import org.w3c.dom.NodeList;

/*
 * Class that is responsible for dealing with the XML processing
 *  of a XML camera search file.
 */
public class CameraProcessor {
	/**
	 * The XML document.
	 */
	public Document document = null;
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
	public void openXMLFile (String name) throws Exception
    {
        SAXBuilder sb = new SAXBuilder();

        try {
            document = sb.build(new File(name));
        }
        catch (JDOMException e) {
            throw new Exception("Error opening file - " + name + ").");
        }
        catch (IOException e) {
        	throw new Exception("Error opening file - " + name + ").");
        }
    }

	/**
	 * Obtains the list from a XML document using a query.
	 * 
	 * @param xPath the query to obtain the nodes
	 * @return the list of the query results
	 * @throws JDOMException  if an error
	 */
	@SuppressWarnings("unchecked")
	public List getNodesFromXPath(String xpath) throws JDOMException {
		List nodeList = XPath.selectNodes(this.document,xpath);
		return nodeList;
	}
	
	/**
	 * Obtains a single node from a XML document using a query.
	 * 
	 * @param xPath the query to obtain the nodes
	 * @return the list of the query results
	 * @throws JDOMException  if an error
	 */
	public Object getSingleNodeFromXPath(String xpath) throws JDOMException {
		Object node = XPath.selectSingleNode(this.document,xpath);
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
    	
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		FileWriter writer = new FileWriter(xmlFileName);
	    outputter.output(document, writer);
	    writer.close();
    }
}
