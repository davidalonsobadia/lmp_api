package com.lmp.api.service;

import com.lmp.api.dao.PersonProviderTokenDao;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;

public interface PersonProviderTokenService {
	
	void setPersonProviderTokenDao(PersonProviderTokenDao personProviderTokenDao);
	
	String getToken(Person person, Provider provider);
}
