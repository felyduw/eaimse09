package pt.uc.dei.eai.lpco;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import pt.uc.dei.eai.common.*;
import pt.uc.dei.eai.cs.CameraSupplier;
import pt.uc.dei.eai.cs.CameraSupplierService;
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

		if (result == null) {
			try {
				// Calling Webservice
				String wsdlURL = "http://127.0.0.1:8080/WSCameraSupplier?wsdl";
				String namespace = "http://cs.eai.dei.uc.pt/";
				String serviceName = "CameraSupplierService";
				QName serviceQN = new QName(namespace, serviceName);

				ServiceFactory serviceFactory = ServiceFactory.newInstance();
				/* The "new URL(wsdlURL)" parameter is optional */
				CameraSupplierService service = (CameraSupplierService) 
					serviceFactory.createService(new URL(wsdlURL),
						serviceQN);
				
				CameraSupplier cs = service.getCameraSupplierPort();
				cs.getCameras(searchTerms);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
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
