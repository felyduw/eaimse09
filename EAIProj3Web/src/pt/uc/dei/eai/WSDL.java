package pt.uc.dei.eai;

public class WSDL {
	
	private static String port = "9081";
	
	/* User Management */
	public static String LoginURL = "http://localhost:${HttpDefaultPort}/Login?wsdl";
	public static String RegisterUserURL = "http://localhost:${HttpDefaultPort}/Register?wsdl";
	
	/* Order Management */
	public static String SearchCamerasURL = "http://localhost:${HttpDefaultPort}/SearchCameras?wsdl";
	public static String GetCameraInfoURL = "http://localhost:${HttpDefaultPort}/GetCamera?wsdl";
	
	
	/* Purchase Management */
	public static String ListPurchasesURL = "http://localhost:${HttpDefaultPort}/GetPurchases?wsdl";
	public static String GetPurchaseInfoURL = "http://localhost:${HttpDefaultPort}/GetPurchaseInfo?wsdl";
	//public static String OrderShippedURL = "http://localhost:${HttpDefaultPort}/Shipped?wsdl";
	public static String CheckoutURL = "http://localhost:${HttpDefaultPort}/Checkout?wsdl";
}
