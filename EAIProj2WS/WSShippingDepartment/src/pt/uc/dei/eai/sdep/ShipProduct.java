package pt.uc.dei.eai.sdep;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.rpc.Call;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.ParameterMode;
import javax.xml.namespace.QName;


import com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe;

import pt.uc.dei.eai.common.Order;
import pt.uc.dei.eai.common.Utility;

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
            
            // TODO
        	// Invoke web service in LPCO
        	
            // Shipped new order
            Utility.writeLog("Order ID: " + shipOrder.getOrderId());
            
        } catch (Exception ex) {
            Utility.writeLog(ex.getMessage());
        }
    }
	
	public void invokeWSLPCO() {
		try {
			String wsdlURL = "http://127.0.0.1:8080/WSLPCO?wsdl";
			String namespace = "LPCOWebService";
			String serviceName = "shipped";
			QName serviceQN = new QName(namespace, serviceName);
			
			ServiceFactory serviceFactory = ServiceFactory.newInstance();
			Service service = serviceFactory.createService(new URL(wsdlURL), serviceQN);
			
		} catch(Exception ex) {
			
		}
	}
}
