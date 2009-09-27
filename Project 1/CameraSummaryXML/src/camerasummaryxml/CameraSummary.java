/**
 * CameraSummary.java
 */
package camerasummaryxml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
	 */
	public static void obtainCameras() {
		
	}
	
    /**
     * Date of announcement of the most recent camera and corresponding model.
     */
	public static void recentCameras() {
		
	}
	
    /**
     * Date of announcement of the oldest camera and corresponding model.
     */
	public static void oldestCameras() {
		
	}
	
    /**
     *  Maximum resolution of a camera and corresponding model.
     */
	public static void maxResolutionCameras() {
		
	}
	
	/**
	 * Minimum resolution of a camera and corresponding model.
	 */
	public static void minResolutionCameras() {
		
	}
	
    /**
     * A list containing all model names of that manufacturer.
     */
	public static void obtainAllModels() {
		
	}
}
