package com.lmp.api.model.providers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;

@Component
public class FitbitOauth implements ProviderOauthObject {
	
	// Parameters for building the authorization code request
	@Value("${provider.fitbit.authorization.url}")
	private String AUTHORIZATION_URL;
	@Value("${provider.fitbit.redirect.authorization.url}")
	private String REDIRECT_AUTHORIZATION_URL;
	@Value("${provider.fitbit.client.id}")
	private String CLIENT_ID;
	@Value("${provider.fitbit.response.type}")
	private String RESPONSE_TYPE;
	@Value("${provider.fitbit.state}")
	private String STATE;
	@Value("${provider.fitbit.scope}")
	private String SCOPE;
	
	// Parameters for building the access token request
	@Value("${provider.fitbit.access.token.url}")
	private String ACCESS_TOKEN_URL;
	@Value("${provider.fitbit.secret.id}")
	private String SECRET_ID;
	@Value("${provider.fitbit.authorization.code}")
	private String AUTHORIZATION_CODE_PARAMETER;
	
	
	//Parameters to rule the API
	@Value("${provider.fitbit.grant.type}")
	private String GRANT_TYPE;
	@Value("${provider.fitbit.api.url}")
	private String API_URI;
	@Value("${provider.fitbit.access.token.parameter}")
	private String ACCESS_TOKEN_PARAMETER;
	
	
	public HttpRequestInitializer getRequestInitializer(){
		String toEncode = CLIENT_ID + ":" + SECRET_ID;
		String encoded = new String(Base64.encode(toEncode.getBytes()));
		final String authorizationHeader = "Basic " + encoded;
		
		HttpRequestInitializer httpRequestInitializer = new HttpRequestInitializer()
			{
			    @Override
			    public void initialize(HttpRequest request) throws IOException
			    {
			        request.getHeaders().setAuthorization(authorizationHeader);
			        request.getHeaders().setContentType("application/x-www-form-urlencoded");
			    }
			};
		
		return httpRequestInitializer;
	}

	public FitbitOauth() {
	}

	public String getAccessTokenParameter() {
		return ACCESS_TOKEN_PARAMETER;
	}

	public String getScope() {
		return SCOPE;
	}

	public String getResponseType() {
		return RESPONSE_TYPE;
	}

//	public String getRedirectUri() {
//		return REDIRECT_URI;
//	}
	
	public String getRedirectAuthorizationUri() {
		return REDIRECT_AUTHORIZATION_URL;
	}

	public String getState() {
		return STATE;
	}

	public String getClientId() {
		return CLIENT_ID;
	}

	public String getSecretId() {
		return SECRET_ID;
	}

	public String getAuthorizationUrl() {
		return AUTHORIZATION_URL;
	}

	public String getAccessTokenUrl() {
		return ACCESS_TOKEN_URL;
	}

	public String getGrantType() {
		return GRANT_TYPE;
	}

	public String getApiUri() {
		return API_URI;
	}

	public String getAuthorizationCodeParameter() {
		return AUTHORIZATION_CODE_PARAMETER;
	}

}