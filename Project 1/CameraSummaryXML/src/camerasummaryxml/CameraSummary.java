/**
 * CameraSummary.java
 */
package camerasummaryxml;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import project1.BrandSummary;
import project1.CameraSummaryDetails;
/**
 * Class that starts the Camera Summary application.
 */
public class CameraSummary {
	/**
	 * The files where to execute the search.
	 */
	private static Map<String, CameraProcessor> inputFiles;
	/**
	 * The name of the input files.
	 */
	private static Set<String> fileNames;
	/**
	 * The name of the brands.
	 */
	private static Set<String> brandNames;	
	/**
	 * Details of camera brands and its cameras.
	 * <name, list of camera through brand>
	 */
	private static Map<String, BrandSummary> listBrands;
	/**
	 * Final XML output document.
	 */
	private static Document finalDocument = null;
	/**
	 * XPATH for the necessary XML queries.
	 */
	private final static String XPATH_BRAND_NAME = "//Name";
	private final static String XPATH_CAMERA_PER_BRAND = "/Brand/Cameras/Camera"; 
	private final static String XPATH_CAMERA_DATE = 
		"/Brand/Cameras/Camera/Date";
	private final static String XPATH_CAMERA_MAX_RESOLUTION = 
		"/Brand/Cameras/Camera/MaxResolutions/MaxResolution/NumberPixels";
	private final static String XPATH_CAMERA_MIN_RESOLUTION = 
		"/Brand/Cameras/Camera/LowerResolutions/LowerResolution/NumberPixels"; 
	private final static String XPATH_CAMERA_ALL_MODELS = "//Model";

	/**
	 * Creates a new Camera Summary application.
	 * @param args the XML files for obtaining the camera summary
	 */
    public static void main(final String[] args) {
    	String brandName = null;
    	
    	// Read arguments from command line
 		if (args.length == 0) {
			System.out.println("Arguments: <filename 1> ... <filename n>");
			return;
		}

 		// Initialization
		inputFiles = new HashMap<String, CameraProcessor>();
		fileNames = new HashSet<String>();
		
		try {
			// For each new file, initialize it
	 		for (int i = 0; i < args.length; i++) {
	 			fileNames.add(args[i]);
	 			inputFiles.put(args[i], new CameraProcessor(args[i]));
	 		}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return;
		}
		
		try {		
			// Initialize List
			listBrands = new HashMap<String, BrandSummary>();
			brandNames = new HashSet<String>();
			
			for (String filename : fileNames) {
				// Obtains the brand name from the file to process
				brandName = inputFiles.get(filename).getSingleNodeFromXPath(XPATH_BRAND_NAME).getTextContent();
				inputFiles.get(filename).brandName = brandName;
				
				// Unique brand names (ensure that we don't have 2 brands with the same name)
				if (!brandNames.contains(brandName)) {
					brandNames.add(brandName);
					listBrands.put(brandName, new BrandSummary());
				}
			}
			
			// Obtain all the correct cameras
	 		execCameraSummary();
	 		
	 		// Constructs the XML content
	 		prepareOutputFile();
	 		
	    	// Write output XML file
	 		writeXml();
 		} catch (Exception ex) {
 			System.out.println("Error writing output file - " + ex.toString());
			return;
 		}
    }

    /**
     * Executes the XML file processing.
     * @throws XPathExpressionException 
     * @throws JDOMException 
     */
	public static void execCameraSummary() throws XPathExpressionException  {
		// Number of cameras that are present in the input files
		obtainAllBrandCameras();
		
	    // Date of announcement of the most recent camera and corresponding model
		recentCameras();
		
	    // Date of announcement of the oldest camera and corresponding model
		oldestCameras();
		
	    // Maximum resolution of a camera and corresponding model
		maxResolutionCameras();
		
		// Minimum resolution of a camera and corresponding model
		minResolutionCameras();
		
	    // List containing all model names of that manufacturer
		obtainAllModels();
	}
	
