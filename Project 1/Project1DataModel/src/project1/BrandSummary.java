package project1;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to obtain the Summary for each brand.
 */
public class BrandSummary {
	public String name;
	public int numberCameras;
	public List<String> allCameras;
	public List<CameraSummaryDetails> recentCameras; 
	public List<CameraSummaryDetails> oldestCameras; 
	public List<CameraSummaryDetails> maxResCameras; 
	public List<CameraSummaryDetails> minResCameras;
	
	/**
	 * Initialize all variables.
	 */
	public BrandSummary() {
		name = null;
		numberCameras = 0;
		allCameras = new ArrayList<String>();
		recentCameras = new ArrayList<CameraSummaryDetails>(); 
		oldestCameras = new ArrayList<CameraSummaryDetails>(); 
		maxResCameras = new ArrayList<CameraSummaryDetails>();
		minResCameras = new ArrayList<CameraSummaryDetails>();
	}
}

