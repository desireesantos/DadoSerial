package util;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;


/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 * 
 */

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			 
			sessionFactory = new AnnotationConfiguration().configure()
					.buildSessionFactory();			
			
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		System.out.println(sessionFactory);
		if (sessionFactory == null) {			
			try {
				// Create the SessionFactory from hibernate.cfg.xml
				sessionFactory = new AnnotationConfiguration().configure()
						.buildSessionFactory();
			} catch (Throwable ex) {
				// Make sure you log the exception, as it might be swallowed
				System.err.println("Initial SessionFactory creation failed."
						+ ex);
				throw new ExceptionInInitializerError(ex);
			}

		}
		return sessionFactory;
	}

}
