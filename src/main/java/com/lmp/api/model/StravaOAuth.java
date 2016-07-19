package com.lmp.api.model;

import com.google.api.client.http.HttpRequestInitializer;

public class StravaOAuth implements ProviderOauthObject {
	
	// STRAVA DATA:
	
	// Parameters for building the authorization code request
	private static final String AUTHORIZATION_URL = "https://www.strava.com/oauth/authorize";
	private static final String REDIRECT_AUTHORIZATION_URI = "http://localhost:8080/authorization";
	private static final String CLIENT_ID = "12531";
	private static final String RESPONSE_TYPE = "code";
	private static final String STATE = "DCEeFWf45A53sdfKef424";
	private static final String SCOPE = "view_private";
	
	// Parameters for building the access token request
	private static final String ACCESS_TOKEN_URL = "https://www.strava.com/oauth/token";
	private static final String SECRET_ID = "6b66f849a6a30babb7fa1a2380ba08536c04877b";
	private static final String AUTHORIZATION_CODE_PARAMETER = "code";
	
	//Other
	private static final String REDIRECT_URI = "http://localhost:3000/new_provider";
	
	//Parameters to rule the API
	private static final String GRANT_TYPE = "";
	private static final String API_URI = "https://www.strava.com/api/v3/athlete";
	private static final String ACCESS_TOKEN_PARAMETER = "access_token";

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

	public String getRedirectUri() {
		return REDIRECT_URI;
	}
	
	public String getRedirectAuthorizationUri() {
		return REDIRECT_AUTHORIZATION_URI;
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
