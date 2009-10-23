package pt.uc.dei.eai.lpco;
import java.util.List;

import javax.ejb.Remote;

import pt.uc.dei.eai.common.Camera;
import pt.uc.dei.eai.common.Order;

@Remote
public interface LPCOBeanRemote {
	public boolean doLogin(String username, String password);
	public boolean doLogout(String username);
	public boolean addToCart(Integer cameraId);
	public List<Camera> searchCameras(String searchTerms);
	public List<String> registerUser(String username, String password, String address, String email);
	public boolean submitOrder();
	public List<Order> listAllOrders();
	public List<Order> listPurchases();
}
