package pt.uc.dei.eai.sdep;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;

import pt.uc.dei.eai.common.Order;
import pt.uc.dei.eai.common.Utility;
import pt.uc.dei.eai.lpco.LPCO;
import pt.uc.dei.eai.lpco.LPCOService;

public class ShipProduct extends Thread {
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
            Utility.writeLog("Order ID: " + shipOrder.getOrderId());
            
        } catch (Exception ex) {
            Utility.writeLog(ex.getMessage());
        }
    }
	
	public void invokeWSLPCO(Integer orderId, String shippedDates) {
		try {
			// Calling Web Service
			Settings setts = new Settings();
			String wsdlURL = setts.getSDwsdl();
			String namespace = setts.getSDnamespace();
			String serviceName = setts.getSDserviceName();
			QName serviceQN = new QName(namespace, serviceName);

			ServiceFactory serviceFactory = ServiceFactory.newInstance();

			LPCOService service = (LPCOService) serviceFactory
					.createService(new URL(wsdlURL), serviceQN);

			LPCO lpco = service.getLPCOPort();
			lpco.shipped(orderId, shippedDates);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
