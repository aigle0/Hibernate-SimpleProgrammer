package com.simpleprogrammer;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtilities {
	
	private static  SessionFactory sessionFactory;
	private static  ServiceRegistry serviceRegistry;
	
	static
	{
		try{
			//Configuration configuration = new Configuration().configure();//to find the hibernate confg file
			Configuration configuration = new Configuration().setInterceptor(new AuditInterceptor()).configure();//to find the hibernate confg file

			ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
			serviceRegistryBuilder.applySettings(configuration.getProperties());
			serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
			
			//serviceRegistry =  new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		catch(HibernateException exception){
			System.out.println("Problem creating session factoy..." + exception);
		}
	}
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	public static void closeSessionfactory(){
		if (sessionFactory != null)
			sessionFactory.close();
	}

}
