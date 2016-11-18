package com.lmp.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.lmp.api.model.Attribute;
import com.lmp.api.model.AttributeMap;
import com.lmp.api.model.AttributeView;
import com.lmp.api.model.Consumer;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Sphere;
import com.lmp.api.model.Token;
import com.lmp.api.model.providers.ProviderOauthFactory;
import com.lmp.api.model.providers.ProviderOauthObject;
import com.lmp.api.service.interfaces.AttributeCategoryService;
import com.lmp.api.service.interfaces.AttributeMapService;
import com.lmp.api.service.interfaces.AttributeService;
import com.lmp.api.service.interfaces.ConsumerService;
import com.lmp.api.service.interfaces.PersonService;
import com.lmp.api.service.interfaces.ProviderService;
import com.lmp.api.service.interfaces.SphereService;
import com.lmp.api.service.interfaces.TokenService;

@RestController
public class ConsumersController {
	private static final Logger logger = LoggerFactory.getLogger(ConsumersController.class);
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired	
	private SphereService sphereService;
	
	@Autowired
	private AttributeService attributeService;
	
	@Autowired
	private AttributeCategoryService attributeCategoryService;
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AttributeMapService attributeMapService;

	@Autowired
	private ProviderOauthFactory providerOauthFactory;

	@RequestMapping("/getAttribute")
	public Map<String, Object> getAttribute(
    		@RequestParam(value="name", required=false) String lmpAttributeName,
    		@RequestParam(value="email", required=true) String personEmail,
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
		Map<String, Object> responseMap = new HashMap<>();
		
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
		Person person = personService.findPersonByEmail(personEmail);
		
		if(person == null){
    		responseMap.put("key", "error");
    		responseMap.put("value", "There is no user with email '" + personEmail);
    		return responseMap;
		}
		
		//1.3 ver si ese usuario tiene 'dado de alta' ese consumidor 
		//1.3.1 sino, fuera --> no puede acceder al recurso
		if(! personService.isConsumerInList(personEmail, consumerName) ){
    		responseMap.put("key", "error");
    		responseMap.put("value", "The user doesn't add the consumer '" + consumerName+ "' to their consumers List.");
    		return responseMap;
		}
		//1.3.1 si sí que está dado de alta, sigue...
		
		//1.4 obtener esferas del usuario.
		//1.5 ver en qué esferas está incorporado ese consumidor.
		List<Sphere> spheresWithConsumer = sphereService.findByConsumerAndPerson(consumer, person);
		
		
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
		
		
		//1.8 ver si el atributo que hay en el parámetro está en la lista de atributos de las esferas
		//1.8.2 si esta, hacer la consulta
		if(sphereAttributes.contains(attribute) ){
			//1.9 utilizar el proveedor correspondiente para hacer dicha consulta.
			
			//TODO: Aqui saldra una lista de proveedores. De momento, elegir el primero, pero mas adelante
			// habrá que elegir el que tenga más reputación, por ejemplo.					
			Provider provider = providerService.findProviderByAttributeAndPerson(attribute, person);
			
			if (provider == null){
				responseMap.put("key", "error");
	    		responseMap.put("value", "We couldn´t find any valid provider for attribute " + lmpAttributeName + ".");
	    		return responseMap;
			}
			
			//2. Obtener y Mandar dicho atributo
			responseMap.put("key", attribute.getName());
			responseMap.put("value", getAttributeValue(provider, attribute, person));
			
			return responseMap;			
			//return makeRequest(lmpAttributeName, person,  provider);
		} else {
		
			//1.8.1 si no está, fuera
			responseMap.put("key", "error");
			responseMap.put("value", "The user doesn't add the Attribute '" + lmpAttributeName +
					"' in any of the Spheres it has the consumer '" + consumerName + "'.");
			return responseMap;
		}	
	}

