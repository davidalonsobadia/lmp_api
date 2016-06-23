package com.lmp.api.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmp.api.model.Consumer;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Sphere;

public class PersonDaoImpl implements PersonDao{
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

	private SessionFactory sessionFactory;
	
    public void setSessionFactory (SessionFactory sessionFactory){
    	this.sessionFactory = sessionFactory;
    }

    @Override
	@SuppressWarnings("unchecked")
	public Person getPersonByName(String username){
    	logger.info("in getPersonByName method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Person where name LIKE '" + username + "'";
		
		List<Person> persons = session.createQuery(query).list();
		
		if(persons.size() > 0)
			return persons.get(0);
		return null;
	}
    
    @Override
	@SuppressWarnings("unchecked")
	public Person getPersonByEmail(String email){
    	logger.info("in getPersonByEmail method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Person where email LIKE '" + email + "'";
		
		List<Person> persons = session.createQuery(query).list();
		
		if(persons.size() > 0)
			return persons.get(0);
		return null;
	}
    
    @Override
	@SuppressWarnings("unchecked")
	public Person getPersonById(int id){
    	logger.info("in getPersonById method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Person where id = " + id;
		
		List<Person> persons = session.createQuery(query).list();
		
		if(persons.size() > 0)
			return persons.get(0);
		return null;
	}
    
    
    @Override
	@SuppressWarnings("unchecked")
	public void updatePersonProviderAssociation(int providerId, int userId){
		logger.info("in updatePersonProviderAssociation method");
		Session session = this.sessionFactory.getCurrentSession();
		
		String query = "from Person where id = " + userId;
		
		List<Person> people = session.createQuery(query).list();
		
		Person person = people.get(0);
		
		Set<Provider> providersList = person.getProviders();
			
		Iterator<Provider> i = providersList.iterator();
		while (i.hasNext()){
			Provider provider = i.next();
			if(provider.getId() == providerId){
				i.remove();
				break;
			}
		}
		session.update(person);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean isConsumerInList(String userName, String consumerName) {
		logger.info("in isConsumerInList method");
		Session session = this.sessionFactory.getCurrentSession();
		
		String query = "from Person where name LIKE '" + userName + "'";
		
		List<Person> people = session.createQuery(query).list();
		
		Person person = people.get(0);
		
		List<Consumer> consumersList = person.getConsumers();
			
		Iterator<Consumer> i = consumersList.iterator();
		while (i.hasNext()){
			Consumer consumer = i.next();
			if(consumer.getName().equalsIgnoreCase(consumerName)){
				return true;
			}
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Sphere> getSpheres(Person person) {
    	logger.info("in getSpheres method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from Person where id = " + person.getId();
		
		List<Person> persons = session.createQuery(query).list();
				
		if(persons.size() > 0)
			return persons.get(0).getSpheres();
		return null;
	}
}
