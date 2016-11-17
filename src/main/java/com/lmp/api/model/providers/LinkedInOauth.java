package com.lmp.api.model.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.google.api.client.http.HttpRequestInitializer;

@Component
@PropertySource("classpath:providers.properties")
public class LinkedInOauth implements ProviderOauthObject {
	
	//LINKEDIN DATA
	// Parameters for building the authorization code request
	@Value("${provider.linkedin.authorization.url}")
	private String AUTHORIZATION_URL;
	@Value("${provider.linkedin.redirect.authorization.url}")
	private String REDIRECT_AUTHORIZATION_URL;
	@Value("${provider.linkedin.client.id}")
	private String CLIENT_ID;
	@Value("${provider.linkedin.response.type}")
	private String RESPONSE_TYPE;
	@Value("${provider.linkedin.state}")
	private String STATE;
	@Value("${provider.linkedin.scope}")
	private String SCOPE;
	
	// Parameters for building the access token request
	@Value("${provider.linkedin.access.token.url}")
	private String ACCESS_TOKEN_URL;
	@Value("${provider.linkedin.secret.id}")
	private String SECRET_ID;
	@Value("${provider.linkedin.authorization.code}")
	private String AUTHORIZATION_CODE_PARAMETER;
	
	
	//Parameters to rule the API
	@Value("${provider.linkedin.grant.type}")
	private String GRANT_TYPE;
	@Value("${provider.linkedin.api.url}")
	private String API_URI;
	@Value("${provider.linkedin.access.token.parameter}")
	private String ACCESS_TOKEN_PARAMETER;
	
	public LinkedInOauth() {
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
