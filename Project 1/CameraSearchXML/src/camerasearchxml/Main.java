/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package camerasearchxml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.*;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import project1.Camera;

/**
 *
 * @author csimoes
 */
public class Main {

	private static final String replaceableBrandGenericString = "#BRAND#";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			// get parameters
			if (args.length != 1) {
				ShowErrorMessage("Wrong number of arguments!");
				return;
			}
			// get settings
			Settings settings = new Settings();
			String brandsGenericUrl = settings.getListOfBrandsUrl();
			// get page with links to the brands
			String brandUrl = brandsGenericUrl.replaceFirst(
							replaceableBrandGenericString, args[0]);
			String brandPage = WebSucker.getPage(brandUrl);
			// get links to each model
			List<CameraModel> modelsUrls = DpreviewParser.getCameraModels(brandPage, args[0]);
			Element brandElement = new Element("Brand");
			// for each model, get page with details
			Iterator<CameraModel> iterator = modelsUrls.iterator();
			while (iterator.hasNext()) {
				CameraModel cameraModel = iterator.next();
				System.out.println(cameraModel.getUrl());
				String modelPage = WebSucker.getPage(settings.getSiteUrl() + cameraModel.getUrl());
				// parse page to extract details
				Camera modelDetails = DpreviewParser.getModelDetails(modelPage);
				modelDetails.Description = cameraModel.getDescription();
				modelDetails.DepthReviewUrl = settings.getSiteUrl() + "/" + modelDetails.DepthReviewUrl;
				// create DOM node with model details and add to xml
				Element xmlNewModel = modelDetails.getDomDoc();
				brandElement.addContent(xmlNewModel);
			}
			Document xmlDom = new Document(brandElement);
			// save DOM node to file
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			FileWriter writer = new FileWriter(args[0] + ".xml");
			outputter.output(xmlDom, writer);
			writer.close();
			// return
		} catch (IOException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			ShowErrorMessage("Communication error: " + ex.getMessage());
		} catch (Exception exc) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exc);
			ShowErrorMessage("Unknown internal error: " + exc.getMessage());
		}
	}

	private static void ShowHelpMessage() {
		System.out.println("usage: camerasearchxml.exe <brand>");
	}

	private static void ShowErrorMessage(String errorMessage) {
		System.out.println(errorMessage);
		ShowHelpMessage();
	}
}
