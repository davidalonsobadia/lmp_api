package com.lmp.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmp.api.model.Attribute;
import com.lmp.api.model.AttributeMap;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.repositories.ProviderRepository;
import com.lmp.api.service.interfaces.ProviderService;

@Service
@Transactional
public class ProviderServiceImpl implements ProviderService{
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Override
	public Provider getProviderByName(String providerName){
		return providerRepository.findFirstByName(providerName);
	}
	
	@Override
	public Provider getProviderById(long providerId){
		return providerRepository.findOne(providerId);
	}

	@Override
	public Provider findProviderByAttributeAndPerson(Attribute attribute, Person person) {
		List<AttributeMap> attrMaps = attribute.getAttributeMaps();
		List<Provider> attrMapProviders = new ArrayList<>();
		for(AttributeMap attrMap: attrMaps)
			attrMapProviders.add(attrMap.getProvider());
		Set<Provider> personProviders = person.getProviders();
		attrMapProviders.retainAll(personProviders);
		if(attrMapProviders.size() > 0)
			return attrMapProviders.get(0);
		return null;
	}
}
