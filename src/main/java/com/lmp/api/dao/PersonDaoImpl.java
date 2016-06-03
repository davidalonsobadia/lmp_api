package com.lmp.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmp.api.model.Person;

public class PersonDaoImpl implements PersonDao{
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

	private SessionFactory sessionFactory;
	
    public void setSessionFactory (SessionFactory sessionFactory){
    	this.sessionFactory = sessionFactory;
    }

    @Override
	@SuppressWarnings("unchecked")
	public Person getPerson(String username){
    	logger.info("in getPerson method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Person where name LIKE '" + username + "'";
		
		List<Person> persons = session.createQuery(query).list();
		
		if(persons.size() > 0)
			return persons.get(0);
		return null;
	}

}
