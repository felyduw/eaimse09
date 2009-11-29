package pt.uc.dei.eai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import org.netbeans.xml.schema.cameraresponse.SearchCamerasResponse;
import org.netbeans.xml.schema.orderresponse.OrderResponse;
import org.netbeans.xml.schema.userschema.User;

import pt.uc.dei.eai.common.Camera;

public class WebServiceAux {
	
	/* USERS */
	@WebServiceRef(wsdlLocation = WSDL.LoginURL)
	static orchestratorusercomposite.CasaService1 Login;
	
	@WebServiceRef(wsdlLocation = WSDL.RegisterUserURL)
	static orchestratorusercomposite.CasaService2 RegisterUser;
	
	/* ORDERS */
	@WebServiceRef(wsdlLocation = WSDL.SearchCamerasURL)
	static orchestratorordercomposite.CasaService2 SearchCameras;
	
	@WebServiceRef(wsdlLocation = "http://localhost:9080/GetCamera?WSDL")
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
	//SearchCamerasResponse == List<Camera>
	
	public List<Camera> InvokeSearchCameras(String search) {
		SearchCamerasResponse searchCamerasResponse = SearchCameras.getSearchCameras().wsBPELSearchCamerasOperation(search);
		return TranslateSearchCamerasResponse2ListCameras(searchCamerasResponse);
	}
	
	public Camera InvokeGetCameraInfo(Integer cameraId) {
		orchestratorordercomposite.WSBPELGetCameraInfoPortType port = GetCameraInfo.getGetCamera();
		SearchCamerasResponse searchCamerasResponse = port.wsBPELGetCameraInfoOperation(cameraId.intValue());
		List<Camera> listCameras = TranslateSearchCamerasResponse2ListCameras(searchCamerasResponse);
		if (listCameras.size() > 0) {
			return listCameras.get(0);
		}
		return null;
	}
	
	private List<Camera> TranslateSearchCamerasResponse2ListCameras(SearchCamerasResponse searchCamerasResponse) {
		List<Serializable> rawListCameras = searchCamerasResponse.getIdAndModelAndPrice();
		List<Camera> listCameras = new ArrayList<Camera>();
		if (rawListCameras != null) {
			for (int i = 0; i < rawListCameras.size(); i++) {
				Camera newCamera = (Camera)rawListCameras.get(i);
				listCameras.add(newCamera);
			}
		}
		return listCameras;
	}
	/* PURCHASES */
	//OrderResponse == List<Order>
	
	public OrderResponse InvokeListPurchases(String username) {
		return ListPurchases.getGetPurchases().wsBPELGetPurchasesOperation(username);
	}
	
	public OrderResponse InvokeGetPurchaseInfo(Integer orderId) {
		return GetPurchaseInfo.getGetPurchaseInfo().wsBPELGetPurchaseInfoOperation(orderId.intValue());
	}
	
	public boolean InvokeCheckout(User user, SearchCamerasResponse listCameras ) {
		return Checkout.getCheckout().wsBPELCheckoutOperation(user, listCameras);
	}
}
