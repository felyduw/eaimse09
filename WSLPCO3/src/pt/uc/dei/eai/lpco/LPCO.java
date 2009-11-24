package pt.uc.dei.eai.lpco;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class LPCO {
	public static String SUBJECT = "Low Price Cameras Online - Order Confirmation";
	public static String FROM = "psaraiva@dei.uc.pt";
	public static String MESSAGE = "The order has been shipped.";
	
	@WebMethod
	public boolean shipped(Integer orderId, String shippedDates) {
		String orderEmail = null;
		String bodyMessage = null;

		//FIXME Remove bean call
		/*LPCOBeanRemote lpco = null;
		try {
			InitialContext ctx = new InitialContext();
			lpco = (LPCOBeanRemote)ctx.lookup("EAIProj2/LPCOBean/remote");
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		}
		
		assert lpco != null;
		
		// Obtain data from order (check database orders)
		Order order = lpco.getOrder(orderId);
		
		// Update order status to shipped
		order.setOrderStatus(OrderStatus.SHIPPED);
		lpco.updateOrder(order);
		
		// Obtain and add recipient e-mail address
		orderEmail = order.getEmailAddress();

Utility.writeLog("username: " + order.getUsername());		
Utility.writeLog("e-mail: " + orderEmail);		
		
		bodyMessage = MESSAGE + "Order ID: " + orderId;
		
		// Send e-mail
		try {
			SendMail.postMail(orderEmail, SUBJECT, bodyMessage, FROM);
			
			// Order updated and user notified
			Utility.writeLog("Order ID: " + orderId);
		} catch(Exception ex) {
			Utility.writeLog("shipped: " + ex.getMessage());
		}
		*/
		// Everything OK
		return true;	
	}
}
