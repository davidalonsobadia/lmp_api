package com.lmp.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmp.api.model.Consumer;

public class ConsumerDaoImpl implements ConsumerDao {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerDaoImpl.class);

	private SessionFactory sessionFactory;
	
    public void setSessionFactory (SessionFactory sessionFactory){
    	this.sessionFactory = sessionFactory;
    }
    
	@Override
	@SuppressWarnings("unchecked")
	public Consumer getConsumerByName(String name) {
		logger.info("in getConsumerByName method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Consumer where name LIKE '" + name + "'";
		
		List<Consumer> consumers = session.createQuery(query).list();
		
		if(consumers.size() > 0)
			return consumers.get(0);
		return null;
	}

}
