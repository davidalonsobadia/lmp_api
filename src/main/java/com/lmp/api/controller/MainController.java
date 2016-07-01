
package com.lmp.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.lmp.api.model.Attribute;
import com.lmp.api.model.Consumer;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.ProviderOauthFactory;
import com.lmp.api.model.ProviderOauthObject;
import com.lmp.api.model.Sphere;
import com.lmp.api.model.Token;
import com.lmp.api.service.AttributeService;
import com.lmp.api.service.ConsumerService;
import com.lmp.api.service.PersonService;
import com.lmp.api.service.ProviderService;
import com.lmp.api.service.SphereService;
import com.lmp.api.service.TokenService;

@RestController
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	private AuthorizationCodeFlow authorizationCodeFlow;
	
	private static DataStoreFactory DATA_STORE_FACTORY;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired 
	private AttributeService attributeService;
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private SphereService sphereService;
	
	private Person person;
	
	private Provider provider;
	
	private Consumer consumer;
	
	private final ProviderOauthFactory providerOauthFactory = new ProviderOauthFactory();
	private ProviderOauthObject providerOauth;
	
	@RequestMapping("/getAttribute")
	public Map<String, String> getAttribute(
    		@RequestParam(value="name", required=false) String lmpAttributeName,
    		@RequestParam(value="user", required=true) String username,
    		@RequestParam(value="consumer", required=false) String consumerName,
    		HttpServletResponse httpServletResponse) {  
		
		/**
		 * 
		 * 1. averiguar si el consumidor esta dado de alta para ese atributo en ese usuario
		 * 
		 * 1.1 obtener consumidor
		 * 		 
		 * 1.2 obtener usuario
		 * 
		 * 1.3 ver si ese usuario tiene 'dado de alta' ese consumidor
		 * 
		 * 1.3.1 sino, fuera --> no puede acceder al recurso
		 * 
		 * 1.3.1 si sí que está dado de alta, sigue...
		 * 
		 * 1.4 obtener esferas del usuario.
		 * 
		 * 1.5 ver en qué esferas está incorporado ese consumidor.
		 * 
		 * 1.5.1 si no esta incorporado en ninguna esfera, fuera
		 * 
		 * 1.5.2 si sí que está en alguna esfera, seguir...
		 * 
		 * 1.6 obtener atributo
		 * 
		 * 1.7 obtener atributos de las esferas donde está incorporado el consumidor.
		 * 
		 * 1.8 ver si el atributo que hay en el parámetro está en la lista de atributos de las esferas
		 * 
		 * 1.8.1 si no está, fuera
		 * 
		 * 1.8.2 si esta, hacer la consulta
		 * 
		 * 1.9 utilizar el proveedor correspondiente para hacer dicha consulta.
		 * 
		 * 2. Obtener y Mandar dicho atributo
		 * 
		 */
		
		logger.info("In getAttribute");
		
		//1. averiguar si el consumidor esta dado de alta para ese atributo en ese usuario
		
		//0 --> cargar la respuesta
		Map<String, String> responseMap = new HashMap<String, String>();
		
		//1.1 obtener consumidor
		//Consumer consumer = consumerService.getConsumerByName(consumerName);
		consumer = consumerService.getConsumerByName(consumerName);
		
		if(consumer == null){
    		responseMap.put("key", "error");
    		responseMap.put("value", "There is no consumer with name '" + consumerName);
    		return responseMap;
		}
		
		//1.2 obtener usuario
		//Person person = personService.getPersonByName(username);
		person = personService.getPersonByName(username);
		
		if(person == null){
    		responseMap.put("key", "error");
    		responseMap.put("value", "There is no user with name '" + username);
    		return responseMap;
		}
		
		//1.3 ver si ese usuario tiene 'dado de alta' ese consumidor 
		//1.3.1 sino, fuera --> no puede acceder al recurso
		if(! personService.isConsumerInList(username, consumerName) ){
    		responseMap.put("key", "error");
    		responseMap.put("value", "The user doesn't add the consumer '" + consumerName+ "' to their consumers List.");
    		return responseMap;
		}
		//1.3.1 si sí que está dado de alta, sigue...
		
		//1.4 obtener esferas del usuario.
		Set<Sphere> spheresList = personService.getSpheres(person);
		
		//1.5 ver en qué esferas está incorporado ese consumidor.
		List<Sphere> spheresWithConsumer = new ArrayList<>();
		for(Sphere sphere : spheresList){
			if( sphereService.isConsumer(sphere, consumer))
				spheresWithConsumer.add(sphere);
		}
		
		//1.5.1 si no esta incorporado en ninguna esfera, fuera
		if( spheresWithConsumer.size() <= 0) {
    		responseMap.put("key", "error");
    		responseMap.put("value", "The user doesn't add the consumer '" + consumerName+ "' in any of their Spheres.");
    		return responseMap;
		}
		//1.5.2 si sí que está en alguna esfera, seguir...
		
		//1.6 obtener atributo
		Attribute attribute = attributeService.getAttributeByName(lmpAttributeName);
		
		//1.7 obtener atributos de las esferas donde está incorporado el consumidor.
		List<Attribute> sphereAttributes = new ArrayList<>();
		for (Sphere sphereConsumer : spheresWithConsumer){
			sphereAttributes.addAll(sphereConsumer.getAttributes());
		}
		
		
		for (Attribute sphereAttribute : sphereAttributes){
			//1.8 ver si el atributo que hay en el parámetro está en la lista de atributos de las esferas
			//1.8.2 si esta, hacer la consulta
			if(attribute.getId() == sphereAttribute.getId()){
				//1.9 utilizar el proveedor correspondiente para hacer dicha consulta.
				Provider provider = attribute.getProvider();
				
				//2. Obtener y Mandar dicho atributo
				return makeRequest(lmpAttributeName, username, provider.getName());
			}
		}
		
		//1.8.1 si no está, fuera
		responseMap.put("key", "error");
		responseMap.put("value", "The user doesn't add the Attribute '" + lmpAttributeName +
				"' in any of the Spheres it has the consumer '" + consumerName + "'.");
		return responseMap;
		
		
		
	}
	
    @SuppressWarnings("rawtypes")
	public Map<String, String> makeRequest(
    		String lmpAttributeName,
    		String username,
    		String providerName) {  	
    	logger.info("in makeRequest");
    	
    	Map<String, String> responseMap = new HashMap<String, String>();
    	
    	// 1. Get the user
//    	Person person = personService.getPersonByName(username);
//    	
//    	//1.1 Get the provider
//    	Provider provider = providerService.getProviderDao(providerName);
    	provider = providerService.getProviderDao(providerName);
    	
    	providerOauth = providerOauthFactory.getProviderOauthObject(providerName);
    	
    	// 2. Check whether the user has an access token.
    	Token token = tokenService.getToken(person, provider);
    	
    	// 3. If not, go to authorization process ( to get the access token)
    	if (token == null && provider.isoAuth()){

    		responseMap.put("key", "error");
    		responseMap.put("value", "No access Token permission to get this attribute data");
    		return responseMap;
    	} else if(token != null){
    	
	    	// 5. prepare the query	    	
	    	RestTemplate restTemplate = new RestTemplate();
	    	
	    	String apiAttributeName = attributeService.getApiAttributeName(lmpAttributeName);  	 	
	    	
	    	String token4header = "Bearer " + token.getToken();
	    	    	
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.add("Authorization", token4header);
	    	
	    	HttpEntity<String> request = new HttpEntity<String>(headers);
	    	
	    	// 6. send the query and get the response
	    	ResponseEntity<HashMap> response = restTemplate.exchange(providerOauth.getApiUri(), HttpMethod.GET, request, HashMap.class);
	    	
	    	// 7. get the data
	    	if( response.getStatusCode().is2xxSuccessful() ) {
	    		HashMap<?, ?> objectResponse = response.getBody();
	    		
	    		System.out.print("objectResponse: ");
	    		for (Object s : objectResponse.values()){
	    			System.out.println(s);
	    		}
		    	
	    		// return the data you are interested in
		    	responseMap.put("key", lmpAttributeName);
		    	responseMap.put("value", objectResponse.get(apiAttributeName).toString());
		    	return responseMap;
	    	} else {
	    		logger.error("There was a problem in the API");
	    		logger.error(response.getStatusCode().getReasonPhrase());
	    		
	    		responseMap.put("key", "error");
		    	responseMap.put("value", response.getStatusCode().getReasonPhrase());
	    		return responseMap;
	    	}
	    	
    	} else {
    		responseMap.put("key", "no data yet");
    		return responseMap;
    		
    	}
    }
		
