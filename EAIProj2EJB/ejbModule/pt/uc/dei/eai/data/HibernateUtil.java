package pt.uc.dei.eai.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateUtil {

	private static SessionFactory factory;
	private static String xmlMappingFile = "eai-hibernate-mapping.xml";

	public static Configuration getInitializedConfiguration() {
		Configuration config = new Configuration();
		config.addFile(xmlMappingFile);
		config.configure();
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
