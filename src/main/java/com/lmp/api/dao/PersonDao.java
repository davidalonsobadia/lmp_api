package com.lmp.api.dao;

import java.util.Set;

import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Sphere;

public interface PersonDao {
	
	public Person getPersonByName(String username);

	public Person getPersonByEmail(String email);

	Person getPersonById(int id);

	void updatePersonProviderAssociation(int providerId, int userId);

	public boolean isConsumerInList(String userName, String consumerName);

	public Set<Sphere> getSpheres(Person person);

	void addProvider(Person person, Provider provider);
}
