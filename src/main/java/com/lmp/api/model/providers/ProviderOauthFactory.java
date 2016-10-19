package com.lmp.api.model.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProviderOauthFactory {
	
	@Autowired
	private LinkedInOauth linkedInOauth;
	@Autowired
	private FacebookOauth facebookOauth;
	@Autowired
	private StravaOAuth stravaOAuth;
	@Autowired
	private FitbitOauth fitbitOauth;
	
	public ProviderOauthObject getProviderOauthObject(String ProviderOauthName){
		
		if(ProviderOauthName.equalsIgnoreCase("LinkedIn")){		
			return linkedInOauth;
		} else if(ProviderOauthName.equalsIgnoreCase("Facebook")){		
			return facebookOauth;
		} else if(ProviderOauthName.equalsIgnoreCase("Strava")){		
			return stravaOAuth;
		} else if(ProviderOauthName.equalsIgnoreCase("Fitbit")){		
			return fitbitOauth;
		} 
		return null;
	}

}
