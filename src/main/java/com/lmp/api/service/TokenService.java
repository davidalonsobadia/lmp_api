package com.lmp.api.service;

import com.lmp.api.dao.TokenDao;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Token;

public interface TokenService {
	
	void setPersonProviderTokenDao(TokenDao personProviderTokenDao);
	
	Token getToken(Person person, Provider provider);
	
	void addPersonProviderToken(Token p);
	
	boolean deleteToken(Token token);
}
