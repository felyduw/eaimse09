package pt.uc.dei.eai;

import javax.xml.ws.WebServiceRef;
import usercompositeorchestrator.CasaService1;

public class WebServiceAux {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/client/Login/localhost_9081/Login.wsdl")
    private CasaService1 service;

	public org.netbeans.xml.schema.userschema.User InvokeLogin(String username, String password) {
        service = new CasaService1();
        usercompositeorchestrator.WSLoginWrapperPortType port = service.getLogin();
        org.netbeans.xml.schema.userschema.UserInfo loginRequest = new org.netbeans.xml.schema.userschema.UserInfo();
        loginRequest.setUsername("csimoes");
        loginRequest.setPassword("sa");
        org.netbeans.xml.schema.userschema.User result = port.wsLoginWrapperOperation(loginRequest);
        return result;
    }

    /*
    @WebServiceRef(wsdlLocation = "http://localhost:8081/CalculatorApp/CalculatorWSService?wsdl")
    public orchestratorusercomposite.CasaService1 loginService;
     * */
	// METHODS
	/* USERS */
    /*
	public pt.uc.dei.eai.common.User InvokeLogin(String username, String password) {
        loginService = new orchestratorusercomposite.CasaService1();
        orchestratorusercomposite.WSLoginWrapperPortType port = loginService.getLogin();
        org.netbeans.xml.schema.userschema.User userOrg = port.wsLoginWrapperOperation(username, password);
        pt.uc.dei.eai.common.User user = null;
        if (userOrg != null) {
            user = new pt.uc.dei.eai.common.User();
            try {
                user.setAddress(userOrg.getAddress());
                user.setName(userOrg.getName());
                user.setId(userOrg.getId());
                user.setUsername(userOrg.getUsername());
                user.setEmail(userOrg.getEmail());
                user.setPassword(userOrg.getPassword());
            } catch (Exception exc) {
                //user = new pt.uc.dei.eai.common.User();
                user.setName(exc.getMessage());
            }
        }
        try {
            ((Closeable) port).close();
        } catch (IOException ex) {
            Logger.getLogger(WebServiceAux.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
	}
	
	public boolean InvokeRegisterUser(String username, String password, String address, String email) {
        orchestratorusercomposite.CasaService2 registerUserService = new orchestratorusercomposite.CasaService2();
        orchestratorusercomposite.WSRegisterUSerPortType port = registerUserService.getRegister();
		return port.wsRegisterUSerOperation(username, password, address, email);
	}
*/
    /* ORDERS */
	//SearchCamerasResponse == List<Camera>
	/*
	public List<Camera> InvokeSearchCameras(String search) {
        orchestratorordercomposite.CasaService2 casaService2 = new orchestratorordercomposite.CasaService2();
        orchestratorordercomposite.WSBPELSearchCamerasPortType port = casaService2.getSearchCameras();
		SearchCamerasResponse searchCamerasResponse = port.wsBPELSearchCamerasOperation(search);
		return TranslateSearchCamerasResponse2ListCameras(searchCamerasResponse);
	}
	*/
    /*
	public CameraInfo InvokeGetCameraInfo(Integer cameraId) {
        getCameraInfoService = new CasaService2();
        testordercomposite.WSBPELGetCameraInfoPortType port = getCameraInfoService.getGetCameraInfo();
        CameraSearch request = new CameraSearch();
        request.setModelId(cameraId.intValue());
        return port.wsBPELGetCameraInfoOperation(request);
	}
    */
    /*
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
     * */
	/* PURCHASES */
	//OrderResponse == List<Order>
	/*
	public OrderResponse InvokeListPurchases(String username) {
		return ListPurchases.getGetPurchases().wsBPELGetPurchasesOperation(username);
	}
	
	public OrderResponse InvokeGetPurchaseInfo(Integer orderId) {
		return GetPurchaseInfo.getGetPurchaseInfo().wsBPELGetPurchaseInfoOperation(orderId.intValue());
	}
	
	public boolean InvokeCheckout(User user, SearchCamerasResponse listCameras ) {
		return Checkout.getCheckout().wsBPELCheckoutOperation(user, listCameras);
	}
    */
}
