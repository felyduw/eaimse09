package pt.uc.dei.eai.lpco;
import java.util.List;

import javax.ejb.Remote;

import pt.uc.dei.eai.common.Camera;
import pt.uc.dei.eai.common.Order;

@Remote
public interface LPCOBeanRemote {
	public boolean doLogin(String username, String password);
	public boolean doLogout(String username);
	public List<Camera> searchCameras(String searchTerms);
	public Camera getCamera(Integer cameraId);
	public boolean registerUser(String username, String password, String address, String email);
	public boolean submitOrder(List<Camera> shoppingCart);
	public List<Order> listAllOrders();
	public Order getOrder(Integer orderId);
	public List<Order> listPurchases();
	public Order getPurchase(Integer orderId);
}
