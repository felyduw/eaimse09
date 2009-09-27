package camerasummaryxml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/*
 * Class that is responsible for dealing with the XML processing
 *  of a XML camera search file.
 */
public class CameraProcessor {
	/**
	 * The XML document.
	 */
	private Document document = null;
	
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
