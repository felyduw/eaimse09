/**
 * CameraSummary.java
 */
package camerasummaryxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.JDOMException;

import project1.Brand;
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
	 * Details of camera brands and its cameras.
	 */
	private static List<Brand> listBrands;	
	
	/**
	 * Creates a new Camera Summary application.
	 * @param args the XML files for obtaining the camera summary
	 */
    public static void main(final String[] args) {
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
			listBrands = new ArrayList<Brand>();
			
			// Initialize output file
			summaryDoc = new CameraProcessor(OUTPUT_FILE_SCHEMA);
			
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
     */
	public static void execCameraSummary() {
		// TODO Number of cameras that are present in the input files
		
	    // TODO Date of announcement of the most recent camera and corresponding model

	    // TODO Date of announcement of the oldest camera and corresponding model

	    // TODO Maximum resolution of a camera and corresponding model

		// TODO Minimum resolution of a camera and corresponding model

	    // TODO A list containing all model names of that manufacturer
	}
	
	/**
	 *  Number of cameras that are present in the input files.
	 * @throws JDOMException 
	 */
	public static void obtainAllBrandCameras() throws JDOMException {
		final String XPATH = "/Brand/Cameras/Camera/"; 
		CameraProcessor cameraAux = null;
		List cameras = new ArrayList();
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			
			cameras = cameraAux.getNodesFromXPath(XPATH);
			
			// Obtains the number of cameras of a specific brand
			listBrands.add cameras.size();
			
			
		}
	}
	
    /**
     * Date of announcement of the most recent camera and corresponding model.
     * @throws JDOMException 
     */
	public static void recentCameras() throws JDOMException {
		final String XPATH = "/"; 
		CameraProcessor cameraAux = null;
		List cameras = new ArrayList();
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			
			cameras = cameraAux.getNodesFromXPath(XPATH);
			
			
		}
	}
	
    /**
     * Date of announcement of the oldest camera and corresponding model.
     * @throws JDOMException 
     */
	public static void oldestCameras() throws JDOMException {
		final String XPATH = "/"; 
		CameraProcessor cameraAux = null;
		List cameras = new ArrayList();
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			
			cameras = cameraAux.getNodesFromXPath(XPATH);
			
			
		}
	}
	
    /**
     *  Maximum resolution of a camera and corresponding model.
     * @throws JDOMException 
     */
	public static void maxResolutionCameras() throws JDOMException {
		final String XPATH = "/"; 
		CameraProcessor cameraAux = null;
		List cameras = new ArrayList();
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			
			cameras = cameraAux.getNodesFromXPath(XPATH);
			
			
		}
	}
	
	/**
	 * Minimum resolution of a camera and corresponding model.
	 * @throws JDOMException 
	 */
	public static void minResolutionCameras() throws JDOMException {
		final String XPATH = "/"; 
		CameraProcessor cameraAux = null;
		List cameras = new ArrayList();
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			
			cameras = cameraAux.getNodesFromXPath(XPATH);
			
			
		}
	}
	
    /**
     * A list containing all model names of that manufacturer.
     * @throws JDOMException 
     */
	public static void obtainAllModels() throws JDOMException {
		final String XPATH = "/"; 
		CameraProcessor cameraAux = null;
		List cameras = new ArrayList();
		
		for (String filename : fileNames) {
			// Obtains the camera file name to process
			cameraAux = inputFiles.get(filename);
			
			cameras = cameraAux.getNodesFromXPath(XPATH);
			
			
		}
	}
}
