package pt.uc.dei.eai.sdep;

import javax.jws.WebMethod;
import javax.jws.WebService;

import pt.uc.dei.eai.common.Order;

@WebService
public class ShippingDepartment {

	@WebMethod
	public boolean makeOrder(Order order) {
		
		ShipProduct shipProduct = new ShipProduct(order);
		
		// New Thread to handle the product shipping
		shipProduct.start();
		
		// Product handled
		return true;
	}
}
