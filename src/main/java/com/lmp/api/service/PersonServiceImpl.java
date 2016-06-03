package com.lmp.api.service;

import org.springframework.transaction.annotation.Transactional;

import com.lmp.api.dao.PersonDao;
import com.lmp.api.model.Person;

public class PersonServiceImpl implements PersonService{

	private PersonDao personDao;
	
	@Override
	public void setPersonDao(PersonDao personDao){
		this.personDao = personDao;
	}
	
	@Override
	@Transactional
	public Person getPersonDao(String username) {
		return personDao.getPerson(username);
	}

}