	@RequestMapping("/allAttributes")
	@ResponseBody
	public ResponseEntity<List<AttributeView>> getAllAttributes(
			@RequestParam("consumer") String consumerName,
			@RequestParam("email") String personEmail){
		
		Consumer consumer = consumerService.findConsumerByName(consumerName);
		Person person = personService.findPersonByEmail(personEmail);
	
		//TODO: RESOLVER EXCEPCIONES MAS ADELANTE
		if(person== null){
			ResponseEntity<List<AttributeView>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		if(consumer==null){
			ResponseEntity<List<AttributeView>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}		

		Set<Consumer> personConsumers = person.getConsumers();
		if(!personConsumers.contains(consumer)){
			ResponseEntity<List<AttributeView>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		
		List<Sphere> sphereList = sphereService.findByConsumerAndPerson(consumer, person);
		List<Attribute> attributeList = new ArrayList<>();
		for(Sphere sphere : sphereList){
			attributeList.addAll(attributeService.findAttributesBySphere(sphere));
		}
		
		List<AttributeView> attributesViewList = new ArrayList<>();
		for(Attribute attribute: attributeList){
			AttributeView attributeView= new AttributeView(attributeCategoryService.findByAttribute(attribute).getName(),
					attribute.getSubcategory().getName(),
					attribute.getName());
			Provider provider = providerService.findProviderByAttributeAndPerson(attribute, person);
			if (provider != null){
				attributeView.setValue(getAttributeValue(provider, attribute, person));
				attributesViewList.add(attributeView);
			}
		}
		
		ResponseEntity<List<AttributeView>> response = new	 ResponseEntity<>(attributesViewList, HttpStatus.OK);
		return response;
	}
	
	
	
	
	/// --------------------------------------- NON - API ------------------------------------------
	
	public Object getAttributeValue(Provider provider, Attribute attribute, Person person) {
				
		HttpEntity<String> request = prepareHttpRequestToProvider(person, provider);
		
		AttributeMap attributeMap = attributeMapService.findFirstByLmpAttributeNameAndProvider_id(attribute.getName(), 
    			provider.getId());
    	String providerAttributeName = attributeMap.getProviderAttributeName();
		ProviderOauthObject providerOauth = providerOauthFactory.getProviderOauthObject(provider.getName());
      	String ApiUrl = String.format(providerOauth.getApiUri(), providerAttributeName);
    	logger.info("API CALL: " + ApiUrl);
    	try{	   
    		ResponseEntity<Object[]> response = executeRequest(ApiUrl, request);
    		Object result = getResult(response, attributeMap);
    		return result;
    		
    	} catch( RestClientException ex){
    		logger.error("ERROR CONNECTING WITH THE THIRD PARTY API OF " + provider.getName());
    		logger.error(ex.getMessage());
    		return null;
    	}
	}
	
	private HttpEntity<String> prepareHttpRequestToProvider(Person person, Provider provider){
		Token token = tokenService.getToken(person, provider);
    	String authorizationHeader = "Bearer " + token.getToken();
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Authorization", authorizationHeader);
		return new HttpEntity<String>(headers);
	}
	
	
	private static ResponseEntity<Object[]> executeRequest(String url, HttpEntity<String> request) throws RestClientException{
		//ResponseEntity<Map> response = new RestTemplate().exchange(url, HttpMethod.GET, request, Map.class);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, mappingJackson2HttpMessageConverter());
		
		ResponseEntity<Object[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Object[].class);
		logger.info("HTTP response from API: " + response.toString());
		return response;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object getResult(ResponseEntity<Object[]> response, AttributeMap attributeMap){
		Object[] objectResults = response.getBody();
	
		if(objectResults.length <= 0)
			return null;
		else if (objectResults.length == 1){
			Map objectResult = (Map) objectResults[0];
			Map mapResult = new HashMap();
			//mapResult.put(attributeMap.getLmpAttributeName(), getObjectResult(objectResult, attributeMap));
			mapResult.put("value", getObjectResult(objectResult, attributeMap));
			return mapResult;
			
		} else {
			Map map = new HashMap();
			List<Object> list = new ArrayList<>();
			
			int limit = 5;
			for(Object objectResult: objectResults){
				if(limit > 0){
					limit--;
					list.add(getObjectResult(objectResult, attributeMap));
				
				} else {
					break;
				}
			}
			//map.put(attributeMap.getLmpAttributeName(), list);
			map.put("value", list);
			return map;
		}
	}
	
	private static Object getObjectResult(Object objectResult, AttributeMap attributeMap){
		
		Map mapResult = (Map) objectResult;
		JSONObject jsonObject = new JSONObject(mapResult);
		if(attributeMap.getAttributeNameInResponse()==null || attributeMap.getAttributeNameInResponse().equals("")){
			if(!jsonObject.isNull(attributeMap.getProviderAttributeName())){
				Object result = jsonObject.get(attributeMap.getProviderAttributeName());
				return result;
			}
		} else {
			Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
			Object result = JsonPath.read(document, attributeMap.getAttributeNameInResponse());
			return result;
		}
		return null;
	}
	
	
	private static MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	    converter.setObjectMapper(myObjectMapper());
	    return converter;
	}
	
	private static ObjectMapper myObjectMapper() {
			
		ObjectMapper mapper = Jackson2ObjectMapperBuilder.json()
			    .featuresToEnable(
			    	DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
			    .build();
		
		return mapper;
	}
}
