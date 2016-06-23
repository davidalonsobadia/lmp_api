package com.lmp.api.model;

public class FacebookOauth implements ProviderOauthObject {
	
	//LINKEDIN DATA
//	private static final String SCOPE = "r_basicprofile";
//	private static final String RESPONSE_TYPE = "code";
//	private static final String REDIRECT_URI = "http://localhost:3000/my_providers";
//	private static final String REDIRECT_AUTHORIZATION_URI = "http://localhost:8080/authorization";
//	private static final String STATE = "DCEeFWf45A53sdfKef424";
//	private static final String CLIENT_ID = "77e1py78h0rcec";
//	private static final String SECRET_ID = "tfc9U9i51mbZYfla";
//	private static final String AUTHORIZATION_URL = "https://www.linkedin.com/oauth/v2/authorization";
//	private static final String ACCESS_TOKEN_URL = "https://www.linkedin.com/oauth/v2/accessToken";
//	private static final String GRANT_TYPE = "authorization_code";
//	private static final String API_URI = "https://api.linkedin.com/v1/people/~?format=json";
//	private static final String AUTHORIZATION_CODE_PARAMETER = "code";
//	private static final String ACCESS_TOKEN_PARAMETER = "access_token";
	
	// FACEBOOK DATA: IN PROGRESS...
	private static final String SCOPE = "";
	private static final String RESPONSE_TYPE = "";
	private static final String REDIRECT_URI = "http://localhost:3000/my_providers";
	private static final String REDIRECT_AUTHORIZATION_URI = "http://localhost:8080/authorization";
	private static final String STATE = "";
	private static final String CLIENT_ID = "1617987675182583";
	private static final String SECRET_ID = "ac40ff181ed97d1cac92246170d36c71";
	private static final String AUTHORIZATION_URL = "";
	private static final String ACCESS_TOKEN_URL = "";
	private static final String GRANT_TYPE = "";
	private static final String API_URI = "";
	private static final String AUTHORIZATION_CODE_PARAMETER = "";
	private static final String ACCESS_TOKEN_PARAMETER = "";
	
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
