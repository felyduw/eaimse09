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
	 * Creates a new Camera Summary application.
	 * @param args the XML files for obtaining the camera summary
	 */
    public static void main(final String[] args) {
    	// Read arguments from command line
 		if (args.length != 0) {
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
		
 		execCameraSummary();
    }

    /**
     * Executes the XML file processing.
     */
	public static void execCameraSummary() {

    	// TODO answer all questions

    	// TODO write output to XML file
	}
	

	
    // TODO Number of cameras that are present in the input files

    // TODO Date of announcement of the most recent camera
    // and corresponding model

    // TODO Date of announcement of the oldest camera and corresponding model

    // TODO Maximum resolution of a camera and corresponding model

	// TODO Minimum resolution of a camera and corresponding model

    // TODO A list containing all model names of that manufacturer
}
