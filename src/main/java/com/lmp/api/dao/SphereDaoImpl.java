package com.lmp.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmp.api.model.Consumer;
import com.lmp.api.model.Sphere;

public class SphereDaoImpl implements SphereDao{

	private static final Logger logger = LoggerFactory.getLogger(SphereDaoImpl.class);

	private SessionFactory sessionFactory;
	
    public void setSessionFactory (SessionFactory sessionFactory){
    	this.sessionFactory = sessionFactory;
    }

	@Override
	@SuppressWarnings("unchecked")
	public boolean isConsumer(Sphere sphere, Consumer consumer) {
		logger.info("in isConsumer method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Sphere where id = " + sphere.getId();
		
		List<Sphere> spheres = session.createQuery(query).list();
		
		Sphere sphereResult;
		
		if(spheres.size() > 0)
			sphereResult = spheres.get(0);
		else 
			return false;

		List<Consumer> consumersSphere = sphereResult.getConsumers();
		for(Consumer consumerSphere : consumersSphere){
			if (consumerSphere.getId() == consumer.getId()){
				return true;
			}
		}
		return false;
	}	
}
