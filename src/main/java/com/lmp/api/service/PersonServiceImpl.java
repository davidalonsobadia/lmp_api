package com.lmp.api.service;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.lmp.api.dao.PersonDao;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Sphere;

public class PersonServiceImpl implements PersonService{

	private PersonDao personDao;
	
	@Override
	public void setPersonDao(PersonDao personDao){
		this.personDao = personDao;
	}
	
	@Override
	@Transactional
	public Person getPersonByName(String username) {
		return personDao.getPersonByName(username);
	}
	
	@Override
	@Transactional
	public Person getPersonByEmail(String email) {
		return personDao.getPersonByEmail(email);
	}
	
	@Override
	@Transactional
	public Person getPersonById(int id) {
		return personDao.getPersonById(id);
	}
		
	@Override
	@Transactional
	public void updatePersonProviderAssociation(int providerId, int personId) {
		this.personDao.updatePersonProviderAssociation(providerId, personId);
	}

	@Override
	@Transactional
	public boolean isConsumerInList(String userName, String consumerName) {
		return this.personDao.isConsumerInList(userName, consumerName);
	}
	
	@Override
	@Transactional
	public Set<Sphere> getSpheres(Person person) {
		return personDao.getSpheres(person);
	}

	@Override
	@Transactional
	public void addProvider(Person person, Provider provider) {
		this.personDao.addProvider(person, provider);
		
	}
}
