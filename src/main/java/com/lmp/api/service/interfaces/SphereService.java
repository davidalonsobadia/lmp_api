package com.lmp.api.service.interfaces;

import java.util.List;
import java.util.Set;

import com.lmp.api.model.Consumer;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Sphere;

public interface SphereService {

	boolean isConsumerInList(Sphere sphere, Consumer consumer);

	Sphere findOne(long id);
	
	void save(Sphere sphere);
	
	Sphere findByIdentifier(String identifier);

	void delete(Sphere sphere);

	List<Sphere> findByConsumerAndPerson(Consumer consumer, Person person);

	Set<Sphere> findByProviderAndPerson(Provider provider, Person person);

	void removeAttributesByProviderAndSphere(Sphere sphere, Provider provider);

}
