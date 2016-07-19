package com.lmp.api.model;

import com.google.api.client.http.HttpRequestInitializer;

public interface ProviderOauthObject {

	public String getAccessTokenParameter();
	public String getScope();
	public String getResponseType();
	public String getRedirectUri();
	public String getRedirectAuthorizationUri();
	public String getState();
	public String getClientId();
	public String getSecretId();
	public String getAuthorizationUrl();
	public String getAccessTokenUrl();
	public String getGrantType();
	public String getApiUri();
	public String getAuthorizationCodeParameter();
	
	public HttpRequestInitializer getRequestInitializer();

}
