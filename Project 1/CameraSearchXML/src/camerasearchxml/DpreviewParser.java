/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package camerasearchxml;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import project1.Camera;

/**
 * Class to parse pages from DPReview web site.
 * @author csimoes
 */
public class DpreviewParser {

	public static List<String> getCameraModels(String htmlPage, String brand) {
		List<String> modelUrls = new ArrayList<String>();
		Pattern myPattern = Pattern.compile("/reviews/specs/" + brand + "/[A-Za-z0-9._-]+",
						Pattern.CASE_INSENSITIVE |  Pattern.DOTALL | Pattern.MULTILINE);
		Matcher myMatcher = myPattern.matcher(htmlPage);
		while (myMatcher.find()) {
			modelUrls.add(myMatcher.group());
		}
		return modelUrls;
	}

	public static Camera getModelDetails(String htmlPage) {
		Camera modelDetails = new Camera();
		Pattern myPattern = Pattern.compile("Max resolution",
						Pattern.CASE_INSENSITIVE |  Pattern.DOTALL | Pattern.MULTILINE);
		Matcher myMatcher = myPattern.matcher(htmlPage);
		if (myMatcher.find()) {
			
		}
		return modelDetails;
	}
}
