package pt.uc.dei.eai.custsrv;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pt.uc.dei.eai.common.Camera;
import pt.uc.dei.eai.common.Order;
import pt.uc.dei.eai.common.OrderStatus;
import pt.uc.dei.eai.common.SendMail;
import pt.uc.dei.eai.common.User;
import pt.uc.dei.eai.common.Utility;
import pt.uc.dei.eai.data.HibernateUtil;

@WebService
public class CustomerService implements ICustomerService {
	
	private static String SUBJECT = "Low Price Cameras Online - Order Confirmation";
	private static String FROM = "psaraiva@dei.uc.pt";
	private static String MESSAGE = "The order has been shipped.";

	/******************** User Management */
	@WebMethod
	@Override
	public User doLogin(String username, String password) {
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User u = (User) criteria.uniqueResult();
		HibernateUtil.commitTransaction();

		if (u != null && u.getPassword().equals(password)) {
			return u;
		}
		return null;
	}

	@WebMethod
	@Override
	public boolean doLogout(String username) {
		return true;
	}
	
	@WebMethod
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
	
	/******************** Order Management */
	@WebMethod
	@Override
	public List<Order> listAllOrders(String username) {
		if (username == null) return new ArrayList<Order>();
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("username", username));
		@SuppressWarnings("unchecked")
		List<Order> result = criteria.list();
		HibernateUtil.initializeOrderList(result);
		HibernateUtil.commitTransaction();
		return result;
	}

	@WebMethod
	@Override
	public Integer submitOrder(List<Camera> cart, User user, OrderStatus status) {
		
		if (cart.size() == 0 || user == null)
			return null;
		
		Integer result = -1;

		Order order = new Order();
		order.setOrderedCameras(new ArrayList<Camera>(cart));
		order.setUsername(user.getUsername());
		order.setShippingAddress(user.getAddress());
		order.setEmailAddress(user.getEmail());
		order.setPurchaseDate(Calendar.getInstance().getTime());
		
		order.setOrderStatus(status);
		
		try {
			Session session = HibernateUtil.beginTransaction();
			result = (Integer) session.save(order);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@WebMethod
	@Override
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

	@WebMethod
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
	
	@WebMethod
	@Override
	public boolean shipped(Integer orderId, String shippedDates) {
		String orderEmail = null;
		String bodyMessage = null;

		// Obtain data from order (check database orders)
		Order order = getOrder(orderId);
		
		// Update order status to shipped
		order.setOrderStatus(OrderStatus.SHIPPED);
		updateOrder(order);
		
		// Obtain and add recipient e-mail address
		orderEmail = order.getEmailAddress();		
		
		bodyMessage = MESSAGE + "Order ID: " + orderId;
		
		// Send e-mail
		try {
			SendMail.postMail(orderEmail, SUBJECT, bodyMessage, FROM);
			
			// Order updated and user notified
			Utility.writeLog("Order ID: " + orderId);
		} catch(Exception ex) {
			Utility.writeLog("shipped: " + ex.getMessage());
		}
		
		// Everything OK
		return true;
	}
	
	
	/******************** Purchase Management */
	@WebMethod
	@Override
	public List<Order> listPurchases(String username) {
		if (username == null) return new ArrayList<Order>();
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("orderStatus", OrderStatus.SHIPPED));
		criteria.add(Restrictions.eq("username", username));
		
		@SuppressWarnings("unchecked")
		List<Order> result = (List<Order>) criteria.list();
		HibernateUtil.initializeOrderList(result);
		HibernateUtil.commitTransaction();
		return result;
	}

	@WebMethod
	@Override
	public Order getPurchase(Integer orderId) {
		Order order = getOrder(orderId);
		if (order.getOrderStatus() != OrderStatus.SHIPPED) {
			return null;
		}
		return order;
	}

	@WebMethod
	@Override
	public boolean doesClientHaveMoney() {
		Random r = new Random();
		r.setSeed(Calendar.getInstance().getTimeInMillis());
		r.nextInt(100);
		if (r.nextInt(100) < 75) {
			return true;
		} else {
			return false;
		}
	}
}
