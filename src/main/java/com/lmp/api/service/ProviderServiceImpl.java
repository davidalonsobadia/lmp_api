package com.lmp.api.service;

import org.springframework.transaction.annotation.Transactional;

import com.lmp.api.dao.ProviderDao;
import com.lmp.api.model.Provider;

public class ProviderServiceImpl implements ProviderService{
	
	private ProviderDao providerDao;
	
	@Override
	public void setProviderDao(ProviderDao providerDao) {	
		this.providerDao = providerDao;
	}
	
	@Override
	@Transactional
	public Provider getProviderDao(String providerName){
		return providerDao.getProvider(providerName);
	}
	
	@Override
	@Transactional
	public Provider getProviderById(int providerId){
		return providerDao.getProviderById(providerId);
	}

}
