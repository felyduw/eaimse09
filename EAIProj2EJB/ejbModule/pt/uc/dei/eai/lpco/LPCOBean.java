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
		// TODO DEBUG - apagar!!!
		if (username != null && username.equals("carlos")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean doLogout(String username) {
		// TODO DEBUG - apagar!!!
		if (username != null && username.equals("carlos")) {
			return true;
		}
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
		// TODO DEBUG - apagar!!!
		if (searchTerms == null || searchTerms.isEmpty()) {
			List<Camera> cameras = new ArrayList<Camera>();
			Camera camera1 = new Camera();
			camera1.setCameraId(1);
			camera1.setModel("Panasonic Lumix GF1");
			camera1.setPrice(10f);
			cameras.add(camera1);
			Camera camera2 = new Camera();
			camera2.setCameraId(2);
			camera2.setModel("Canon EOS 500D (Digital Rebel T1i / Kiss X3 Digital)");
			camera2.setPrice(20f);
			cameras.add(camera2);
			Camera camera3 = new Camera();
			camera3.setCameraId(3);
			camera3.setModel("Leica C-LUX 3");
			camera3.setPrice(30f);
			cameras.add(camera3);
			Camera camera4 = new Camera();
			camera4.setCameraId(4);
			camera4.setModel("Leica M8");
			camera4.setPrice(45f);
			cameras.add(camera4);
			return cameras;
		}
		return null;
	}

	@Override
	public boolean submitOrder() {
		// TODO Auto-generated method stub
		return false;
	}

}