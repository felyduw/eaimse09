package pt.uc.dei.eai.lpco;

import javax.jws.WebMethod;
import javax.jws.WebService;

import pt.uc.dei.eai.common.SendMail;
import pt.uc.dei.eai.common.Utility;

@WebService
public class LPCO {
	public static String SUBJECT = "Low Price Cameras Online - Order Confirmation";
	public static String FROM = "lpco@lpco.eai.dei.uc.pt";
	public static String MESSAGE = "The order has been shipped.";
	
	@WebMethod
	public boolean shipped(String orderId, String shippedDates) {
		String orderEmail = null;
		String bodyMessage = null;
		
		//TODO
		// Obtain data from order (check database orders)
		
		//TODO
		// Update order status to shipped 
		
		//TODO
		// Obtain and add recipient e-mail address
		orderEmail = "";
		
		bodyMessage = MESSAGE + "Order ID: " + orderId;
		
		// Send e-mail
		try {
			SendMail.postMail(orderEmail, SUBJECT, bodyMessage, FROM);
			
			// Order updated and user notified
			Utility.writeLog("Order ID: " + orderId);
		} catch(Exception ex) {
			Utility.writeLog(ex.getMessage());
		}
		
		// Everything OK
		return true;	
	}
}
