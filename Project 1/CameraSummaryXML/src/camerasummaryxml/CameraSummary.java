/**
 * CameraSummary.java
 */
package camerasummaryxml;

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
	 * Name of the output file.
	 */
	private final static String OUTPUT_NAME = "summary.xml";
	/**
	 * Content of the output file.
	 */
	private final static String OUTPUT_FILE_SCHEMA = "camera_summary.xml";	
	/**
	 * Output XML output file with the summary details.
	 */
	private static CameraProcessor summaryDoc;
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
	 * XPATH for the necessary XML queries.
	 */
	private final static String XPATH_BRAND_NAME = "//Name";
	private final static String XPATH_CAMERA_PER_BRAND = "/Brand/Cameras/Camera"; 
	private final static String XPATH_CAMERA_RECENT_CAMERAS = 
		" /Brand/Cameras/Camera[not(preceding-sibling::Date > Date or following-sibling::Date > Date)]"; 
	private final static String XPATH_CAMERA_OLDEST_CAMERAS = 
		"/Brand/Cameras/Camera[not(preceding-sibling::Date < Date or following-sibling::Date < Date)]";
	private final static String XPATH_CAMERA_MAX_RESOLUTION = 
		"/Brand/Cameras/Camera[MaxResolutions/MaxResolution/NumberPixels = max(//MaxResolutions/MaxResolution/NumberPixels)]";
	private final static String XPATH_CAMERA_MIN_RESOLUTION = 
		"/Brand/Cameras/Camera[LowerResolutions/LowerResolution/NumberPixels = min(//LowerResolutions/LowerResolution/NumberPixels)]"; 
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
			// Initialize output file
			summaryDoc = new CameraProcessor(OUTPUT_FILE_SCHEMA);
			
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
			
	 		execCameraSummary();
	 		
	    	// Write output XML file
 			summaryDoc.writeCameraSummary(OUTPUT_NAME);
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
		
	    // TODO Maximum resolution of a camera and corresponding model

		// TODO Minimum resolution of a camera and corresponding model

	    // TODO A list containing all model names of that manufacturer
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
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			cameras = cameraAux.getNodesFromXPath(XPATH_CAMERA_RECENT_CAMERAS);
			brandName = cameraAux.brandName;
			
			// Obtains the number of cameras of a specific brand
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
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			cameras = cameraAux.getNodesFromXPath(XPATH_CAMERA_OLDEST_CAMERAS);
			brandName = cameraAux.brandName;
			
			// Obtains the number of cameras of a specific brand
			oldestCameras = listBrands.get(brandName).oldestCameras;
			
			// For all cameras found for a specific brand
			for(int i = 0; i < cameras.getLength(); i++) {
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
		
	}
	
	/**
	 * Minimum resolution of a camera and corresponding model.
	 * @throws XPathExpressionException 
	 */
	public static void minResolutionCameras() throws XPathExpressionException {
	
	}
	
    /**
     * A list containing all model names of that manufacturer.
     * @throws XPathExpressionException 
     */
	public static void obtainAllModels() throws XPathExpressionException {
		
	}
	
	/**
     * A list containing all model names of that manufacturer. 
	 * @throws XPathExpressionException 
     */
	public static CameraSummaryDetails convertNodeToCamera(Node cameraNode) throws XPathExpressionException {
		final String DATE_NODE_MODEL = "//Model";
		final String DATE_NODE_DATE = "//Date";
		final String DATE_NODE_MAX_RESOLUTION = "max(//MaxResolutions/MaxResolution/NumberPixels)]";
		final String DATE_NODE_MIN_RESOLUTION = "min(//LowerResolutions/LowerResolution/NumberPixels)]";
		final String DATE_NODE_MAX_HORIZ_RESOLUTION = "max(//MaxResolutions/MaxResolution/NumberPixels)]";
		final String DATE_NODE_MAX_VERT_RESOLUTION = "max(//MaxResolutions/MaxResolution/NumberPixels)]";
		final String DATE_NODE_MIN_HORIZ_RESOLUTION = "min(//LowerResolutions/LowerResolution/NumberPixels)]";		
		final String DATE_NODE_MIN_VERT_RESOLUTION = "min(//LowerResolutions/LowerResolution/NumberPixels)]";


		assert cameraNode != null;
		
		CameraSummaryDetails newCamera = new CameraSummaryDetails();
		
		for(int i = 0; i < cameraNode.getChildNodes().getLength(); i++){
			Node childNode = cameraNode.getChildNodes().item(i);
		
			newCamera.date = getStringFromXPath(childNode, DATE_NODE_DATE);
			newCamera.model = getStringFromXPath(childNode, DATE_NODE_MODEL);
			newCamera.minResolution = getStringFromXPath(childNode, DATE_NODE_MIN_RESOLUTION);
			newCamera.maxResolution = getStringFromXPath(childNode, DATE_NODE_MAX_RESOLUTION);
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

	    String value = ((Node) expr.evaluate(node, XPathConstants.NODE)).getTextContent();
		return value;
	}	
	
}
