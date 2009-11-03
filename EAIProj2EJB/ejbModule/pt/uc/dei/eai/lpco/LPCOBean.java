package pt.uc.dei.eai.lpco;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import pt.uc.dei.eai.common.Camera;
import pt.uc.dei.eai.common.Order;
import pt.uc.dei.eai.common.OrderStatus;
import pt.uc.dei.eai.common.User;
import pt.uc.dei.eai.cs.CameraSupplier;
import pt.uc.dei.eai.cs.CameraSupplierService;
import pt.uc.dei.eai.data.HibernateUtil;
import pt.uc.dei.eai.sdep.ShippingDepartment;
import pt.uc.dei.eai.sdep.ShippingDepartmentService;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * Session Bean implementation class LPCOBean
 */
@Stateful
public class LPCOBean implements LPCOBeanRemote, LPCOBeanLocal {

	@WebServiceRef(wsdlLocation = "http://127.0.0.1:8080/WSCameraSupplier?wsdl")
	static CameraSupplierService CameraService;

	@WebServiceRef(wsdlLocation = "http://127.0.0.1:8080/WSShippingDepartment?wsdl")
	static ShippingDepartmentService ShippingService;

	private User user;
	private List<Camera> shoppingCart;
	
	@PostConstruct
	public void initialize() {
		user = null;
		shoppingCart = new ArrayList<Camera>();
		//HibernateUtil.recreateDatabase();
	}

	public User getUser() {
		return user;
	}

	@Override
	public boolean doLogin(String username, String password) {
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User u = (User) criteria.uniqueResult();
		HibernateUtil.commitTransaction();

		if (u.getPassword().equals(password)) {
			user = u;
			return true;
		}
		return false;
	}

	@Override
	public boolean doLogout(String username) {
		if (user.getUsername().equals(username)) {
			user = null;
			shoppingCart.clear();
			return true;
		}
		return false;
	}

	
	@Override
	public List<Order> listAllOrders() {
		if (user == null) return new ArrayList<Order>();
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("username", user.getUsername()));
		@SuppressWarnings("unchecked")
		List<Order> result = criteria.list();
		HibernateUtil.initializeOrderList(result);
		HibernateUtil.commitTransaction();
		return result;
	}

	@Override
	public List<Order> listPurchases() {
		if (user == null) return new ArrayList<Order>();
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("orderStatus", OrderStatus.SHIPPED));
		criteria.add(Restrictions.eq("username", user.getUsername()));
		
		@SuppressWarnings("unchecked")
		List<Order> result = (List<Order>) criteria.list();
		HibernateUtil.initializeOrderList(result);
		HibernateUtil.commitTransaction();
		return result;
	}

	@Override
	public boolean registerUser(String username, String password,
			String address, String email) {

		Session s = HibernateUtil.beginTransaction();

		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setAddress(address);
		newUser.setEmail(email);

		s.saveOrUpdate(newUser);

		try {
			HibernateUtil.commitTransaction();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Camera getCamera(Integer cameraId) {
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Camera.class);
		criteria.add(Restrictions.eq("id", cameraId));

		Camera result = (Camera) criteria.uniqueResult();
		HibernateUtil.commitTransaction();
		return result;
	}

	@Override
	public List<Camera> searchCameras(String searchTerms) {
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Camera.class);

		if(searchTerms != null && !searchTerms.isEmpty() ) {
			criteria.add(Restrictions
					.like("model", searchTerms, MatchMode.ANYWHERE));
		}

		@SuppressWarnings("unchecked")
		List<Camera> result = (List<Camera>) criteria.list();
		HibernateUtil.commitTransaction();

		if (result.isEmpty()) {
			CameraSupplier cs = CameraService.getCameraSupplierPort();

			List<pt.uc.dei.eai.cs.Camera> tmp = cs.getCameras(searchTerms);

			Session tsx = HibernateUtil.beginTransaction();
			for (pt.uc.dei.eai.cs.Camera cam : tmp) {
				Camera camToAdd = new Camera(cam);
				result.add(camToAdd);
				tsx.saveOrUpdate(camToAdd);
			}
			HibernateUtil.commitTransaction();
		}

		return result;
	}

	@Override
	public boolean submitOrder() {
		
		if (shoppingCart.size() == 0 || user == null)
			return false;

		Order order = new Order();
		order.setOrderedCameras(new ArrayList<Camera>(shoppingCart));
		order.setOrderStatus(OrderStatus.WAITING_FOR_SHIPPING);
		order.setUsername(getUser().getUsername());
		order.setShippingAddress(getUser().getAddress());
		order.setPurchaseDate(Calendar.getInstance().getTime());
		Integer identifier;
		try {
			Session session = HibernateUtil.beginTransaction();
			identifier = (Integer) session.save(order);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

		// Gerar Random aqui
		Random r = new Random();
		r.setSeed(Calendar.getInstance().getTimeInMillis());
		r.nextInt(100);

		if (r.nextInt(100) < 75) {
			ShippingDepartment sd = ShippingService.getShippingDepartmentPort();

			pt.uc.dei.eai.sdep.Order wsOrder = new pt.uc.dei.eai.sdep.Order();
			wsOrder.setShippingAddress(order.getShippingAddress());
			wsOrder.setUsername(order.getUsername());
			XMLGregorianCalendar calendar = new XMLGregorianCalendarImpl(
					(GregorianCalendar) GregorianCalendar.getInstance());
			wsOrder.setPurchaseDate(calendar);
			wsOrder.setOrderId(identifier);

			sd.makeOrder(wsOrder);

		} else {
			order.setOrderStatus(OrderStatus.NOT_PAID);
			try {
				Session session = HibernateUtil.beginTransaction();
				session.update(order);
				HibernateUtil.commitTransaction();
			} catch (HibernateException e) {
				e.printStackTrace();
				return false;
			}
		}
		shoppingCart.clear();
		return true;
	}

	public boolean updateOrder(Order order) {
		try {
			Session session = HibernateUtil.beginTransaction();
			session.saveOrUpdate(order);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Order getOrder(Integer orderId) {
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("id", orderId));

		Order result = (Order) criteria.uniqueResult();
		Hibernate.initialize(result);
		Hibernate.initialize(result.getOrderedCameras());
		HibernateUtil.commitTransaction();

		return result;
	}

	@Override
	public Order getPurchase(Integer orderId) {
		Order order = getOrder(orderId);
		if (order.getOrderStatus() != OrderStatus.SHIPPED) {
			return null;
		}
		return order;
	}
	
	@Override
	public float getTotalAmount() {
		float totalAmount = 0;
		for (int i = 0; i < shoppingCart.size(); i++) {
			Camera currentCamera = shoppingCart.get(i);
			if (currentCamera != null) {
				totalAmount += shoppingCart.get(i).getPrice();
			}
		}
		return totalAmount;
	}
	
	@Override
	public List<Camera> getShoppingCart() {
		return shoppingCart;
	}

	@Override
	public void setShoppingCart(List<Camera> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	@Override
	public void addCamera(Camera c) {
		shoppingCart.add(c);
	}

	@Override
	public void removeCamera(Camera c) {
		shoppingCart.remove(c);
	}

}
