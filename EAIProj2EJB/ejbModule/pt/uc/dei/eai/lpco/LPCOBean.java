package pt.uc.dei.eai.lpco;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import pt.uc.dei.eai.common.*;

/**
 * Session Bean implementation class LPCOBean
 */
@Stateful
public class LPCOBean implements LPCOBeanRemote, LPCOBeanLocal {
	private List<Camera> shoppingCart;
	private User user;
	
	@PostConstruct
	public void initialize() {
		shoppingCart = new ArrayList<Camera>();
		user = null;
	}

	public List<Camera> getShoppingCart() {
		return shoppingCart;
	}

	public User getUser() {
		return user;
	}

	@Override
	public boolean addToCart(Integer cameraId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doLogin(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doLogout(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Order> listAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> listPurchases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> registerUser(String username, String password,
			String address, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Camera> searchCameras(String searchTerms) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean submitOrder() {
		// TODO Auto-generated method stub
		return false;
	}

}
