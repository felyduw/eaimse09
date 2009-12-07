/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package camerasearchxml;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.jdom.*;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import project1.Camera;

/**
 * Main class of the application.
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
				showErrorMessage("Wrong number of arguments!");
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
			Element rootElement = initializeXml(args[0]);
			// for each model, get page with details
			Element camerasElem = new Element("Cameras");
			Iterator<CameraModel> iterator = modelsUrls.iterator();
			while (iterator.hasNext()) {
				CameraModel cameraModel = iterator.next();
				System.out.println(cameraModel.getUrl());
				String modelPage = WebSucker.getPage(settings.getSiteUrl() + cameraModel.getUrl());
				// parse page to extract details
				Camera modelDetails = DpreviewParser.getModelDetails(modelPage);
				modelDetails.Description = cameraModel.getDescription();
				if (modelDetails.DepthReviewUrl != null && !modelDetails.DepthReviewUrl.isEmpty()) {
					modelDetails.DepthReviewUrl = settings.getSiteUrl() + modelDetails.DepthReviewUrl;
				}
				// create DOM node with model details and add to xml
				Element xmlNewModel = modelDetails.getDomDoc();
				camerasElem.addContent(xmlNewModel);
			}
			rootElement.addContent(camerasElem);
			Document xmlDom = new Document(rootElement);
			// save DOM node to file
			writeXml(args[0], xmlDom);
			// return
		} catch (IOException ex) {
			//Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			showErrorMessage("IO error: " + ex.getMessage());
		} catch (Exception exc) {
			//Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exc);
			showErrorMessage("Unknown internal error: " + exc.getMessage());
		}
	}

	/**
	 * Prints the help message.
	 */
	private static void showHelpMessage() {
		System.out.println("usage: camerasearchxml.exe <brand>");
	}

	/**
	 * Prints an error message and the help message.
	 * @param errorMessage	Error message to print.
	 */
	private static void showErrorMessage(String errorMessage) {
		System.out.println(errorMessage);
		showHelpMessage();
	}

	/**
	 * Initializes the top DOM structures.
	 * @param brandName
	 * @return
	 */
	private static Element initializeXml(String brandName) {
		Element brandElement = new Element("Brand");
		Element nameElement = new Element("Name");
		nameElement.addContent(brandName);
		brandElement.addContent(nameElement);
		return brandElement;
	}

	/**
	 * Writes xml to a file.
	 * @param brand		Prefix of the filename.
	 * @param xmlDom	Xml to be written to the file.
	 * @throws java.io.IOException
	 */
	private static void writeXml(String brand, Document xmlDom) throws IOException {
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String filename = brand + "_" + formatter.format(new Date()) + ".xml";
		FileWriter writer = new FileWriter(filename);
		outputter.output(xmlDom, writer);
		writer.close();
		System.out.println("File written: " + filename);
	}

	/*
	public static void main(String[] args) {
		String[] arr = new String[] {
			"Agfa", "Canon", "Casio", "Contax", "Epson",
		  "FujiFilm", "HP", "Kodak", "Konica_Minolta", "Kyocera",
		  "Leica", "Nikon", "Olympus", "Panasonic", "Pentax",
			"Ricoh", "Samsumg", "Sanyo", "Sigma", "Sony",
			"Toshiba"};
		for (int i = 0; i < arr.length; i++) {
			String[] arg = new String[] { arr[i] };
			main1(arg);
		}
	}
	*/

}
