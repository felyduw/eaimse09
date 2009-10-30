package pt.uc.dei.eai.data;

import org.hibernate.MappingNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateUtil {

	private static SessionFactory factory;
	private static String xmlMappingFile = "eai.hbm.xml";

	public static Configuration getInitializedConfiguration() {
		Configuration config = new Configuration();
		 
		try {
			config.setProperties(System.getProperties());
			config.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
			config.setProperty("hibernate.connection.url", "jdbc:hsqldb:${jboss.server.data.dir}${/}hypersonic${/}localDB");
			config.setProperty("hibernate.connection.username", "sa");
			config.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
			config.setProperty("hibernate.current_session_context_class", "org.hibernate.context.ThreadLocalSessionContext");
			
			config.addFile(xmlMappingFile);
			config.configure();
		} catch (MappingNotFoundException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return config;
	}

	public static void recreateDatabase() {
		Configuration config = HibernateUtil.getInitializedConfiguration();
		new SchemaExport(config).create(true, true);
	}

	public static Session getSession() {
		if (factory == null) {
			Configuration config = HibernateUtil.getInitializedConfiguration();
			factory = config.buildSessionFactory();
		}
		Session hibernateSession = factory.getCurrentSession();
		return hibernateSession;
	}

	public static Session beginTransaction() {
		Session hibernateSession;
		hibernateSession = HibernateUtil.getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public static void commitTransaction() {
		HibernateUtil.getSession().getTransaction().commit();
	}

	public static void closeSession() {
		HibernateUtil.getSession().close();
	}
	
	public static void rollbackTransaction(){
		HibernateUtil.getSession().getTransaction().rollback();
	}
}
