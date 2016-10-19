package com.lmp.api.model.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.api.client.http.HttpRequestInitializer;

@Component
public class StravaOAuth implements ProviderOauthObject {
	
	// STRAVA DATA:
	// Parameters for building the authorization code request
	@Value("${provider.strava.authorization.url}")
	private String AUTHORIZATION_URL;
	@Value("${provider.strava.redirect.authorization.url}")
	private String REDIRECT_AUTHORIZATION_URL;
	@Value("${provider.strava.client.id}")
	private String CLIENT_ID;
	@Value("${provider.strava.response.type}")
	private String RESPONSE_TYPE;
	@Value("${provider.strava.state}")
	private String STATE;
	@Value("${provider.strava.scope}")
	private String SCOPE;
	
	// Parameters for building the access token request
	@Value("${provider.strava.access.token.url}")
	private String ACCESS_TOKEN_URL;
	@Value("${provider.strava.secret.id}")
	private String SECRET_ID;
	@Value("${provider.strava.authorization.code}")
	private String AUTHORIZATION_CODE_PARAMETER;
	
	
	//Parameters to rule the API
	@Value("${provider.strava.grant.type}")
	private String GRANT_TYPE;
	@Value("${provider.strava.api.url}")
	private String API_URI;
	@Value("${provider.strava.access.token.parameter}")
	private String ACCESS_TOKEN_PARAMETER;

	public StravaOAuth() {
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

	@Override
	public HttpRequestInitializer getRequestInitializer() {
		// TODO Auto-generated method stub
		return null;
	}	
}
