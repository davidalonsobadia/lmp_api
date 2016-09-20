package com.lmp.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lmp.api.RemoteUMClient;
import com.lmp.api.model.Person;
import com.lmp.api.service.interfaces.PersonService;


@BasePathAwareController
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private RemoteUMClient remoteUMClient;

	@RequestMapping(value="/people", method=RequestMethod.POST)
	public ResponseEntity<Person> saveUser(@RequestBody Person person) throws Exception{
		logger.info("----------------- in ResponseEntity<Person> saveUser");
		try{
			
			Map<String, String> claims = new HashMap<String, String>();
			claims.put("http://wso2.org/claims/givenname", person.getName());
			claims.put("http://wso2.org/claims/lastname", person.getSurname());
			claims.put("http://wso2.org/claims/emailaddress", person.getEmail());
			
			remoteUMClient.createRemoteUserStoreManager();
			remoteUMClient.remoteUserStoreManager.addUser(person.getEmail(), person.getPassword(), null, claims, null);
		} catch (Exception e) {
			logger.error("An error occurred while saving the user in WSO2 IS");
			logger.error(e.getMessage());
			throw e;
		}
		personService.save(person);
		Resource<Person> resource = new Resource<Person>(person);
		ResponseEntity<Person> responseEntity = new ResponseEntity(resource, HttpStatus.CREATED);
		return responseEntity;
    }
	
	@RequestMapping(value="/people/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable long id) throws Exception{
		logger.info("----------------- in ResponseEntity<Person> deleteUser");
		Person person = personService.findOne(id);
		if(person == null){
			logger.error("Not person found with ID "+id);
			throw new Exception("Not person found with ID "+id);
		}
		try{
			remoteUMClient.createRemoteUserStoreManager();
			remoteUMClient.remoteUserStoreManager.deleteUser(person.getEmail());
		} catch (Exception e) {
			logger.error("An error occurred while saving the user in WSO2 IS");
			logger.error(e.getMessage());
			throw e;
		}
		personService.delete(person);
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.OK);
		return responseEntity;
    }
	
	@RequestMapping(value="/people/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Person> updateUser(
			@PathVariable long id,
			@RequestBody Person newPerson) throws Exception{
		logger.info("----------------- in ResponseEntity<Person> deleteUser");
		Person oldPerson = personService.findOne(id);
		newPerson.setId(id);
		if(oldPerson == null) {
			logger.error("Not person found with ID "+id);
			throw new Exception("Not person found with ID "+id);
		}
		try{
			Map<String, String> claims = new HashMap<String, String>();
			claims.put("http://wso2.org/claims/givenname", newPerson.getName());
			claims.put("http://wso2.org/claims/lastname", newPerson.getSurname());
			claims.put("http://wso2.org/claims/emailaddress", newPerson.getEmail());
			
			remoteUMClient.createRemoteUserStoreManager();
			remoteUMClient.remoteUserStoreManager.deleteUser(oldPerson.getEmail());
			remoteUMClient.remoteUserStoreManager.addUser(newPerson.getEmail(), newPerson.getPassword(), null, claims, null);
			
		} catch (Exception e) {
			logger.error("An error occurred while saving the user in WSO2 IS");
			logger.error(e.getMessage());
			throw e;
		}
		personService.save(newPerson);
		Resource<Person> resource = new Resource<Person>(newPerson);
		ResponseEntity<Person> responseEntity = new ResponseEntity(resource, HttpStatus.OK);
		return responseEntity;
    }
}
