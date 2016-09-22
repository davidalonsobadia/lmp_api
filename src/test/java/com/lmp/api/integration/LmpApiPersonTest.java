package com.lmp.api.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmp.api.model.Person;
import com.lmp.api.repositories.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class LmpApiPersonTest {
	
	private static final String API_URL = "http://localhost:8080/";
	private static final String PERSON_URL = "http://localhost:8080/people/";

	//Required to Generate JSON content from Java objects
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	//Test RestTemplate to invoke the APIs.
	@Autowired
	private TestRestTemplate restTemplate;
		
	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void CRUDPerson() throws Exception{
		
	    //Building the Request body data
		Map<String, Object> requestBodyJson = new HashMap<String, Object>();
		requestBodyJson.put("name", "David");
		requestBodyJson.put("surname", "Alonso");
		requestBodyJson.put("email", "test.davidalonso@gmail.com");
		requestBodyJson.put("phone", "652232323");
		requestBodyJson.put("password", "patata");
		requestBodyJson.put("identifier", "45858585L");
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		//Creating http entity object with request body and headers
	    HttpEntity<String> httpEntity = 
	        new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBodyJson), requestHeaders);
	    
	    /**
	     * TESTING METHOD POST
	     */
	    
	    //Invoking the API
	    Map<String, Object> apiPostResponse = 
	        restTemplate.postForObject(PERSON_URL, httpEntity, Map.class, Collections.EMPTY_MAP);

	    assertNotNull(apiPostResponse);
	    assertEquals(requestBodyJson.get("name"), apiPostResponse.get("name"));
	    assertEquals(requestBodyJson.get("surname"), apiPostResponse.get("surname"));
	    assertEquals(requestBodyJson.get("email"), apiPostResponse.get("email"));
	    assertEquals(requestBodyJson.get("phone"), apiPostResponse.get("phone"));
	    assertEquals(requestBodyJson.get("password"), apiPostResponse.get("password"));
	    assertEquals(requestBodyJson.get("identifier"), apiPostResponse.get("identifier"));
	    
	    
	    Person person = personRepository.findFirstByEmail(apiPostResponse.get("email").toString());
	    long personId = person.getId();
	    
	    /**
	     * TESTING METHOD GET
	     */
	    
	    Person apiGetResponse = 
		        restTemplate.getForObject(PERSON_URL+personId, Person.class);
	    
	    assertNotNull(apiGetResponse);
	    assertEquals(requestBodyJson.get("name"), apiGetResponse.getName());
	    assertEquals(requestBodyJson.get("surname"), apiGetResponse.getSurname());
	    assertEquals(requestBodyJson.get("email"), apiGetResponse.getEmail());
	    assertEquals(requestBodyJson.get("phone"), apiGetResponse.getPhone());
	    assertEquals(requestBodyJson.get("password"), apiGetResponse.getPassword());
	    assertEquals(requestBodyJson.get("identifier"), apiGetResponse.getIdentifier());
	    
		requestBodyJson.put("password", "patata");
		httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBodyJson), requestHeaders);
		
	    /**
	     * TESTING METHOD PUT (UPDATE)
	     */
		
	    Map<String, Object> apiUpdateResponse =
	    		(Map)restTemplate.exchange(PERSON_URL + personId, 
	    		        HttpMethod.PUT, httpEntity, Map.class, Collections.EMPTY_MAP).getBody();
    
	    
	    assertEquals(requestBodyJson.get("name"), apiUpdateResponse.get("name"));
	    assertEquals(requestBodyJson.get("surname"), apiUpdateResponse.get("surname"));
	    assertEquals(requestBodyJson.get("email"), apiUpdateResponse.get("email"));
	    assertEquals(requestBodyJson.get("phone"), apiUpdateResponse.get("phone"));
	    assertEquals(requestBodyJson.get("password"), apiUpdateResponse.get("password"));
	    assertEquals(requestBodyJson.get("identifier"), apiUpdateResponse.get("identifier"));
	    
	    
	    /**
	     * TESTING METHOD DELETE
	     */
	    
	    restTemplate.delete(PERSON_URL+personId);
	    
	    Person personDeleted = personRepository.findFirstByEmail(apiUpdateResponse.get("email").toString());
	    
	    assertNull(personDeleted);
	}
	
	@Test
	public void loginPerson(){
		
		String loginUrl = API_URL + "loginWithPassword?user={user}&password={password}";
		
		// OK -- AUTHORIZED
		String user2 = "david.alonsobadia@gmail.com";
		String password2 = "123456";
		Map<String, String> urlVariables2 = new HashMap<String, String>();
		urlVariables2.put("user", user2);
		urlVariables2.put("password", password2);
		ResponseEntity response2 = restTemplate.getForEntity(loginUrl, Object.class, urlVariables2);
		assertNotNull(response2);
		assertEquals(200, response2.getStatusCodeValue());
				
		
		// NOT OK --- UNAUTHORIZED
		String user = "david.alonsobadia@gmail.com";
		String password = "1234567";
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("user", user);
		urlVariables.put("password", password);
		ResponseEntity response = restTemplate.getForEntity(loginUrl, Object.class, urlVariables);
		assertNotNull(response);
		assertEquals(401, response.getStatusCodeValue());

	}
		
}
