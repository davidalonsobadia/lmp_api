package com.lmp.api.service;

import com.lmp.api.dao.PersonDao;
import com.lmp.api.model.Person;

public interface PersonService {

	public void setPersonDao(PersonDao personDao);
	
	public Person getPersonDao(String username);

}
