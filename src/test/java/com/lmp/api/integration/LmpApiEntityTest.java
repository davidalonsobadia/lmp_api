package com.lmp.api.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmp.api.model.Organization;
import com.lmp.api.repositories.OrganizationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class LmpApiEntityTest {
	
	private static final String ENTITY_URL = "http://localhost:8080/entities/";

	@Autowired
	OrganizationRepository organizationRepository;
	
	//Required to Generate JSON content from Java objects
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	//Test RestTemplate to invoke the APIs.
	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	public void CRUDEntity() throws JsonProcessingException{
		
	    //Building the Request body data
		Map<String, Object> requestBodyJson = new HashMap<String, Object>();
		requestBodyJson.put("name", "test name");
		requestBodyJson.put("email", "test@test.com");
		requestBodyJson.put("identifier", "xxxxxxxx");
		requestBodyJson.put("description", "description test");
				
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
	        restTemplate.postForObject(ENTITY_URL, httpEntity, Map.class, Collections.EMPTY_MAP);
	    	    
	    assertNotNull(apiPostResponse);
	    
	    assertEquals(requestBodyJson.get("name"), apiPostResponse.get("name"));
	    assertEquals(requestBodyJson.get("email"), apiPostResponse.get("email"));
	    assertEquals(requestBodyJson.get("description"), apiPostResponse.get("description"));
	    assertEquals(requestBodyJson.get("identifier"), apiPostResponse.get("identifier"));
	      		
	    
	    Organization organizaion = organizationRepository.findFirstByEmail(apiPostResponse.get("email").toString());
	    long entityId = organizaion.getId();
	    
	    /**
	     * TESTING METHOD GET
	     */
	    
	    Organization apiGetResponse =
	    		restTemplate.getForObject(ENTITY_URL+entityId, Organization.class);
	    
	    assertNotNull(apiGetResponse);
	    
	    assertEquals(requestBodyJson.get("name"), apiGetResponse.getName());
	    assertEquals(requestBodyJson.get("email"), apiGetResponse.getEmail());
	    assertEquals(requestBodyJson.get("description"), apiGetResponse.getDescription());
	    assertEquals(requestBodyJson.get("identifier"), apiGetResponse.getIdentifier());
	    
	    
	    requestBodyJson.put("identifier", "yyyyyyyy");
	    requestBodyJson.put("description", "description test UPDATED");
	    requestBodyJson.put("name", "test name UPDATED");
	    
	    httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBodyJson), requestHeaders);
	    
	    /**
	     * TESTING METHOD PUT
	     */
	    
	    Map<String, Object> apiUpdateResponse =
	    		(Map)restTemplate.exchange(ENTITY_URL + entityId, 
	    		        HttpMethod.PUT, httpEntity, Map.class, Collections.<String, Object> emptyMap()).getBody();
	   
	    assertNotNull(apiPostResponse);
	    
	    assertEquals(requestBodyJson.get("name"), apiUpdateResponse.get("name"));
	    assertEquals(requestBodyJson.get("email"), apiUpdateResponse.get("email"));
	    assertEquals(requestBodyJson.get("description"), apiUpdateResponse.get("description"));
	    assertEquals(requestBodyJson.get("identifier"), apiUpdateResponse.get("identifier"));
	    
	    /**
	     * TESTING METHOD DELETE
	     */
	    
	    restTemplate.delete(ENTITY_URL + entityId);
	    
	    Organization organizationDeleted = organizationRepository.findFirstByEmail(apiUpdateResponse.get("email").toString());
	    
	    assertNull(organizationDeleted);
	    
	}
}
