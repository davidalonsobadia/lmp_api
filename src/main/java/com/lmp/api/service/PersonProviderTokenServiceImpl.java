package com.lmp.api.service;

import org.springframework.transaction.annotation.Transactional;

import com.lmp.api.dao.PersonProviderTokenDao;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;

public class PersonProviderTokenServiceImpl implements PersonProviderTokenService{
	
	private PersonProviderTokenDao personProviderTokenDao;
	
	@Override
	public void setPersonProviderTokenDao(PersonProviderTokenDao personProviderTokenDao){
		this.personProviderTokenDao = personProviderTokenDao;
	};
	
	@Override
	@Transactional
	public String getToken(Person person, Provider provider){
		return personProviderTokenDao.getToken(person, provider);
	}

}
