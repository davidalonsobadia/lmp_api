package com.lmp.api.service;

import java.util.Set;

import com.lmp.api.dao.PersonDao;
import com.lmp.api.model.Person;
import com.lmp.api.model.Sphere;

public interface PersonService {

	public void setPersonDao(PersonDao personDao);
	
	public Person getPersonByName(String username);

	Person getPersonByEmail(String email);

	Person getPersonById(int id);
	
	void updatePersonProviderAssociation(int providerId, int personId);
	
	public boolean isConsumerInList(String userName, String consumerName);

	public Set<Sphere> getSpheres(Person person);
}
