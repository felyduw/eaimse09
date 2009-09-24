package camerasummaryxml;

import java.io.File;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/*
 * Class that is responsible for dealing with the XML processing
 *  of a XML camera search file.
 */
public class CameraProcessor {
	/**
	 * The XML document.
	 */
	private Document doc = null;
	
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
            doc = sb.build(new File(name));
        }
        catch (JDOMException e) {
            throw new Exception("Error opening file - " + name + ").");
        }
        catch (IOException e) {
        	throw new Exception("Error opening file - " + name + ").");
        }
    }
}
