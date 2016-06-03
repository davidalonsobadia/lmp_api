package com.lmp.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmp.api.model.Person;
import com.lmp.api.model.ProviderToken;
import com.lmp.api.model.Provider;

public class PersonProviderTokenDaoImpl implements PersonProviderTokenDao{

	
	private static final Logger logger = LoggerFactory.getLogger(PersonProviderTokenDaoImpl.class);

	private SessionFactory sessionFactory;
	
    public void setSessionFactory (SessionFactory sessionFactory){
    	this.sessionFactory = sessionFactory;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public String getToken(Person person, Provider provider) {
    	logger.info("in getToken method");
		Session session = this.sessionFactory.getCurrentSession();

		String query = "from PersonProviderToken where person_id = " + person.getId() 
			+ " and provider_id = " + provider.getId();
				
		List<ProviderToken> personProviderTokens = session.createQuery(query).list();
				
		if(personProviderTokens.size() > 0)
			return personProviderTokens.get(0).getToken();
		return null;		
	}
}
