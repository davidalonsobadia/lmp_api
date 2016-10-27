package com.lmp.api.service;

import java.util.List;

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
		List<Token> tokenList = tokenRepository.findTokensByPersonAndProvider(person, provider);
		if (tokenList != null && tokenList.size() > 0 )
			return tokenList.get(0);
		else
			return null;
			
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
