package pt.uc.dei.eai.custsrv;
import java.util.List;

import javax.ejb.Remote;

import pt.uc.dei.eai.common.Order;

@Remote
public interface ICustomerService {
	
	/* User Functionality */
	public boolean doLogin(String username, String password);
	public boolean doLogout(String username);
	public boolean registerUser(String username, String password, String address, String email);
	
	/* Order Management */
	public boolean submitOrder();
	public boolean updateOrder(Order order);
	public List<Order> listAllOrders();
	public Order getOrder(Integer orderId);
	
	/* Purchase Management */
	public List<Order> listPurchases();
	public Order getPurchase(Integer orderId);
}
