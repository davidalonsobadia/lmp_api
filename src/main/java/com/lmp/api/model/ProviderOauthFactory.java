package com.lmp.api.model;

public class ProviderOauthFactory {
	
	public ProviderOauthObject getProviderOauthObject(String ProviderOauthName){
		
		if(ProviderOauthName.equalsIgnoreCase("LinkedIn")){		
			return new LinkedInOauth();
		} else if(ProviderOauthName.equalsIgnoreCase("Facebook")){		
			return new FacebookOauth();
		} else if(ProviderOauthName.equalsIgnoreCase("Strava")){		
			return new StravaOAuth();
		} else if(ProviderOauthName.equalsIgnoreCase("Fitbit")){		
			return new FitbitOauth();
		} 
		return null;
	}

}
