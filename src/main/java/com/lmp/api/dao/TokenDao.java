package com.lmp.api.dao;

import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Token;

public interface TokenDao {
	
	Token getToken(Person person, Provider provider);

	void addToken(Token providerToken);

	boolean deleteToken(Token token);
}
