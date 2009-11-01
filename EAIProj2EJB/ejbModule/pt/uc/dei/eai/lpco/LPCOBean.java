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

/**
 * Session Bean implementation class LPCOBean
 */
@Stateful
public class LPCOBean implements LPCOBeanRemote, LPCOBeanLocal {

	private User user;
	//private Session hbSession;

	@PostConstruct
	public void initialize() {
		user = null;
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
			return true;
		}
		return false;
	}

	@Override
	public List<Order> listAllOrders() {
		Session session = HibernateUtil.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Order> result = session.createCriteria(Order.class).list();
		
		HibernateUtil.commitTransaction();
		return result;
	}

	@Override
	public List<Order> listPurchases() {
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("orderStatus", OrderStatus.SHIPPED));

		@SuppressWarnings("unchecked")
		List<Order> result = (List<Order>) criteria.list();
		
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
		criteria.add(Restrictions
				.like("model", searchTerms, MatchMode.ANYWHERE));

		@SuppressWarnings("unchecked")
		List<Camera> result = (List<Camera>) criteria.list();
		HibernateUtil.commitTransaction();
		
		if (result.isEmpty()) { //TODO Retirar o ponto de exclamacao
			try {
				// Calling Webservice
//				Settings setts = new Settings();
//				String wsdlURL = setts.getCSwsdl();
//				String namespace = setts.getCSnamespace();
//				String serviceName = setts.getCSserviceName();
				String wsdlURL = "http://127.0.0.1:8080/WSCameraSupplier?wsdl";
				String namespace = "http://cs.eai.dei.uc.pt/";
				String serviceName = "CameraSupplierService";
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
			try {
				// Calling Webservice
				String wsdlURL = "http://127.0.0.1:8080/WSShippingDepartment?wsdl";
				String namespace = "http://sdep.eai.dei.uc.pt/";
				String serviceName = "ShippingDepartmentService";
				QName serviceQN = new QName(namespace, serviceName);

				ServiceFactory serviceFactory = ServiceFactory.newInstance();
				// TODO WebService: MUDAR ESTE CODIGO
				CameraSupplierService service = (CameraSupplierService) serviceFactory
						.createService(new URL(wsdlURL), serviceQN);

				CameraSupplier cs = service.getCameraSupplierPort();
				List<pt.uc.dei.eai.cs.Camera> tmp = cs.getCameras("");
				

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			
			
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
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("id", orderId));

		Order result = (Order) criteria.uniqueResult();
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

}
