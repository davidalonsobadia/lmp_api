package com.lmp.api.service.interfaces;

import com.lmp.api.model.Consumer;
import com.lmp.api.model.Sphere;

public interface SphereService {

	boolean isConsumerInList(Sphere sphere, Consumer consumer);

	Sphere findOne(long id);
	
	void save(Sphere sphere);
	
	Sphere findByIdentifier(String identifier);

	void delete(Sphere sphere);

}
