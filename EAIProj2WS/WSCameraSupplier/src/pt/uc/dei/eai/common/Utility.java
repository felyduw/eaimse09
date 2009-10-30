package pt.uc.dei.eai.common;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Random;

public class Utility {
	static final String LOG_LOCATION = "LogWSCameraSupplier.txt";
	
	/** Logger */
    public static void writeLog(String logMsg) {
        try {
            Calendar cal = Calendar.getInstance();
            String date = cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH)+1) + 
            	"/" + cal.get(Calendar.DAY_OF_MONTH);
            String hour = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
            
            File file = new File(LOG_LOCATION);
   
         	synchronized(file) {
                if(!file.isFile()) {
                    FileWriter log = new FileWriter(file);
                    log.write("WS Received: " + date + " at " + hour + " - " + logMsg + "\n");
                    log.close();
                }
                else {
                    FileWriter fw = new FileWriter(file, true);
                    fw.write("WS Received: " + date + " at " + hour + " - " + logMsg + "\n");
                    fw.close();
                }
            }
        } catch (Exception ex) {
            // Error while writing in log file
        	System.out.println("Error" + ex.toString());
        }
    }
    
    /** 
     * Obtains a random price.
     * @return the price of the camera
     */
    public static Float randomPrice() {
    	final int MIN_PRICE = 1;
    	final int MAX_PRICE = 10;
    	final float PRICE_RANGE = 250;
    	int price;
    	
    	Random rdm = new Random();
    	
    	price = rdm.nextInt(MAX_PRICE);
    	
    	if (price < MIN_PRICE) {
    		price = MIN_PRICE;
    	}
    	
    	return new Float(price * PRICE_RANGE);
    }
    
}
