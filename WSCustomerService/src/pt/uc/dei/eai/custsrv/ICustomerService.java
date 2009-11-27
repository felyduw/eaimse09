package pt.uc.dei.eai.custsrv;
import java.util.List;

import javax.ejb.Remote;

import pt.uc.dei.eai.common.Camera;
import pt.uc.dei.eai.common.Order;
import pt.uc.dei.eai.common.OrderStatus;
import pt.uc.dei.eai.common.User;

@Remote
public interface ICustomerService {
	
	/* User Functionality */
	public User doLogin(String username, String password);
	public boolean doLogout(String username);
	public boolean registerUser(String username, String password, String address, String email);
	
	/* Order Management */
	public boolean submitOrder(List<Camera> cart, User user, OrderStatus status);
	public boolean updateOrder(Order order);
	public List<Order> listAllOrders(String username);
	public Order getOrder(Integer orderId);
	public boolean shipped(Integer orderId, String shippedDates);
	
	/* Purchase Management */
	public List<Order> listPurchases(String username);
	public Order getPurchase(Integer orderId);
	
	/* Auxiliary Functions */
	public boolean doesClientHaveMoney();
}
