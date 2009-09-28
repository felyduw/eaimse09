/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package camerasearchxml;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.*;
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
							replaceableBrandGenericString, brandsGenericUrl);
			try {
				String brandPage = WebSucker.getPage(brandUrl);
				// get links to each model
				List<String> modelsUrls = DpreviewParser.getCameraModels(brandPage);
			} catch (IOException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
				ShowErrorMessage("Communication error: " + ex.getMessage());
			}
			// for each model, get page with details

				String modelUrl = null; // APAGAR!!!
				String modelPage = WebSucker.getPage(modelUrl);
				// parse page to extract details
				Camera modelDetails = DpreviewParser.getModelDetails(modelPage);
				// create DOM node with model details
				Document xmlDom = modelDetails.getDomDoc();

    // Write it to disk
    XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
    FileWriter writer = new FileWriter("publications.xml");
    outputter.output(myDocument, writer);
    writer.close();

				// save DOM node to file
				
			// return
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
