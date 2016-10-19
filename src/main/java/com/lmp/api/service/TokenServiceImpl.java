package com.lmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Token;
import com.lmp.api.repositories.TokenRepository;
import com.lmp.api.service.interfaces.TokenService;

@Service
@Transactional
public class TokenServiceImpl implements TokenService{
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Override
	public Token getToken(Person person, Provider provider){
		return tokenRepository.findTokenByPersonAndProvider(person, provider);
	}
	
	@Override
	public void addPersonProviderToken(Token providerToken){
		this.tokenRepository.save(providerToken);
	}

	@Override
	public void deleteToken(Token token){
		this.tokenRepository.delete(token);
	}
}
