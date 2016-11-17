package com.lmp.api.service.interfaces;

import com.lmp.api.model.Attribute;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;

public interface ProviderService {
		
	Provider getProviderByName(String providerName);

	Provider getProviderById(long providerId);

	Provider findProviderByAttributeAndPerson(Attribute attribute, Person person);

}
