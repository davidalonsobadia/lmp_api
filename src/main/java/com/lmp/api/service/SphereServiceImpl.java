package com.lmp.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmp.api.model.Attribute;
import com.lmp.api.model.AttributeMap;
import com.lmp.api.model.Consumer;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Sphere;
import com.lmp.api.repositories.SphereRepository;
import com.lmp.api.service.interfaces.SphereService;

@Service
@Transactional
public class SphereServiceImpl implements SphereService {
	
	@Autowired
	private SphereRepository sphereRepository;
	
	@Override
	public boolean isConsumerInList(Sphere sphere, Consumer consumer) {
		Sphere sphereSaved = sphereRepository.findOne(sphere.getId());
		for(Consumer consumerSphere: sphereSaved.getConsumers()){
			if (consumer.getId() == consumerSphere.getId())
				return true;
		}
		return false;
	}
	
	@Override
	public Sphere findOne(long id){
		return sphereRepository.findOne(id);
	}

	@Override
	public void save(Sphere sphere) {
		this.sphereRepository.save(sphere);
	}

	@Override
	public Sphere findByIdentifier(String identifier) {
		return this.sphereRepository.findByIdentifier(identifier);
	}
	
	@Override
	public void delete(Sphere sphere){
		this.sphereRepository.delete(sphere);
	}

	@Override
	public List<Sphere> findByConsumerAndPerson(Consumer consumer, Person person){
		List<Sphere> spheresList = new ArrayList<>();
		for(Sphere sphere : person.getSpheres()){
			if(sphere.getConsumers().contains(consumer)){
				spheresList.add(sphere);
			}
		}
		return spheresList;
	}

	@Override
	public Set<Sphere> findByProviderAndPerson(Provider provider, Person person) {
		Set<Sphere> spheresSet = person.getSpheres();
		for (Sphere sphere : spheresSet){
			//TODO: not sure...some problems here
		}
		return null;
	}

	@Override
	public void removeAttributesByProviderAndSphere(Sphere sphere, Provider provider) {
		List<Attribute> attributesSphere = sphere.getAttributes();
		List<AttributeMap> attributeMapsProvider = provider.getAttributeMaps();
		for(AttributeMap attributeMapProvider: attributeMapsProvider){
			Attribute attributeProvider = attributeMapProvider.getAttribute();
			if(attributesSphere.contains(attributeProvider)){
				attributesSphere.remove(attributeProvider);
			}
		}		
	}
}
