package pt.uc.dei.eai.sdep;

import pt.uc.dei.eai.common.Utility;
import pt.uc.dei.eai.lpco.LPCOProxy;

public class TestWS {

	public boolean executeWS(Integer orderID) {
		boolean shipped = false;
		

		try {
			LPCOProxy lpProxy = new LPCOProxy();
			System.out.println(lpProxy.toString());
			lpProxy.shipped(orderID, "");
			
		} catch (Exception e) {
			Utility.writeLog(e.getMessage());
		}
		return shipped;
	}
}
