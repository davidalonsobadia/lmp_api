package com.lmp.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;

public class ProviderDaoImpl implements ProviderDao {

	private static final Logger logger = LoggerFactory.getLogger(ProviderDaoImpl.class);

	private SessionFactory sessionFactory;
	
    public void setSessionFactory (SessionFactory sessionFactory){
    	this.sessionFactory = sessionFactory;
    }
    
	@Override
	@SuppressWarnings("unchecked")
	public Provider getProvider(String providerName) {
		logger.info("in getProvider method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Provider where name LIKE '" + providerName + "'";
		
		List<Provider> providers = session.createQuery(query).list();
		
		if(providers.size() > 0)
			return providers.get(0);
		return null;
	}
}
