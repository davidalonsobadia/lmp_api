/*
package com.lmp.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lmp.api.model.LinkedInObject;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.service.AttributeService;
import com.lmp.api.service.PersonProviderTokenService;
import com.lmp.api.service.PersonService;
import com.lmp.api.service.ProviderService;

@RestController
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	public static final String CLIENT_ID = "77e1py78h0rcec";
	public static final String SECRET_CLIENT_ID = "tfc9U9i51mbZYfla";
	public static final String REDIRECT_URI = "http%3A%2F%2Feurecat.org%2Fes%2F";
	public static final String LINKEDIN_AUTHORIZATION_URL = "https://www.linkedin.com/oauth/v2/authorization";
	public static final String RESPONSE_TYPE = "code";
	public static final String STATE = "DCEeFWf45A53sdfKef424";
	public static final String BASIC_SCOPE = "r_basicprofile";
	
	public static final String FORMAT_JSON = "format=json";
	public static final String LINKEDIN_API_CALL = "https://api.linkedin.com/v1/people/~";
	public static final String LINKEDIN_FIELDS = ":(id,location,positions)";
	public static final String QUESTION_MARK = "?";
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired 
	private AttributeService attributeService;
	
	@Autowired
	private PersonProviderTokenService personProviderTokenService;
		
    @RequestMapping("/getAttribute")
    public String greeting(
    		@RequestParam(value="attributeName", required=false) String attributeName,
    		@RequestParam(value="username", required=true) String username,
    		@RequestParam(value="providerName", required=false) String providerName) {  	
    	
    	logger.info("in getAttribute");
    	
    	// 1. Get the user
    	Person person = personService.getPersonDao(username);
    	
    	//1.1 Get the provider
    	Provider provider = providerService.getProviderDao(providerName);
    	
    	// 2. Check whether the user has an access token.
    	String token = personProviderTokenService.getToken(person, provider);
    	
    	logger.info("token: " + token);
    	
    	// 3. If not, go to authorization process ( to get the access token)
    	// TODO: DO IT LATER...
    	
    	// 4. If it has access token, retrieve it
    	
    	
    	// 5. prepare the query
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	String linkedInAPIUrl = "https://api.linkedin.com/v1/people/~?format=json";
    	
    	String ApiAttribute = attributeService.getApiAttributeName(attributeName);
    	 	
    	
    	String token4header = "Bearer " + token;
    	
    	Map<String, String> headerMap = new HashMap<String, String>();
    	headerMap.put("Authorization", token4header);
    	
    	//restTemplate.headForHeaders(linkedInAPIUrl, headerMap);
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Authorization", token4header);
    	
    	HttpEntity<String> request = new HttpEntity<String>(headers);
    	
    	// 6. send the query
    	ResponseEntity<LinkedInObject> response = restTemplate.exchange(linkedInAPIUrl, HttpMethod.GET, request, LinkedInObject.class);
    	
    	// 7. get the data
    	LinkedInObject g = response.getBody();
    	
    	// return the data you are interested in
    	return g.getObjects().get(ApiAttribute);
    	
    }	
}
**/
