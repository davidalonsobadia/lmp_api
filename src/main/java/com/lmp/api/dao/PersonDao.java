package com.lmp.api.dao;

import com.lmp.api.model.Person;

public interface PersonDao {
	
	public Person getPerson(String username);
}
