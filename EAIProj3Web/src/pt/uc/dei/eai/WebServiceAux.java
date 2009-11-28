package pt.uc.dei.eai;

import javax.xml.ws.WebServiceRef;

import org.netbeans.xml.schema.cameraresponse.SearchCamerasResponse;
import org.netbeans.xml.schema.userschema.User;

public class WebServiceAux {
	
	/* USERS */
	@WebServiceRef(wsdlLocation = WSDL.LoginURL)
	static orchestratorusercomposite.CasaService1 Login;
	
	@WebServiceRef(wsdlLocation = WSDL.RegisterUserURL)
	static orchestratorusercomposite.CasaService2 RegisterUser;
	
	/* ORDERS */
	@WebServiceRef(wsdlLocation = WSDL.SearchCamerasURL)
	static orchestratorordercomposite.CasaService2 SearchCameras;
	
	@WebServiceRef(wsdlLocation = WSDL.GetCameraInfoURL)
	static orchestratorordercomposite.CasaService1 GetCameraInfo;
	
	
	/* PURCHASES */
	
	@WebServiceRef(wsdlLocation = WSDL.ListPurchasesURL)
	static orchestratorpurchasecomposite.CasaService4 ListPurchases;
	
	@WebServiceRef(wsdlLocation = WSDL.GetPurchaseInfoURL)
	static orchestratorpurchasecomposite.CasaService2 GetPurchaseInfo;
	
	@WebServiceRef(wsdlLocation = WSDL.CheckoutURL)
	static orchestratorpurchasecomposite.CasaService1 Checkout;
	
	// METHODS
	/* USERS */
	public User InvokeLogin(String username, String password) {
		return Login.getLogin().wsLoginWrapperOperation(username, password);
	}
	
	public boolean InvokeRegisterUser(String username, String password, String address, String email) {
		return RegisterUser.getRegister().wsRegisterUSerOperation(username, password, address, email);
	}
	
	/* ORDERS */
	
	public SearchCamerasResponse InvokeSearchCameras(String search) {
		return SearchCameras.getSearchCameras().wsBPELSearchCamerasOperation(search);
	}
	
	//GetCameraInfo
}
