package pt.uc.dei.eai.common;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Random;

public class Utility {
	static final String LOG_LOCATION = "LogWSLPCO.txt";
	
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
     * Obtains a random number between 3 and 7.
     * @return the number of days between 3 and 7
     */
    public static int randomDays() {
    	final int MIN_DAYS = 3;
    	final int MAX_DAYS = 8;
    	int numDays;
    	
    	Random rdm = new Random();
    	
    	numDays = rdm.nextInt(MAX_DAYS);
    	
    	if (numDays < MIN_DAYS) {
    		numDays = MIN_DAYS;
    	}
    	
    	return numDays;
    }
}
