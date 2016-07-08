package com.lmp.api.model;

public class LinkedInOauth implements ProviderOauthObject {
	
	//LINKEDIN DATA
	private static final String SCOPE = "r_basicprofile";
	private static final String RESPONSE_TYPE = "code";
	private static final String REDIRECT_URI = "http://localhost:3000/new_provider";
	private static final String REDIRECT_AUTHORIZATION_URI = "http://localhost:8080/authorization";
	private static final String STATE = "DCEeFWf45A53sdfKef424";
	private static final String CLIENT_ID = "77e1py78h0rcec";
	private static final String SECRET_ID = "tfc9U9i51mbZYfla";
	private static final String AUTHORIZATION_URL = "https://www.linkedin.com/oauth/v2/authorization";
	private static final String ACCESS_TOKEN_URL = "https://www.linkedin.com/oauth/v2/accessToken";
	private static final String GRANT_TYPE = "authorization_code";
	private static final String API_URI = "https://api.linkedin.com/v1/people/~:(%s)?format=json";
	private static final String AUTHORIZATION_CODE_PARAMETER = "code";
	private static final String ACCESS_TOKEN_PARAMETER = "access_token";
	
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

	
}
