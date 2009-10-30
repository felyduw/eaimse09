package pt.uc.dei.eai.lpco;
import java.util.List;

import javax.ejb.Local;

import pt.uc.dei.eai.common.Camera;
import pt.uc.dei.eai.common.Order;

@Local
public interface LPCOBeanLocal {
	public boolean doLogin(String username, String password);
	public boolean doLogout(String username);
	public List<Camera> searchCameras(String searchTerms);
	public Camera getCamera(Integer cameraId);
	public boolean registerUser(String username, String password, String address, String email);
	public boolean submitOrder(List<Camera> shoppingCart);
	public boolean updateOrder(Order order);
	public List<Order> listAllOrders();
	public Order getOrder(Integer orderId);
	public List<Order> listPurchases();
	public Order getPurchase(Integer orderId);
}
