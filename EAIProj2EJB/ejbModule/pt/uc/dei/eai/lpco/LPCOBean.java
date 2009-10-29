package pt.uc.dei.eai.lpco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pt.uc.dei.eai.common.*;
import pt.uc.dei.eai.data.HibernateUtil;

/**
 * Session Bean implementation class LPCOBean
 */
@Stateful
public class LPCOBean implements LPCOBeanRemote, LPCOBeanLocal {

	private User user;
	private Session hbSession;
	
	
	@PostConstruct
	public void initialize() {
		user = null;
		hbSession = HibernateUtil.getSession();
	}

	public User getUser() {
		return user;
	}

	@Override
	public boolean doLogin(String username, String password) {
		
		Criteria criteria = hbSession.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User u = (User) criteria.uniqueResult();
		
		if(u.getPassword().equals(password)) {
			user = u;
			return true;
		}
		return false;
		
	}

	@Override
	public boolean doLogout(String username) {
		if(user.getUsername().equals(username)) {
			user=null;
			return true;
		}
		return false;
	}

	
	@Override
	public List<Order> listAllOrders() {
		
		@SuppressWarnings("unchecked")
		List<Order> result = hbSession.createCriteria(Order.class).list();
		return result;
	}

	
	@Override
	public List<Order> listPurchases() {
		Criteria criteria = hbSession.createCriteria(Order.class);
		criteria.add(Restrictions.eq("orderStatus", OrderStatus.SHIPPED));
		
		@SuppressWarnings("unchecked")
		List<Order> result = (List<Order>) criteria.list();
		return result;
	}

	@Override
	public boolean registerUser(String username, String password,
			String address, String email) {
		
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setAddress(address);
		newUser.setEmail(email);
		
		return true;
	}
	
	@Override
	public Camera getCamera(Integer cameraId) {
		// TODO DEBUG - apagar!!!
		Camera camera1 = new Camera();
		camera1.setId(cameraId);
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
			camera1.setId(1);
			camera1.setModel("Panasonic Lumix GF1");
			camera1.setPrice(10f);
			cameras.add(camera1);
			Camera camera2 = new Camera();
			camera2.setId(2);
			camera2.setModel("Canon EOS 500D (Digital Rebel T1i / Kiss X3 Digital)");
			camera2.setPrice(20f);
			cameras.add(camera2);
			Camera camera3 = new Camera();
			camera3.setId(3);
			camera3.setModel("Leica C-LUX 3");
			camera3.setPrice(30f);
			cameras.add(camera3);
			Camera camera4 = new Camera();
			camera4.setId(4);
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
		order.setId(1);
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
		order.setId(134);
		order.setPurchaseDate(new Date());
		order.setShippingAddress("Rua Teófilo Braga, nº63, R/C Esq. Coimbra");
		order.setUsername("carlos");
		order.setOrderedCameras(searchCameras(null));
		return order;
	}

}
