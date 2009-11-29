package pt.uc.dei.eai;

public class WSDL {
	
	private static final String port = "9080";
	
	/* User Management */
	public static final String LoginURL = "http://localhost:" + port + "/Login?wsdl";
	public static final String RegisterUserURL = "http://localhost:" + port + "/Register?wsdl";
	
	/* Order Management */
	public static final String SearchCamerasURL = "http://localhost:" + port + "/SearchCameras?wsdl";
	public static final String GetCameraInfoURL = "http://localhost:" + port + "/GetCamera?wsdl";
	
	
	/* Purchase Management */
	public static final String ListPurchasesURL = "http://localhost:" + port + "/GetPurchases?wsdl";
	public static final String GetPurchaseInfoURL = "http://localhost:" + port + "/GetPurchaseInfo?wsdl";
	//public static String OrderShippedURL = "http://localhost:" + port + "/Shipped?wsdl";
	public static final String CheckoutURL = "http://localhost:" + port + "/Checkout?wsdl";
}
