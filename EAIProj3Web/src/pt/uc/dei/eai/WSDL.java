package pt.uc.dei.eai;

public class WSDL {
	
	private static String port = "9081";
	
	/* User Management */
	public static String LoginURL = "http://localhost:" + port + "/Login?wsdl";
	public static String RegisterUserURL = "http://localhost:" + port + "/Register?wsdl";
	
	/* Order Management */
	public static String SearchCamerasURL = "http://localhost:" + port + "/SearchCameras?wsdl";
	public static String GetCameraInfoURL = "http://localhost:" + port + "/GetCamera?wsdl";
	
	
	/* Purchase Management */
	public static String ListPurchasesURL = "http://localhost:" + port + "/GetPurchases?wsdl";
	public static String GetPurchaseInfoURL = "http://localhost:" + port + "/GetPurchaseInfo?wsdl";
	//public static String OrderShippedURL = "http://localhost:" + port + "/Shipped?wsdl";
	public static String CheckoutURL = "http://localhost:" + port + "/Checkout?wsdl";
}
