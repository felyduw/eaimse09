package pt.uc.dei.eai.cs;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jboss.system.server.ServerConfigLocator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pt.uc.dei.eai.common.Camera;
import pt.uc.dei.eai.common.CameraProcessor;
import pt.uc.dei.eai.common.Utility;

@WebService
public class CameraSupplier {
	private final static String XML_CATALOG_LOCATION = "camera_catalog.xml";
	private final static String XPATH_MODEL_START = "//Model[contains(node(), '";
	private final static String XPATH_MODEL_END =	"')]";
	
	@WebMethod
	public List<Camera> getCameras(String name) {
		List<Camera> resultCameras = new ArrayList<Camera>();
		
		try {	
			CameraProcessor cameraProc = new CameraProcessor(ServerConfigLocator.locate().getServerDataDir().getPath() 
					+ "/" + XML_CATALOG_LOCATION);
			NodeList cameraNodes = cameraProc.getNodesFromXPath(XPATH_MODEL_START + name + XPATH_MODEL_END);
			
			// For all cameras found
			for(int i = 0; i < cameraNodes.getLength(); i++){
			  Node childNode = cameraNodes.item(i);
			  resultCameras.add(convertNodeToCamera(childNode));
			}
			
			Utility.writeLog("Cameras searched: " + name + ". #Camera: " + cameraNodes.getLength());
		} catch (Exception ex) {
			Utility.writeLog(ex.getMessage());
		}
		
		// Search cameras output
		return resultCameras;
	}
	
	/**
     * Converts a node into a camera object.
	 * @throws XPathExpressionException 
     */
	public static Camera convertNodeToCamera(Node cameraNode) throws XPathExpressionException {
		assert cameraNode != null;
		
		final String NODE_MODEL = "//Model";
		final String NODE_DATE = "//Date";
	
		Camera newCamera = new Camera();
		newCamera.setModel(getStringFromXPath(cameraNode.getParentNode(), NODE_MODEL));
		newCamera.setDate(getStringFromXPath(cameraNode.getParentNode(), NODE_DATE));
		
		return newCamera;
	}
	
	/**
	 * Obtains a value node from a XML node using a query.
	 * 
	 * @param xPath the query to obtain the nodes
	 * @return the value
	 * @throws XPathExpressionException 
	 */
	public static String getStringFromXPath(Node node, String sXpath) throws XPathExpressionException  {
	    XPathFactory factory = XPathFactory.newInstance();
	    XPath xpath = factory.newXPath();
	    XPathExpression expr = xpath.compile(sXpath);
	    
	    Node auxNode = node.cloneNode(true);	    
	    String value = (String) expr.evaluate(auxNode, XPathConstants.STRING);

	    return value;
	}	
}
