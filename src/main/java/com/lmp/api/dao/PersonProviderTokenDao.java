package com.lmp.api.dao;

import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;

public interface PersonProviderTokenDao {
	
	String getToken(Person person, Provider provider);
}
