package com.lmp.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lmp.api.model.Person;
import com.lmp.api.service.interfaces.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PersonTests {
	
	@Autowired
	private PersonService personService;

	@Test
	public void addNewPerson() {
		
		String name =  "David";
		String surname = "Alonso";
		String email = "test.davidalonso@gmail.com";
		String phone = "652232323";
		String password = "patata";
		String identifier = "45858585L";
		
		Person person = new Person(name, surname, phone, email, password, identifier);
		
		personService.save(person);
		
		Person personSaved = personService.findPersonByEmail(email);
		
		assertNotNull(personSaved);
		assertEquals(personSaved.getName(), name);
		
		Person p = personService.findOne(personSaved.getId());
		assertNotNull(p);
		assertEquals(personSaved.getName(), p.getName());
		
		personService.delete(personSaved);
	}
	
	@Test
	public void updatePerson(){
		String name =  "David";
		String surname = "Alonso";
		String email = "test.davidalonso@gmail.com";
		String phone = "652232323";
		String password = "patata";
		String identifier = "45858585L";
		
		Person person = new Person(name, surname, phone, email, password, identifier);
		
		personService.save(person);
		
		Person personSaved = personService.findPersonByEmail(email);
		
		String name2 = "Francisco";
		String phone2 = "666666666";
		
		personSaved.setName(name2);
		personSaved.setPhone(phone2);
		
		personService.save(personSaved);
		
		Person personUpdated = personService.findPersonByEmail(email);
		
		assertNotNull(personUpdated);
		assertEquals(personUpdated.getName(), name2);
		assertEquals(personUpdated.getPhone(), phone2);
		assertEquals(personUpdated.getEmail(), email);
		assertEquals(personUpdated.getId(), personSaved.getId());
		
		personService.delete(personUpdated);
	}
	

}
