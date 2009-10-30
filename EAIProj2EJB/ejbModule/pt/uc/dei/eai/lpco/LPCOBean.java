package pt.uc.dei.eai.lpco;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;

import org.hibernate.Criteria;
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
import pt.uc.dei.eai.data.Settings;

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

		Criteria criteria = hbSession.createCriteria(Camera.class);
		criteria.add(Restrictions.eq("id", cameraId));

		Camera result = (Camera) criteria.uniqueResult();

		return result;
	}

	@Override
	public List<Camera> searchCameras(String searchTerms) {
		Criteria criteria = hbSession.createCriteria(Camera.class);
		criteria.add(Restrictions
				.like("model", searchTerms, MatchMode.ANYWHERE));

		@SuppressWarnings("unchecked")
		List<Camera> result = (List<Camera>) criteria.list();

		if (result.isEmpty()) {
			try {
				// Calling Webservice
				Settings setts = new Settings();
				String wsdlURL = setts.getCSwsdl();
				String namespace = setts.getCSnamespace();
				String serviceName = setts.getCSserviceName();
				QName serviceQN = new QName(namespace, serviceName);

				ServiceFactory serviceFactory = ServiceFactory.newInstance();

				CameraSupplierService service = (CameraSupplierService) serviceFactory
						.createService(new URL(wsdlURL), serviceQN);

				CameraSupplier cs = service.getCameraSupplierPort();
				List<pt.uc.dei.eai.cs.Camera> tmp = cs.getCameras(searchTerms);

				Session tsx = HibernateUtil.beginTransaction();
				for (pt.uc.dei.eai.cs.Camera cam : tmp) {
					Camera camToAdd = new Camera(cam);
					result.add(camToAdd);
					tsx.saveOrUpdate(camToAdd);
				}
				HibernateUtil.commitTransaction();

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public boolean submitOrder(List<Camera> shoppingCart) {

		Order order = new Order();
		order.setOrderedCameras(shoppingCart);
		order.setOrderStatus(OrderStatus.WAITING_FOR_SHIPPING);
		order.setUsername(getUser().getUsername());
		order.setShippingAddress(getUser().getAddress());
		order.setPurchaseDate(Calendar.getInstance().getTime());

		try {
			Session session = HibernateUtil.beginTransaction();
			session.save(order);
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
			// Se sim
			// / Invocar WebService
			
			
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
		Criteria criteria = hbSession.createCriteria(Order.class);
		criteria.add(Restrictions.eq("id", orderId));

		Order result = (Order) criteria.uniqueResult();

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

}
