/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package camerasearchxml;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import project1.Camera;
import project1.Resolution;

/**
 * Class to parse pages from DPReview web site.
 * @author csimoes
 */
public class DpreviewParser {

	public static List<CameraModel> getCameraModels(String htmlPage, String brand) {
		List<CameraModel> modelUrls = new ArrayList<CameraModel>();
		Pattern myPattern = Pattern.compile("(/reviews/specs/" + brand + "/[A-Za-z0-9._-]+)\"><b[^>]*>(.*?)</b></a>[<br>[&nbsp]*]*<font color=#bbbbbb>(.*?)</font>",
						Pattern.CASE_INSENSITIVE |  Pattern.DOTALL | Pattern.MULTILINE);
		Matcher myMatcher = myPattern.matcher(htmlPage);
		while (myMatcher.find()) {
			if (myMatcher.groupCount() >= 3) {
				CameraModel newModel = new CameraModel();
				newModel.setUrl(myMatcher.group(1));
				newModel.setDescription(myMatcher.group(3));
				modelUrls.add(newModel);
			}
		}
		return modelUrls;
	}

	public static Camera getModelDetails(String htmlPage) {
		Camera modelDetails = new Camera();
		// get Model
		modelDetails.Model = getSpecificDetail(htmlPage, "tdlabelproduct\">([^<]+)");
		// get Description
		// get Date
		modelDetails.Date = getDate(htmlPage, "Announced (\\d+-[A-Za-z]{3}-\\d+)");
		// get MaxResolutions
		modelDetails.MaxResolutions = getResolutions(htmlPage, "Max resolution[&nbsp;]+</td>\\s*<td class=\"tdcontentsm\">([^<]+)");
		// get LowerResolutions
		modelDetails.LowerResolutions = getResolutions(htmlPage, "Low resolution[&nbsp;]+</td>\\s*<td class=\"tdcontentsm\">([^<]+)");
		// get ImageRatio
		modelDetails.ImageRatio = getSpecificDetail(htmlPage, "Image ratio w:h[&nbsp;]+</td>\\s*<td class=\"tdcontentsm\">(\\d+:\\d+)");
		// get EffectivePixels
		modelDetails.EffectivePixels = getSpecificDetail(htmlPage, "Effective pixels[&nbsp;]+</td>\\s*<td class=\"tdcontentsm\">([^<]+)");
		// get SensorSize
		modelDetails.SensorSize = getSpecificDetail(htmlPage, "Sensor size[&nbsp;]+</td>\\s*<td class=\"tdcontentsm\">([^<]+)");
		// get IsoRatings
		modelDetails.IsoRatings = getStringList(htmlPage, "Iso rating[&nbsp;]+</td>\\s*<td class=\"tdcontentsm\">([^<]+)");
		// get MinShutterSpeed
		modelDetails.MinShutterSpeed = getSpecificDetail(htmlPage, "Min shutter[&nbsp;]+</td>\\s*<td class=\"tdcontentsm\">([^<]+)");
		// get MaxShutterSpeed
		modelDetails.MaxShutterSpeed = getSpecificDetail(htmlPage, "Max shutter[&nbsp;]+</td>\\s*<td class=\"tdcontentsm\">([^<]+)");
		// get DepthReviewUrl
		modelDetails.DepthReviewUrl = getSpecificDetail(htmlPage, "Image[&nbsp;]+</td><td class=\"tdlabelproduct\"><a href=\"([^\"]+)");
		// get PictureUrl
		modelDetails.PictureUrl = getSpecificDetail(htmlPage, "Image[&nbsp;]+</td><td class=\"tdlabelproduct\"><a href=\"/[A-Za-z0-9]+/[A-Za-z0-9]+/\"><img vspace=\"\\d*\" border=\"\\d*\" src=\"([^\"]+)");

		return modelDetails;
	}

	public static String getSpecificDetail(String htmlPage, String regex) {
		Pattern myPattern = Pattern.compile(regex,
						Pattern.CASE_INSENSITIVE |  Pattern.DOTALL | Pattern.MULTILINE);
		Matcher myMatcher = myPattern.matcher(htmlPage);
		if (myMatcher.find() && myMatcher.groupCount() >= 1) {
			return myMatcher.group(1);
		}
		return null;
	}

	public static Date getDate(String htmlPage, String regex) {
		Pattern myPattern = Pattern.compile(regex,
						Pattern.CASE_INSENSITIVE |  Pattern.DOTALL | Pattern.MULTILINE);
		Matcher myMatcher = myPattern.matcher(htmlPage);
		if (myMatcher.find() && myMatcher.groupCount() >= 1) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy", new Locale("en"));
			try {
				return formatter.parse(myMatcher.group(1));
			} catch (ParseException ex) {
				Logger.getLogger(DpreviewParser.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	public static List<Resolution> getResolutions(String htmlPage, String regex) {
		Pattern myPattern = Pattern.compile(regex,
						Pattern.CASE_INSENSITIVE |  Pattern.DOTALL | Pattern.MULTILINE);
		Matcher myMatcher = myPattern.matcher(htmlPage);
		List<Resolution> resList = null;
		if (myMatcher.find() && myMatcher.groupCount() >= 1) {
			String resolutionsRaw = myMatcher.group(1);
			myPattern = Pattern.compile("(\\d+)\\s*x\\s*(\\d+)\\s*,*\\s*",
							Pattern.CASE_INSENSITIVE |  Pattern.DOTALL | Pattern.MULTILINE);
			myMatcher = myPattern.matcher(resolutionsRaw);
			resList = new ArrayList<Resolution>();
			while (myMatcher.find() && myMatcher.groupCount() >= 2) {
				for (int i = 1; i <= myMatcher.groupCount(); i+=2) {
					Resolution res = new Resolution();
					try {
						res.Horizontal = Integer.parseInt(myMatcher.group(i));
						res.Vertical = Integer.parseInt(myMatcher.group(i+1));
						res.NumberPixels = res.Horizontal * res.Vertical;
						resList.add(res);
					} catch (NumberFormatException exc) {

					}
				}
			}
		}
		return resList;
	}

	public static List<String> getStringList(String htmlPage, String regex) {
		Pattern myPattern = Pattern.compile(regex,
						Pattern.CASE_INSENSITIVE |  Pattern.DOTALL | Pattern.MULTILINE);
		Matcher myMatcher = myPattern.matcher(htmlPage);
		List<String> resList = null;
		if (myMatcher.find() && myMatcher.groupCount() >= 1) {
			String listRaw = myMatcher.group(1);
			myPattern = Pattern.compile("[^,]+",
							Pattern.CASE_INSENSITIVE |  Pattern.DOTALL | Pattern.MULTILINE);
			myMatcher = myPattern.matcher(listRaw);
			resList = new ArrayList<String>();
			while (myMatcher.find()) {
				resList.add(myMatcher.group());
			}
		}
		return resList;
	}

}
