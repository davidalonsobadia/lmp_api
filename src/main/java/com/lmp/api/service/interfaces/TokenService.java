package com.lmp.api.service.interfaces;

import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Token;

public interface TokenService {
	
	Token getToken(Person person, Provider provider);
	
	void addPersonProviderToken(Token p);
	
	void deleteToken(Token token);
}
