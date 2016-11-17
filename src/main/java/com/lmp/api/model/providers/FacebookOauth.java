package com.lmp.api.model.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.google.api.client.http.HttpRequestInitializer;

@Component
@PropertySource("classpath:providers.properties")
public class FacebookOauth implements ProviderOauthObject {
		
	// FACEBOOK DATA
	// Parameters for building the authorization code request
	@Value("${provider.facebook.authorization.url}")
	private String AUTHORIZATION_URL;
	@Value("${provider.facebook.redirect.authorization.url}")
	private String REDIRECT_AUTHORIZATION_URL;
	@Value("${provider.facebook.client.id}")
	private String CLIENT_ID;
	@Value("${provider.facebook.response.type}")
	private String RESPONSE_TYPE;
	@Value("${provider.facebook.state}")
	private String STATE;
	@Value("${provider.facebook.scope}")
	private String SCOPE;
	
	// Parameters for building the access token request
	@Value("${provider.facebook.access.token.url}")
	private String ACCESS_TOKEN_URL;
	@Value("${provider.facebook.secret.id}")
	private String SECRET_ID;
	@Value("${provider.facebook.authorization.code}")
	private String AUTHORIZATION_CODE_PARAMETER;
	
	
	//Parameters to rule the API
	@Value("${provider.facebook.grant.type}")
	private String GRANT_TYPE;
	@Value("${provider.facebook.api.url}")
	private String API_URI;
	@Value("${provider.facebook.access.token.parameter}")
	private String ACCESS_TOKEN_PARAMETER;
	
	public String getAccessTokenParameter() {
		return ACCESS_TOKEN_PARAMETER;
	}

	public String getScope() {
		return SCOPE;
	}

	public String getResponseType() {
		return RESPONSE_TYPE;
	}
	
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
	
	@Override
	public HttpRequestInitializer getRequestInitializer() {
		return null;
	}
}
