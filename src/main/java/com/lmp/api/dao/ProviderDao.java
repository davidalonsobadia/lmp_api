package com.lmp.api.dao;

import com.lmp.api.model.Provider;

public interface ProviderDao {
	
	public Provider getProvider(String providerName);

	public Provider getProviderById(int providerId);
}
