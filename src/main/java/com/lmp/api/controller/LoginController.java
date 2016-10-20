package com.lmp.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lmp.api.service.interfaces.PersonService;
import com.lmp.api.utils.RemoteUMClient;

@RestController
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private RemoteUMClient remoteUMClient;
	
	@Autowired
	private PersonService personService;
	
	
	@RequestMapping(value="/loginWithPasswordWSO2", method=RequestMethod.GET)
	public ResponseEntity<?> loginWithPasswordWSO2(
			@RequestParam String user,
			@RequestParam String password){		
		ResponseEntity<?> responseEntity;
		try{
			remoteUMClient.createRemoteUserStoreManager();
			boolean isAuthenticated = remoteUMClient.remoteUserStoreManager.authenticate(user, password);
			if (isAuthenticated)
				responseEntity = new ResponseEntity<>(HttpStatus.OK);
			else 
				responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			return responseEntity;
		} catch (Exception e){
			logger.error("An Error ocurred when trying to authenticate");
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
	}
	
	
	@RequestMapping(value="/loginWithPassword", method=RequestMethod.GET)
	public ResponseEntity<?> loginWithPassword(
			@RequestParam String email,
			@RequestParam String password){
		ResponseEntity<?> responseEntity;
		try {
			boolean isAuthenticated = personService.authenticate(email, password);
			if(isAuthenticated)
				responseEntity = new ResponseEntity<>(HttpStatus.OK);
			else
				responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			return responseEntity;
		} catch (Exception e){
			logger.error("An Error ocurred when trying to authenticate");
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return responseEntity;
		}		
	}
}
