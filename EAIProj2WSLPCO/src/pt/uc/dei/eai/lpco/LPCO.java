package pt.uc.dei.eai.lpco;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class LPCO {
	@WebMethod
	public boolean shipped(String orderId) {
		
		return false;	
	}
}
