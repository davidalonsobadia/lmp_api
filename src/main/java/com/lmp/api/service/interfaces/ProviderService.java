package com.lmp.api.service.interfaces;

import com.lmp.api.model.Provider;

public interface ProviderService {
		
	Provider getProviderByName(String providerName);

	Provider getProviderById(long providerId);

}
