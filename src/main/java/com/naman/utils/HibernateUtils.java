package com.naman.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateUtils {
	
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
    
    @Bean(name="sessionFactory")
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			try {
				registry = new StandardServiceRegistryBuilder().configure().build();
				
				MetadataSources sources = new MetadataSources(registry);
				
				Metadata metadata = sources.getMetadataBuilder().build();
				
				sessionFactory = metadata.buildSessionFactory();
			}catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
		}
		return sessionFactory;
	}
	
	public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
