package com.lmp.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Token;

public class TokenDaoImpl implements TokenDao{

	
	private static final Logger logger = LoggerFactory.getLogger(TokenDaoImpl.class);

	private SessionFactory sessionFactory;
	
    public void setSessionFactory (SessionFactory sessionFactory){
    	this.sessionFactory = sessionFactory;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public Token getToken(Person person, Provider provider) {
    	logger.info("in getToken method");
		Session session = this.sessionFactory.getCurrentSession();

		String query = "from Token where person_id = " + person.getId() 
			+ " and provider_id = " + provider.getId();
				
		List<Token> personProviderTokens = session.createQuery(query).list();
				
		if(personProviderTokens.size() > 0)
			return personProviderTokens.get(0);
		return null;		
	}
		
	@Override
	public void addToken(Token token){
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(token);
		logger.info("Token saved successfully, token Details: " + token.toString());
	}
	
	@Override
	public boolean deleteToken(Token token){
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(token);
		logger.info("Token deletected successfully, token Details: " + token.toString());
		return true;
	}
	
}
