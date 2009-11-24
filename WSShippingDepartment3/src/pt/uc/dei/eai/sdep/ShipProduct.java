package pt.uc.dei.eai.sdep;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import pt.uc.dei.eai.common.Order;
import pt.uc.dei.eai.common.Utility;
import pt.uc.dei.eai.lpco.LPCOProxy;
import pt.uc.dei.eai.lpco.LPCOService;

/**
 * Ship Product and call LPCO Web Service.
 */
public class ShipProduct extends Thread {
	
	//FIXME CALL PROCESS ORCHESTRATOR
	final static String wsdlLocation = "http://127.0.0.1:8080/WSLPCO?wsdl";
	static LPCOService LPCOWebService;
	
	Order shipOrder;
	
	/** Creates a new instance of ShipProduct */
	public ShipProduct(Order newOrder) {
		assert newOrder != null;
		
		shipOrder = newOrder;
	}
	
	public void run() {
        try {
        	// Order must not be null
            assert shipOrder.getOrderId() != null;
        	
            // Simulate number of days (3 to 7 seconds)
            int shippingDays = Utility.randomDays(); 
            Thread.sleep(shippingDays*1000);
            
            // Discover Shipping Date
            Calendar cal = Calendar.getInstance();
            shipOrder.getPurchaseDate();
            cal.add(Calendar.DATE, shippingDays);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String orderShipDate = sdf.format(cal.getTime());  
            
            invokeWSLPCO(shipOrder.getOrderId(), orderShipDate);
        	
            // Shipped new order
            Utility.writeLog("Order Shipped ID: " + shipOrder.getOrderId());
            
        } catch (Exception ex) {
            Utility.writeLog(ex.getMessage());
        }
    }
	
	public void invokeWSLPCO(Integer orderId, String shippedDates) {
	
		try {
			// Calling Web Service
			LPCOProxy lpProxy = new LPCOProxy();
			lpProxy.shipped(orderId, shippedDates);
		} catch(Exception ex) {
			 Utility.writeLog(ex.getMessage());
		}
	}
}
