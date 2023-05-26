package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

//	declare a static variable for a factory
	private static SessionFactory factory;
	
//	static block to access the static variable and initialize it
	static {
//		inside the static block a hibernate session factory is being created and being assigned to the factory variable
		System.out.println("inside the static session factory block");
		factory = new Configuration()//empty config object
						.configure()//populated from hibernate.cfg.xml
						.buildSessionFactory();//attach singleton SF
	}
	
//	static getter
	public static SessionFactory getFactory() {
		return factory;
	}
	
	
}
