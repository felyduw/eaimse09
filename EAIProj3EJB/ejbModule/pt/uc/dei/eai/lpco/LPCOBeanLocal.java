package pt.uc.dei.eai.lpco;
import java.util.List;

import javax.ejb.Local;

import pt.uc.dei.eai.common.Camera;
import pt.uc.dei.eai.common.Order;
import pt.uc.dei.eai.common.User;

@Local
public interface LPCOBeanLocal {
	public User getUser();
	public boolean doLogin(String username, String password);
	public boolean doLogout(String username);
	public List<Camera> searchCameras(String searchTerms);
	public Camera getCamera(Integer cameraId);
	public boolean registerUser(String username, String password, String address, String email);
	public boolean submitOrder();
	public boolean updateOrder(Order order);
	public List<Order> listAllOrders();
	public Order getOrder(Integer orderId);
	public List<Order> listPurchases();
	public Order getPurchase(Integer orderId);
	public List<Camera> getShoppingCart();
	public void setShoppingCart(List<Camera> shoppingCart);
	public float getTotalAmount();
	public void addCamera(Camera c);
	public void removeCamera(Camera c);
}
