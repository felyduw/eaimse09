/**
 * CameraSummary.java
 */
package camerasummaryxml;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.NodeList;

import project1.Brand;
import project1.Camera;
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
	private static Map<String, Brand> listBrands;
	
	/**
	 * XPATH for the necessary XML queries.
	 */
	private final static String XPATH_BRAND_NAME = "Brand/Name";
	private final static String XPATH_CAMERA_PER_BRAND = "/Brand/Cameras/Camera"; 
	private final static String XPATH_CAMERA_RECENT_CAMERAS = "/Brand/Cameras/Camera"; 
	
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
			listBrands = new HashMap<String, Brand>();
			brandNames = new HashSet<String>();
			
			for (String filename : fileNames) {
				// Obtains the brand name from the file to process
				brandName = inputFiles.get(filename).getSingleNodeFromXPath(XPATH_BRAND_NAME).getNodeValue();
				inputFiles.get(filename).brandName = brandName;
				
				// Unique brand names (ensure that we don't have 2 brands with the same name)
				if (!brandNames.contains(brandName)) {
					brandNames.add(brandName);
					listBrands.put(brandName, new Brand());
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
		
	    // TODO Date of announcement of the most recent camera and corresponding model

	    // TODO Date of announcement of the oldest camera and corresponding model

	    // TODO Maximum resolution of a camera and corresponding model

		// TODO Minimum resolution of a camera and corresponding model

	    // TODO A list containing all model names of that manufacturer
	}
	
	/**
	 * Number of cameras that are present in the input files.
	 * @throws XPathExpressionException 
	 */
	@SuppressWarnings("unchecked")
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
     */
	@SuppressWarnings("unchecked")
	public static void recentCameras()  {
	
	}
	
    /**
     * Date of announcement of the oldest camera and corresponding model.
     * @throws XPathExpressionException 
     */
	@SuppressWarnings("unchecked")
	public static void oldestCameras()  {
	
	}
	
    /**
     *  Maximum resolution of a camera and corresponding model.
     * @throws XPathExpressionException 
     */
	@SuppressWarnings("unchecked")
	public static void maxResolutionCameras()  {
		
	}
	
	/**
	 * Minimum resolution of a camera and corresponding model.
	 * @throws XPathExpressionException 
	 */
	@SuppressWarnings("unchecked")
	public static void minResolutionCameras() {
	
	}
	
    /**
     * A list containing all model names of that manufacturer.
     * @throws XPathExpressionException 
     */
	@SuppressWarnings("unchecked")
	public static void obtainAllModels()  {
		
	}
}
