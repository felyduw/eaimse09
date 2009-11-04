package pt.uc.dei.eai.sdep;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import pt.uc.dei.eai.common.Order;
import pt.uc.dei.eai.common.Utility;
import pt.uc.dei.eai.lpco.LPCO;
import pt.uc.dei.eai.lpco.LPCOService;

@WebService
public class ShippingDepartment {
	
	
	@WebMethod
	public boolean makeOrder(Order order) {
		Utility.writeLog("Order Received ID: " + order.getOrderId());
		
		ShipProduct shipProduct = new ShipProduct(order);
		
		// New Thread to handle the product shipping
		shipProduct.start();
	
		// Product handled
		return true;
	}
}
