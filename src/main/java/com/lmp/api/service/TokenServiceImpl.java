package com.lmp.api.service;

import org.springframework.transaction.annotation.Transactional;

import com.lmp.api.dao.TokenDao;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Token;

public class TokenServiceImpl implements TokenService{
	
	private TokenDao tokenDao;
	
	@Override
	public void setPersonProviderTokenDao(TokenDao personProviderTokenDao){
		this.tokenDao = personProviderTokenDao;
	};
	
	@Override
	@Transactional
	public Token getToken(Person person, Provider provider){
		return tokenDao.getToken(person, provider);
	}
	
	@Override
	@Transactional
	public void addPersonProviderToken(Token providerToken){
		this.tokenDao.addToken(providerToken);
	}

	@Override
	@Transactional
	public boolean deleteToken(Token token){
		return this.tokenDao.deleteToken(token);
	}
}
