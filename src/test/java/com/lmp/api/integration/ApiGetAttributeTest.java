package com.lmp.api.integration;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class ApiGetAttributeTest {
	
	private static final String API_URL = "http://localhost:8080/";
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getAttribute() {
		
		final String GET_ATTRIBUTE_URL = API_URL + "getAttribute?name={name}&user={user}&consumer={consumer}";
		
		String attribute = "First Name";
		String consumer = "Amazon";
		String user = "Juan";
		
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("name", attribute);
		urlVariables.put("consumer", consumer);
		urlVariables.put("user", user);
				
		Map<String, String> map = restTemplate.getForObject(GET_ATTRIBUTE_URL, HashMap.class, urlVariables);
		
		String key = map.get("key");
		String value = map.get("value");
		
		assertNotNull(map);
		assertEquals(map.size(), 2);
		System.out.println(key);
		System.out.println(value);
		assertEquals(key, "First Name");
		assertEquals(value, "David Alonso");
		
	}

}
