package pt.uc.dei.eai.lpco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import pt.uc.dei.eai.common.*;

/**
 * Session Bean implementation class LPCOBean
 */
@Stateful
public class LPCOBean implements LPCOBeanRemote, LPCOBeanLocal {

	private User user;
	
	@PostConstruct
	public void initialize() {
		user = null;
	}

	public User getUser() {
		return user;
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
		// TODO DEBUG - apagar!!!
		List<Order> orders = new ArrayList<Order>();
		Order order1 = new Order();
		order1.setOrderId(1);
		order1.setPurchaseDate(new Date());
		order1.setShippingAddress("Rua Teófilo Braga, nº63, R/C Esq. Coimbra");
		order1.setUsername("carlos");
		order1.setOrderedCameras(searchCameras(null));
		orders.add(order1);
		Order order2 = new Order();
		order2.setOrderId(2);
		order2.setPurchaseDate(new Date());
		order2.setShippingAddress("Rua do Teodoro, n.º77, R/C; 3030-213 Coimbra");
		order2.setUsername("jaquim");
		order2.setOrderedCameras(searchCameras(null));
		orders.add(order2);
		return orders;
	}

	@Override
	public List<Order> listPurchases() {
		// TODO DEBUG - apagar!!!
		return listAllOrders();
	}

	@Override
	public List<String> registerUser(String username, String password,
			String address, String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Camera getCamera(Integer cameraId) {
		// TODO DEBUG - apagar!!!
		Camera camera1 = new Camera();
		camera1.setCameraId(cameraId);
		camera1.setModel("some model");
		camera1.setPrice(cameraId.floatValue() * 10);
		return camera1;
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
			camera4.setPrice(40f);
			cameras.add(camera4);
			return cameras;
		}
		return null;
	}

	@Override
	public boolean submitOrder(List<Camera> shoppingCart) {
		// TODO DEBUG - apagar!!!
		return true;
	}

	@Override
	public Order getOrder(Integer orderId) {
		// TODO DEBUG - apagar!!!
		Order order = new Order();
		order.setOrderId(1);
		order.setPurchaseDate(new Date());
		order.setShippingAddress("Rua Teófilo Braga, nº63, R/C Esq. Coimbra");
		order.setUsername("carlos");
		order.setOrderedCameras(searchCameras(null));
		return order;
	}

	@Override
	public Order getPurchase(Integer orderId) {
		// TODO DEBUG - apagar!!!
		Order order = new Order();
		order.setOrderId(134);
		order.setPurchaseDate(new Date());
		order.setShippingAddress("Rua Teófilo Braga, nº63, R/C Esq. Coimbra");
		order.setUsername("carlos");
		order.setOrderedCameras(searchCameras(null));
		return order;
	}

}
