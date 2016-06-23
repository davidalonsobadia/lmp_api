package com.lmp.api.service;

import com.lmp.api.dao.ProviderDao;
import com.lmp.api.model.Provider;

public interface ProviderService {
	
	public void setProviderDao(ProviderDao providerDao);
	
	public Provider getProviderDao(String providerName);

	public Provider getProviderById(int providerId);

}
