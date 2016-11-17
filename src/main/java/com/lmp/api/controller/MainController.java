
package com.lmp.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import com.google.api.client.util.store.MemoryDataStoreFactory;
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
		
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ProviderOauthFactory providerOauthFactory;
		
	
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
		Set<Sphere> spheresPerson = person.getSpheres();
		for(Sphere sphere : spheresPerson){
			sphereService.removeAttributesByProviderAndSphere(sphere, provider);
		}
		Token token = tokenService.getToken(person, provider);
		if(token != null) {
			tokenService.deleteToken(token);
		}
		personService.updatePersonProviderAssociation(providerId, userId);
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
		SimpleMailMessage simpleMailMessage = createMailMessage(request, person, token, recoverUrl);
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
//		String appUrl = "http://" + request.getServerName() +
//				 ":" + request.getServerPort() +
//				 request.getContextPath() +
//				 "/person/changePassword";
		//tring appUrl = env.getRequiredProperty("mail.reset.password.url");
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

