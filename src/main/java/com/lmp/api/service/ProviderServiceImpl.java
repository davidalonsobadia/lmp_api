package com.lmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
