package com.lmp.api.model;

import java.io.IOException;

import org.springframework.security.crypto.codec.Base64;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;

public class FitbitOauth implements ProviderOauthObject {
	
	// STRAVA DATA:
	
	// Parameters for building the authorization code request
	private static final String AUTHORIZATION_URL = "https://www.fitbit.com/oauth2/authorize";
	private static final String REDIRECT_AUTHORIZATION_URI = "http://localhost:8080/authorization";
	private static final String CLIENT_ID = "227VVY";
	private static final String RESPONSE_TYPE = "code";
	private static final String STATE = "DCEeFWf45A53sdfKef424";
	private static final String SCOPE = "activity profile weight heartrate settings location sleep nutrition social";
	
	// Parameters for building the access token request
	private static final String ACCESS_TOKEN_URL = "https://api.fitbit.com/oauth2/token";
	private static final String SECRET_ID = "c1535c8ba7f9442f34db7cbec263a93e";
	private static final String AUTHORIZATION_CODE_PARAMETER = "code";
	
	//Other
	private static final String REDIRECT_URI = "http://localhost:3000/new_provider";
	
	//Parameters to rule the API
	private static final String GRANT_TYPE = "authorization_code";
	private static final String API_URI = "https://api.fitbit.com/1/user/-/profile.json";
	private static final String ACCESS_TOKEN_PARAMETER = "access_token";
	
	
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