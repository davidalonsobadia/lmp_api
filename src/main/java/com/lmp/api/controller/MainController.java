
package com.lmp.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.lmp.api.model.AttributeMap;
import com.lmp.api.model.Consumer;
import com.lmp.api.model.PasswordResetToken;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.SavePasswordBody;
import com.lmp.api.model.Sphere;
import com.lmp.api.model.Token;
import com.lmp.api.model.providers.ProviderOauthFactory;
import com.lmp.api.model.providers.ProviderOauthObject;
import com.lmp.api.service.interfaces.AttributeMapService;
import com.lmp.api.service.interfaces.AttributeService;
import com.lmp.api.service.interfaces.ConsumerService;
import com.lmp.api.service.interfaces.PasswordResetTokenService;
import com.lmp.api.service.interfaces.PersonService;
import com.lmp.api.service.interfaces.ProviderService;
import com.lmp.api.service.interfaces.SphereService;
import com.lmp.api.service.interfaces.TokenService;	

@RestController
@PropertySource("classpath:email.properties")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	private AuthorizationCodeFlow authorizationCodeFlow;
	
	//private static DataStoreFactory DATA_STORE_FACTORY;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired 
	private AttributeService attributeService;
	
	@Autowired
	private AttributeMapService attributeMapService;
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private SphereService sphereService;
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	//TODO: arreglar todo esto usando clases
	//private Person person;
	
	//private Provider provider;
	
	//TODO: ARREGLAR ESTO POR DIOSSSS
	//private String referer;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ProviderOauthFactory providerOauthFactory;
	
	//private ProviderOauthObject providerOauth;
	
	@RequestMapping("/getAttribute")
	public Map<String, String> getAttribute(
    		@RequestParam(value="name", required=false) String lmpAttributeName,
    		@RequestParam(value="user", required=true) String username,
    		@RequestParam(value="consumer", required=false) String consumerName,
    		HttpServletRequest httpServletRequest,
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
		 * 1.8.2 si está, seguir...
		 * 
		 * 1.9 comprobar que proveedor(es) proporciona ese atributo
		 * 
		 * 1.10 ver si alguno de esos proveedores esta en el usuario dado de alta ( de momento elegimos el primero)
		 * 
		 * 1.10.1 si esta dado de alta, hacer la consulta
		 * 
		 * 1.10.2. si no está dado de alta, como coño he llegado hasta aquí? por qué aparecía el atributo en la lista de atrbiutos de las esferas?
		 * 
		 * 1.11 utilizar el proveedor correspondiente para hacer dicha consulta.
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
		Consumer consumer = consumerService.findConsumerByName(consumerName);
		
		if(consumer == null){
    		responseMap.put("key", "error");
    		responseMap.put("value", "There is no consumer with name '" + consumerName);
    		return responseMap;
		}
		
		//1.2 obtener usuario
		//Person person = personService.getPersonByName(username);
		Person person = personService.findPersonByName(username);
		
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
		List<Sphere> spheresWithConsumer = new ArrayList<Sphere>();
		for(Sphere sphere : spheresList){
			if( sphereService.isConsumerInList(sphere, consumer))
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
		Attribute attribute = attributeService.findAttributeByName(lmpAttributeName);
		
		//1.7 obtener atributos de las esferas donde está incorporado el consumidor.
		List<Attribute> sphereAttributes = new ArrayList<Attribute>();
		for (Sphere sphereConsumer : spheresWithConsumer){
			sphereAttributes.addAll(sphereConsumer.getAttributes());
		}
		
		
		for (Attribute sphereAttribute : sphereAttributes){
			//1.8 ver si el atributo que hay en el parámetro está en la lista de atributos de las esferas
			//1.8.2 si esta, hacer la consulta
			if(attribute.getId() == sphereAttribute.getId()){
				//1.9 utilizar el proveedor correspondiente para hacer dicha consulta.
				
				//TODO: Aqui saldra una lista de proveedores. De momento, elegir el primero, pero mas adelante
				// habrá que elegir el que tenga más reputación, por ejemplo.
				
				Set<Provider> userProviders = person.getProviders();
				
				Provider provider = null;
				
				for (Provider userProvider : userProviders){
					List<AttributeMap> attributeMaps = attribute.getAttributeMaps();
					
					for(AttributeMap attributeMap : attributeMaps){
						Provider attributeMapProvider = attributeMap.getProvider();
						if(userProvider.getId() == attributeMapProvider.getId()){
							//TODO: Aqui saldra una lista de proveedores. De momento, elegir el primero, pero mas adelante
							// habrá que elegir el que tenga más reputación, por ejemplo.
							provider = userProvider;
							break;
						}
					}
					
				}
				
				if (provider == null){
					responseMap.put("key", "error");
		    		responseMap.put("value", "We couldn´t find any valid provider for attribute " + lmpAttributeName + ".");
		    		return responseMap;
				}
				
				//2. Obtener y Mandar dicho atributo
				return makeRequest(lmpAttributeName, username, person,  provider);
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
    		Person person,
    		Provider provider) {  	
    	logger.info("in makeRequest");
    	
    	Map<String, String> responseMap = new HashMap<String, String>();
    	
    	// 1. Get the user
//    	Person person = personService.getPersonByName(username);
//    	
//    	//1.1 Get the provider
//    	Provider provider = providerService.getProviderDao(providerName);
    	
    	ProviderOauthObject providerOauth = providerOauthFactory.getProviderOauthObject(provider.getName());
    	
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
	    	
	    	//String providerAttributeName = attributeService.getProviderAttributeName(lmpAttributeName, provider.getId());  	 	
	    	
	    	String providerAttributeName = attributeMapService.findFirstByLmpAttributeNameAndProvider_id(lmpAttributeName, provider.getId()).getProviderAttributeName();
	    	
	    	String token4header = "Bearer " + token.getToken();
	    	    	
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.add("Authorization", token4header);
	    	
	    	HttpEntity<String> request = new HttpEntity<String>(headers);
	    	
	    	//5.1 get the complete URI
	    	String ApiUri = String.format(providerOauth.getApiUri(), providerAttributeName);
	    	
	    	// 6. send the query and get the response
	    	try{	    	
	    		ResponseEntity<HashMap> response = restTemplate.exchange(ApiUri, HttpMethod.GET, request, HashMap.class);
	    		
	    		// 7. get the data
		    	if( response.getStatusCode().is2xxSuccessful() ) {
		    		HashMap<?, ?> objectResponse = response.getBody();

		    		
		    		JSONObject jsonObject = new JSONObject(objectResponse);
		    		JsonParser parser = Json.createParser(new StringReader(jsonObject.toString()));

		    		Object apiAttributeValue = null;
		    		
		    		while (parser.hasNext()){
		    			Event e = parser.next();
		    			if (e == Event.KEY_NAME){
		    				if(parser.getString().equals(providerAttributeName)){
		    					parser.next();
		    					apiAttributeValue = parser.getString();
		    				}
		    			}
		    			
		    		}
		    			    	
		    		// return the data you are interested in
			    	responseMap.put("key", lmpAttributeName);
			    		    			    	
			    	if(apiAttributeValue == null){
			    		logger.error("The third party API doesn't answer correctly. It seems it hasn't the attribute " + lmpAttributeName + " properly configured. Please check API configuration classes.", apiAttributeValue);
			    		responseMap.put("key", "error");
				    	responseMap.put("value", "Bad configuration for " +lmpAttributeName+" with third parties. Please check with LMP administrator");
				    	return responseMap;
			    	}
			    	
			    	responseMap.put("value", apiAttributeValue.toString());
			    	return responseMap;
		    	} else {
		    		logger.error("There was a problem in the API");
		    		logger.error(response.getStatusCode().getReasonPhrase());
		    		
		    		responseMap.put("key", "error");
			    	responseMap.put("value", response.getStatusCode().getReasonPhrase());
		    		return responseMap;
		    	}
	    		
	    	}catch (Exception e){
	    		logger.error("Problem getting the result from Provider");
	    		logger.error(e.getMessage());
	    		responseMap.put("key", "error");
	    		responseMap.put("value", e.getMessage());
	    	}
	    	
	    	return responseMap;
	    	
    	} else {
    		responseMap.put("key", "no data yet");
    		return responseMap;
    		
    	}
    }
		   
    @RequestMapping("/checkToken")
    public String checkToken(
    		@RequestParam(value="user", required=true) String username,
    		@RequestParam(value="provider", required=false) String providerName) { 
    
    	// 1. Get the user
    	Person person = personService.findPersonByName(username);
    	
    	//1.1 Get the provider
    	Provider provider = providerService.getProviderByName(providerName);
    	
    	// 2. Check whether the user has an access token.
    	Token token = tokenService.getToken(person, provider);
    	
    	if(token == null || token.getToken() == null){
    		return "no token";
    	} else {
    		return token.getToken();
    	}
    }
    
	@RequestMapping(value = "/authorizationUrl", method = RequestMethod.GET)
	@ResponseBody
    public Map<String,String> authorizationUrl(
    		@RequestParam(value="provider", required=true) String providerName,
    		@RequestParam(value="email", required=true) String userEmail,
    		@RequestParam(value="redirectUrl", required=true) String redirectUrl,
    		HttpServletRequest httpServletRequest,
    		HttpServletResponse httpServletResponse) throws IOException{
		logger.info("creating new token for provider: " + providerName);
		

		ProviderOauthObject providerOauth = providerOauthFactory.getProviderOauthObject(providerName);
				
		authorizationCodeFlow = createAuthorizationCodeFlow(providerOauth);
						
		AuthorizationCodeRequestUrl authorizationCodeRequestUrl = createAuthorizationCodeRequestUrl(authorizationCodeFlow, 
				providerOauth, redirectUrl);
				
		String authorizationUrl = authorizationCodeRequestUrl.build();
		
		logger.info("authorization code request Url: " + authorizationUrl);
					
		Map<String, String> responseMap = new HashMap<>();
		
		responseMap.put("authorizationUrl", authorizationUrl);
		responseMap.put("provider", providerName);
		responseMap.put("email", userEmail);
		
		return responseMap;
	}
	

	@SuppressWarnings("unchecked")
	@RequestMapping("/newToken")
	public String newToken(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@RequestParam(value="provider") String providerName,
			@RequestParam(value="email") String userEmail,
			@RequestParam(value="redirectUrl") String redirectUrl,
			@RequestParam(value="code") String authorizationCode) throws IOException {
		logger.info("in authorize");
		//error in authorization code response
		String error = httpServletRequest.getParameter("error");
		if(error != null){
			logger.error(error);
			logger.error(httpServletRequest.getParameter("error_description"));
			
			return null;
		
		} else {
			
			ProviderOauthObject providerOauthObj = providerOauthFactory.getProviderOauthObject(providerName);			
			
			AuthorizationCodeTokenRequest authorizationCodeTokenRequest = createAuthorizationCodeTokenRequest(authorizationCode, 
					providerOauthObj, redirectUrl);
						
			HttpResponse httpResponse = authorizationCodeTokenRequest.executeUnparsed();
			
			InputStream inputStreamResponse = httpResponse.getContent();
			String stringResponse = IOUtils.toString(inputStreamResponse, "UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> jsonMap = mapper.readValue(stringResponse, Map.class);
			
			String accessToken = jsonMap.get(providerOauthObj.getAccessTokenParameter()).toString(); 
						
			Token oldToken = tokenService.getToken(
					personService.findPersonByEmail(userEmail),
					providerService.getProviderByName(providerName));
			// There was an old token inside.. delete it
			if (oldToken != null){
				tokenService.deleteToken(oldToken);
			}
			
			Token token = createToken(userEmail, providerName, accessToken);
						
			return token.getToken();
	
		}
		
	}

	@RequestMapping("/delete/provider/{providerId}/user/{userId}")
	public void deleteToken(HttpServletRequest request,
			@PathVariable(value="providerId") int providerId,
			@PathVariable(value="userId") int userId) throws IOException{
		
		Person person = personService.findOne(userId);
		Provider provider = providerService.getProviderById(providerId);
		Token token = tokenService.getToken(person, provider);
		personService.updatePersonProviderAssociation(providerId, userId);
		
		if(token != null) {
			tokenService.deleteToken(token);
		}
	}

	@RequestMapping(value = "/person/resetPassword", method = RequestMethod.GET)
	public void resetPassword(
			HttpServletRequest request,
			@RequestParam("email") String email,
			@RequestParam("recoverUrl") String recoverUrl){

		Person person = personService.findPersonByEmail(email);
		if(person == null){
			logger.error("person not found");
			return;
		}
		String token = UUID.randomUUID().toString();
		personService.createPasswordResetTokenForPerson(person, token);
		SimpleMailMessage simpleMailMessage = createMailMessage(request, person, token);
		mailSender.send(simpleMailMessage);
	}

	@RequestMapping(value="/person/savePassword", method=RequestMethod.POST)
	public void savePassword(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody SavePasswordBody input) {
			
		String validatedToken = validatePasswordResetToken(input.getEmail(), input.getToken());
		if(validatedToken.equals("valid")){
			Person person = personService.findPersonByEmail(input.getEmail());
			personService.changePassword(person, input.getPassword());
			passwordResetTokenService.deleteByToken(input.getToken());
			response.setStatus(200);
		} else {
			logger.error("An error occurred validating the token...");
			logger.error("ERROR: " + validatedToken);
			response.setStatus(400);
		}
	}
	
	
	// ------------------- NON API -------------------
	
	private SimpleMailMessage createMailMessage(HttpServletRequest request, 
			Person person, String token, String recoverUrl) {		
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		try {
			String emailText = "Hello " + person.getName() + ",\n\n" +
		        "To create a new password please click the URL below:\n" +
		        recoverUrl + "?" +
		        "token=" + URLEncoder.encode(token, "UTF-8") +
		        "&email=" + URLEncoder.encode(person.getEmail(), "UTF-8");	
			mailMessage.setFrom(env.getRequiredProperty("smtp.username"));
			mailMessage.setTo(person.getEmail());
			mailMessage.setSubject("LMP password recovery request");
			mailMessage.setText(emailText);
		} catch (UnsupportedEncodingException e) {
			logger.error("ERROR CREATING MAIL MESSAGE");
			e.printStackTrace();
		}
		return mailMessage;
	}
	
	private String validatePasswordResetToken(String email, String token) {
        final PasswordResetToken passToken = personService.getPasswordResetToken(token);
        final Person passPerson = personService.findPersonByEmail(email);
        if ((passToken == null) || (passPerson == null) || (passToken.getPerson().getId() != passPerson.getId())) {
            return "invalidToken";
        }
        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExiprationDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "token expired";
        }
        return "valid";
    }
	
	private Token createToken(String userEmail, String providerName, String accessToken) {
		Person person = personService.findPersonByEmail(userEmail);
		Provider provider = providerService.getProviderByName(providerName);
		
		Token token = new Token();
		token.setToken(accessToken);
		token.setPerson(person);
		token.setProvider(provider);
		
		tokenService.addPersonProviderToken(token);
		personService.addProvider(person, provider);
		
		return token;
	}

	private AuthorizationCodeTokenRequest createAuthorizationCodeTokenRequest(
			String authorizationCode, 
			ProviderOauthObject providerOauthObj,
			String redirectUrl) {
		
		AuthorizationCodeTokenRequest authorizationCodeTokenRequest = authorizationCodeFlow.newTokenRequest(authorizationCode);
			authorizationCodeTokenRequest.setRequestInitializer(providerOauthObj.getRequestInitializer());
			authorizationCodeTokenRequest.setGrantType(providerOauthObj.getGrantType());
			authorizationCodeTokenRequest.setRedirectUri(redirectUrl);
		
		logger.info(authorizationCodeTokenRequest.getRedirectUri());
		logger.info(authorizationCodeTokenRequest.getGrantType());
		
		return authorizationCodeTokenRequest;
	}
	
	private AuthorizationCodeRequestUrl createAuthorizationCodeRequestUrl(AuthorizationCodeFlow authorizationCodeFlow,
			ProviderOauthObject providerOauth,
			String redirectUrl) {
		
		AuthorizationCodeRequestUrl authorizationCodeRequestUrl = authorizationCodeFlow.newAuthorizationUrl();
		
		Collection<String> responseTypes = new ArrayList<String>();
		responseTypes.add(providerOauth.getResponseType());
	
		authorizationCodeRequestUrl.setResponseTypes(responseTypes);
		authorizationCodeRequestUrl.setRedirectUri(redirectUrl);
		authorizationCodeRequestUrl.setState(providerOauth.getState());
		
		return authorizationCodeRequestUrl;
	}

	private AuthorizationCodeFlow createAuthorizationCodeFlow(ProviderOauthObject providerOauthObj) throws IOException {
		return new AuthorizationCodeFlow.Builder(
				BearerToken.authorizationHeaderAccessMethod(),
				new NetHttpTransport(),
				new JacksonFactory(),
				new GenericUrl(providerOauthObj.getAccessTokenUrl()),
				new ClientParametersAuthentication(
						providerOauthObj.getClientId(),
						providerOauthObj.getSecretId()),
				providerOauthObj.getClientId(),
				providerOauthObj.getAuthorizationUrl()
			)
			.setScopes(Arrays.asList(providerOauthObj.getScope()))
			.setDataStoreFactory(new MemoryDataStoreFactory())
			.build();
	}

}

