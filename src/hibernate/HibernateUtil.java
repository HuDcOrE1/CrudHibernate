package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static SessionFactory factory;
	public static Configuration config;
	
	public static SessionFactory buildSessionFactory(){
		try{
			config = new Configuration();
			config.configure("hibernate.hbm.xml");
			System.out.println("LA CONFIGURACION FUE EXITOSA");
			factory = config.buildSessionFactory();
			return factory;
		}catch(HibernateException e){
			System.out.println("Ocurrio un error "+e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}
	public static SessionFactory getSessionFactory(){
		if(factory == null){
			factory = buildSessionFactory();
		}
		return factory;
	}
}
