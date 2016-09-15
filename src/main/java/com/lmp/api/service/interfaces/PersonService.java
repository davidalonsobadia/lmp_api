package com.lmp.api.service.interfaces;

import java.util.Set;

import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Sphere;

public interface PersonService {
		
	boolean isConsumerInList(String userName, String consumerName);

	Set<Sphere> getSpheres(Person person);

	void addProvider(Person person, Provider provider);

	Person findPersonByName(String username);

	Person findPersonByEmail(String email);

	void updatePersonProviderAssociation(long providerId, long personId);

	void save(Person person);

	void delete(Person person);

	Person findOne(long id);

}
