package com.lmp.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmp.api.model.Consumer;
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
}