/*
    @RequestMapping("/getAttributeOld")
    public Map<String, String> getAttributeOld(
    		@RequestParam(value="name", required=false) String lmpAttributeName,
    		@RequestParam(value="user", required=true) String username,
    		@RequestParam(value="provider", required=false) String providerName,
    		HttpServletResponse httpServletResponse) {  	
    	logger.info("in getAttributeOld");
    	
    	Map<String, String> responseMap = new HashMap<String, String>();
    	
    	// 1. Get the user
    	Person person = personService.getPersonByName(username);
    	
    	//1.1 Get the provider
    	Provider provider = providerService.getProviderDao(providerName);
    	
    	providerOauth = providerOauthFactory.getProviderOauthObject(providerName);
    	
    	// 2. Check whether the user has an access token.
    	Token token = tokenService.getToken(person, provider);
    	
    	// 3. If not, go to authorization process ( to get the access token)
    	if (token == null && provider.isoAuth()){

    		responseMap.put("key", "error");
    		responseMap.put("value", "No access Token permission to get this attribute data");
    		return responseMap;
    	} else if(token != null){
    	
	    	// 5. prepare the query	    	
	    	RestTemplate restTemplate = new RestTemplate();
	    	
	    	String apiAttributeName = attributeService.getApiAttributeName(lmpAttributeName);  	 	
	    	
	    	String token4header = "Bearer " + token.getToken();
	    	    	
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.add("Authorization", token4header);
	    	
	    	HttpEntity<String> request = new HttpEntity<String>(headers);
	    	
	    	// 6. send the query and get the response
	    	//ResponseEntity<LinkedInObject> response = restTemplate.exchange(API_URI, HttpMethod.GET, request, LinkedInObject.class);
	    	ResponseEntity<HashMap> response = restTemplate.exchange(providerOauth.getApiUri(), HttpMethod.GET, request, HashMap.class);
	    	
	    	// 7. get the data
	    	if( response.getStatusCode().is2xxSuccessful() ) {
	    		HashMap<?, ?> objectResponse = response.getBody();
	    				    	
	    		// return the data you are interested in
		    	responseMap.put("key", lmpAttributeName);
		    	responseMap.put("value", objectResponse.get(apiAttributeName).toString());
		    	return responseMap;
	    	} else {
	    		logger.error("There was a problem in the API");
	    		logger.error(response.getStatusCode().getReasonPhrase());
	    		
	    		responseMap.put("key", "error");
		    	responseMap.put("value", response.getStatusCode().getReasonPhrase());
	    		return responseMap;
	    	}
	    	
    	} else {
    		responseMap.put("key", "no data yet");
    		return responseMap;	
    	}	
    }
*/
   
    @RequestMapping("/checkToken")
    public String checkToken(
    		@RequestParam(value="user", required=true) String username,
    		@RequestParam(value="provider", required=false) String providerName) { 
    
    	// 1. Get the user
    	Person person = personService.getPersonByName(username);
    	
    	//1.1 Get the provider
    	Provider provider = providerService.getProviderDao(providerName);
    	
    	// 2. Check whether the user has an access token.
    	Token token = tokenService.getToken(person, provider);
    	
    	if(token == null || token.getToken() == null){
    		return "no token";
    	} else {
    		return token.getToken();
    	}
    }
    
	@RequestMapping(value = "/createNewToken", method = RequestMethod.GET)
	@ResponseBody
    public void createNewToken(
    		@RequestParam(value="provider", required=true) String providerName,
    		@RequestParam(value="email", required=true) String userEmail,
    		HttpServletRequest httpServletRequest,
    		HttpServletResponse httpServletResponse) throws IOException{
		
		logger.info("creating new token for provider: " + providerName);
		
    	person = personService.getPersonByEmail(userEmail);
    	    	
    	provider = providerService.getProviderDao(providerName);
		
		providerOauth = providerOauthFactory.getProviderOauthObject(providerName);
				
		DATA_STORE_FACTORY = new MemoryDataStoreFactory();
				
		authorizationCodeFlow = new AuthorizationCodeFlow.Builder(
				BearerToken.authorizationHeaderAccessMethod(),
				new NetHttpTransport(),
				new JacksonFactory(),
				new GenericUrl(providerOauth.getAccessTokenUrl()),
				new ClientParametersAuthentication(
						providerOauth.getClientId(),
						providerOauth.getSecretId()),
				providerOauth.getClientId(),
				providerOauth.getAuthorizationUrl()
			)
			.setScopes(Arrays.asList(providerOauth.getScope()))
			.setDataStoreFactory(DATA_STORE_FACTORY)
			.build();
		
		AuthorizationCodeRequestUrl authorizationCodeRequestUrl =  authorizationCodeFlow.newAuthorizationUrl();
		
		Collection<String> responseTypes = new ArrayList<String>();
		responseTypes.add(providerOauth.getResponseType());
		
		authorizationCodeRequestUrl.setResponseTypes(responseTypes);
		authorizationCodeRequestUrl.setRedirectUri(providerOauth.getRedirectAuthorizationUri());
		authorizationCodeRequestUrl.setState(providerOauth.getState());
		
		String url = authorizationCodeRequestUrl.build();
					
		// Redirection to authorization page
		httpServletResponse.setStatus(302);
		httpServletResponse.setHeader("Location", url);
		httpServletResponse.setHeader("referer", httpServletRequest.getHeader("referer"));

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/authorization")
	public void authorize(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		logger.info("in authorization");
		
		//error in authorization code response
		String error = request.getParameter("error");
		if(error != null){
			logger.error(error);
			logger.error(request.getParameter("error_description"));
			
			
		}
				
		String authorizationCode = request.getParameter(providerOauth.getAuthorizationCodeParameter());
		
		AuthorizationCodeTokenRequest authorizationCodeTokenRequest = authorizationCodeFlow.newTokenRequest(authorizationCode);
		
		authorizationCodeTokenRequest.setGrantType(providerOauth.getGrantType());
		authorizationCodeTokenRequest.setRedirectUri(providerOauth.getRedirectAuthorizationUri());

		HttpResponse httpResponse = authorizationCodeTokenRequest.executeUnparsed();
		
		InputStream inputStreamResponse = httpResponse.getContent();
		String stringResponse = IOUtils.toString(inputStreamResponse, "UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = mapper.readValue(stringResponse, Map.class);
		
		String accessToken = jsonMap.get(providerOauth.getAccessTokenParameter()).toString(); 
		
		Token oldToken = tokenService.getToken(person, provider);
		
		// habia un token viejo...
		if (oldToken != null){
			tokenService.deleteToken(oldToken);
		}
		
		Token token = new Token();
		token.setToken(accessToken);
		token.setPerson(person);
		token.setProvider(provider);
		
		tokenService.addPersonProviderToken(token);
		
		response.setStatus(302);
		response.setHeader("location", providerOauth.getRedirectUri());
	}
	
	@RequestMapping("/delete/provider/{providerId}/user/{userId}")
	public void deleteToken(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value="providerId") int providerId,
			@PathVariable(value="userId") int userId) throws IOException{
		
		Person person = personService.getPersonById(userId);
		Provider provider = providerService.getProviderById(providerId);
		Token token = tokenService.getToken(person, provider);
		
		personService.updatePersonProviderAssociation(providerId, userId);
		
		if(token != null) {
			tokenService.deleteToken(token);
		}
	}
	
}