	/**
	 * Number of cameras that are present in the input files.
	 * @throws XPathExpressionException 
	 */
	public static void obtainAllBrandCameras() throws XPathExpressionException  {
		CameraProcessor cameraAux = null;
		NodeList cameras;
		String brandName;
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			cameras = cameraAux.getNodesFromXPath(XPATH_CAMERA_PER_BRAND);
			brandName = cameraAux.brandName;
			
			// Obtains the number of cameras of a specific brand
			listBrands.get(brandName).name = brandName;
			listBrands.get(brandName).numberCameras = cameras.getLength();
		}
	}
	
    /**
     * Date of announcement of the most recent camera and corresponding model.
     * @throws XPathExpressionException 
     * @throws XPathExpressionException 
     */
	public static void recentCameras() throws XPathExpressionException {
		CameraProcessor cameraAux = null;
		NodeList cameras;
		String brandName;
		List<CameraSummaryDetails> recentCameras;
		int iMaxDate = 0;
		int iAuxDate = 0;
		String sMaxDate = null;
		String xPathMaxDate = "/Brand/Cameras/Camera[Date=\"";
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			cameras = cameraAux.getNodesFromXPath(XPATH_CAMERA_DATE);
			brandName = cameraAux.brandName;
			
			// For all cameras discover what is the max resolution
			for(int i = 0; i < cameras.getLength(); i++) {
			  Node childNode = cameras.item(i);
			  
			  iAuxDate = Integer.parseInt(childNode.getTextContent().replace("-",""));
			  if(iAuxDate > iMaxDate) {
				  sMaxDate = childNode.getTextContent();
				  iMaxDate = iAuxDate;
			  }
			}
			
			// XPATH of the maximum value
			xPathMaxDate = xPathMaxDate +  sMaxDate + "\"]";
			cameras = cameraAux.getNodesFromXPath(xPathMaxDate);

			// Obtains the cameras
			recentCameras = listBrands.get(brandName).recentCameras;
			
			// For all cameras found for a specific brand
			for(int i = 0; i < cameras.getLength(); i++){
			  Node childNode = cameras.item(i);
			  recentCameras.add(convertNodeToCamera(childNode));
			}
		}
	}
	
    /**
     * Date of announcement of the oldest camera and corresponding model.
     * @throws XPathExpressionException 
     */
	public static void oldestCameras() throws XPathExpressionException {
		CameraProcessor cameraAux = null;
		NodeList cameras;
		String brandName;
		List<CameraSummaryDetails> oldestCameras;
		int iMinDate = Integer.MAX_VALUE;
		int iAuxDate = 0;
		String sMinDate = null;
		String xPathMinDate = "/Brand/Cameras/Camera[Date=\"";
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			cameras = cameraAux.getNodesFromXPath(XPATH_CAMERA_DATE);
			brandName = cameraAux.brandName;
			
			// For all cameras discover what is the max resolution
			for(int i = 0; i < cameras.getLength(); i++) {
			  Node childNode = cameras.item(i);
			  
			  iAuxDate = Integer.parseInt(childNode.getTextContent().replace("-",""));
			  if(iMinDate > iAuxDate) {
				  sMinDate = childNode.getTextContent();
				  iMinDate = iAuxDate;
			  }
			}
			
			// XPATH of the maximum value
			xPathMinDate = xPathMinDate +  sMinDate + "\"]";
			cameras = cameraAux.getNodesFromXPath(xPathMinDate);

			// Obtains the cameras
			oldestCameras = listBrands.get(brandName).oldestCameras;
			
			// For all cameras found for a specific brand
			for(int i = 0; i < cameras.getLength(); i++){
			  Node childNode = cameras.item(i);
			  oldestCameras.add(convertNodeToCamera(childNode));
			}
		}
	}
	
    /**
     *  Maximum resolution of a camera and corresponding model.
     * @throws XPathExpressionException 
     */
	public static void maxResolutionCameras() throws XPathExpressionException {
		CameraProcessor cameraAux = null;
		NodeList cameras;
		String brandName;
		List<CameraSummaryDetails> maxResCameras;
		String xPathMaxRes = "/Brand/Cameras/Camera[MaxResolutions/MaxResolution/NumberPixels=\"";
		int maxResolution = 0;
		int auxResolution = 0;
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			cameras = cameraAux.getNodesFromXPath(XPATH_CAMERA_MAX_RESOLUTION);
			brandName = cameraAux.brandName;
						
			// For all cameras discover what is the max resolution
			for(int i = 0; i < cameras.getLength(); i++) {
			  Node childNode = cameras.item(i);
			  
			  auxResolution = Integer.parseInt(childNode.getTextContent());
			  if(auxResolution > maxResolution) {
				  maxResolution = auxResolution;
			  }
			}

			// XPATH of the maximum value
			xPathMaxRes = xPathMaxRes +  maxResolution + "\"]";
			cameras = cameraAux.getNodesFromXPath(xPathMaxRes);

			// Obtains the cameras
			maxResCameras = listBrands.get(brandName).maxResCameras;
			
			// For all cameras found for a specific brand
			for(int i = 0; i < cameras.getLength(); i++) {
			  Node childNode = cameras.item(i);
			  maxResCameras.add(convertNodeToCameraWithResolution(childNode, maxResolution, true));
			}
		}
	}
	
	/**
	 * Minimum resolution of a camera and corresponding model.
	 * @throws XPathExpressionException 
	 */
	public static void minResolutionCameras() throws XPathExpressionException {
		CameraProcessor cameraAux = null;
		NodeList cameras;
		String brandName;
		List<CameraSummaryDetails> minResCameras;
		String xPathMinRes = "/Brand/Cameras/Camera[LowerResolutions/LowerResolution/NumberPixels=\"";
		int minResolution = Integer.MAX_VALUE;
		int auxResolution = 0;
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			cameras = cameraAux.getNodesFromXPath(XPATH_CAMERA_MIN_RESOLUTION);
			brandName = cameraAux.brandName;
						
			// For all cameras discover what is the max resolution
			for(int i = 0; i < cameras.getLength(); i++) {
			  Node childNode = cameras.item(i);
			  
			  auxResolution = Integer.parseInt(childNode.getTextContent());
			  if(minResolution > auxResolution) {
				  minResolution = auxResolution;
			  }
			}

			// XPATH of the minimum value
			xPathMinRes = xPathMinRes +  minResolution + "\"]";
			cameras = cameraAux.getNodesFromXPath(xPathMinRes);

			// Obtains the cameras
			minResCameras = listBrands.get(brandName).minResCameras;
			
			// For all cameras found for a specific brand
			for(int i = 0; i < cameras.getLength(); i++) {
			  Node childNode = cameras.item(i);
			  minResCameras.add(convertNodeToCameraWithResolution(childNode, minResolution, false));
			}
		}
	}
	
    /**
     * A list containing all model names of that manufacturer.
     * @throws XPathExpressionException 
     */
	public static void obtainAllModels() throws XPathExpressionException {
		CameraProcessor cameraAux = null;
		NodeList cameras;
		String brandName;
		List<String> allCameras;
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			cameras = cameraAux.getNodesFromXPath(XPATH_CAMERA_ALL_MODELS);
			brandName = cameraAux.brandName;
			
			// Obtains the number of cameras of a specific brand
			allCameras = listBrands.get(brandName).allCameras;
			
			// For all cameras found for a specific brand
			for(int i = 0; i < cameras.getLength(); i++){
			  Node childNode = cameras.item(i);
			  allCameras.add(childNode.getTextContent());
			}
		}
	}
	
	/**
     * Converts a node into a camera summary details object.
	 * @throws XPathExpressionException 
     */
	public static CameraSummaryDetails convertNodeToCamera(Node cameraNode) throws XPathExpressionException {
		assert cameraNode != null;
		
		final String DATE_NODE_MODEL = "//Model";
		final String DATE_NODE_DATE = "//Date";
	
		CameraSummaryDetails newCamera = new CameraSummaryDetails();				
		newCamera.date = getStringFromXPath(cameraNode, DATE_NODE_DATE);
		newCamera.model = getStringFromXPath(cameraNode, DATE_NODE_MODEL);

		return newCamera;
	}	
	
	/**
     * Converts a node into a camera summary details object.
	 * @throws XPathExpressionException 
     */
	public static CameraSummaryDetails convertNodeToCameraWithResolution(Node cameraNode, 
			int resolution, boolean blnMaxResolution) throws XPathExpressionException {
		assert cameraNode != null;
		
		final String NODE_MODEL = "//Model";
		final String NODE_DATE = "//Date";
		
		String xPathMaxResHoriz = 
			"//Horiz[parent::MaxResolution/NumberPixels=\"" + resolution + "\"]";
		String xPathMaxResVert = 
			"//Vert[parent::MaxResolution/NumberPixels=\"" + resolution + "\"]";
		String xPathMinResHoriz = 
			"//Horiz[parent::LowerResolution/NumberPixels=\"" + resolution + "\"]";
		String xPathMinResVert = 
			"//Vert[parent::LowerResolution/NumberPixels=\"" + resolution + "\"]";		
		
		CameraSummaryDetails newCamera = new CameraSummaryDetails();
		
		newCamera.date = getStringFromXPath(cameraNode, NODE_DATE);
		newCamera.model = getStringFromXPath(cameraNode, NODE_MODEL);
		
		if(blnMaxResolution) {
			newCamera.maxResolution = Integer.toString(resolution);
			newCamera.maxResolutionVertical = getStringFromXPath(cameraNode, xPathMaxResVert);
			newCamera.maxResolutionHorizontal = getStringFromXPath(cameraNode, xPathMaxResHoriz);
		} else {
			newCamera.minResolution = Integer.toString(resolution);
			newCamera.minResolutionVertical = getStringFromXPath(cameraNode, xPathMinResVert);
			newCamera.minResolutionHorizontal = getStringFromXPath(cameraNode, xPathMinResHoriz);
		}
		
		return newCamera;
	}		
		
	/**
	 * Obtains a value node from a XML node using a query.
	 * 
	 * @param xPath the query to obtain the nodes
	 * @return the value
	 * @throws XPathExpressionException 
	 */
	public static String getStringFromXPath(Node node, String sXpath) throws XPathExpressionException  {
	    XPathFactory factory = XPathFactory.newInstance();
	    XPath xpath = factory.newXPath();
	    XPathExpression expr = xpath.compile(sXpath);
	    
	    Node auxNode = node.cloneNode(true);	    
	    String value = (String) expr.evaluate(auxNode, XPathConstants.STRING);

		return value;
	}
	
	/**
	 * Constructs the XML content. 
	 * @throws XPathExpressionException 
	 */
	public static void prepareOutputFile() throws XPathExpressionException {
		// Constructs XML File
		Element summaryElem = new Element("Summary");
		Element brandsElem = new Element("Brands");
		summaryElem.addContent(brandsElem);
		
		// Iterate for all brands
		for (String brandName : brandNames) {
			// Obtains the brand summary for each brand
			BrandSummary brandSummary = listBrands.get(brandName);
	
			Element brandElem = brandSummary.getDomDoc();
			
			brandsElem.addContent(brandElem);
		}	
		
		// Create the final document
		finalDocument = new Document(summaryElem);
	}
	
	/**
	 * Writes XML to a file.
	 * @param xmlDom XML to be written to the file.
	 * @throws IOException
	 */
	private static void writeXml() throws IOException {
		assert finalDocument != null;
		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hhmm");
		FileWriter writer = new FileWriter("Summary_" + formatter.format(new Date()) + ".xml");
		outputter.output(finalDocument, writer);
		writer.close();
	}	
}
