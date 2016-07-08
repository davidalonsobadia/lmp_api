package com.lmp.api.model;

public class FacebookOauth implements ProviderOauthObject {
		
	// FACEBOOK DATA: IN PROGRESS...
	private static final String SCOPE = "public_profile,email,user_about_me,user_birthday,user_likes";
	private static final String RESPONSE_TYPE = "code";
	private static final String REDIRECT_URI = "http://localhost:3000/new_provider";
	private static final String REDIRECT_AUTHORIZATION_URI = "http://localhost:8080/authorization";
	private static final String STATE = "DCEeFWf45A53sdfKef424";
	private static final String CLIENT_ID = "1617987675182583";
	private static final String SECRET_ID = "ac40ff181ed97d1cac92246170d36c71";
	private static final String AUTHORIZATION_URL = "https://www.facebook.com/dialog/oauth";
	private static final String ACCESS_TOKEN_URL = "https://graph.facebook.com/v2.3/oauth/access_token";
	private static final String GRANT_TYPE = "";
	private static final String API_URI = "https://graph.facebook.com/v2.6/me?fields=%s";
	private static final String AUTHORIZATION_CODE_PARAMETER = "code";
	private static final String ACCESS_TOKEN_PARAMETER = "access_token";
	
	public FacebookOauth() {
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
