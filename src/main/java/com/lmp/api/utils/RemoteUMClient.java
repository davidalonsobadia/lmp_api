package com.lmp.api.utils;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HTTPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.wso2.carbon.authenticator.stub.AuthenticationAdminStub;
import org.wso2.carbon.um.ws.api.WSUserStoreManager;
import org.wso2.carbon.user.core.UserStoreException;

/**
 * This demonstrates how to use remote user management API to add, delete and read users.
 */
@Component
public class RemoteUMClient {

    private static String serverUrl;
    private static String username;
    private static String password;

    private AuthenticationAdminStub authstub = null;
    private ConfigurationContext ctx;
    public WSUserStoreManager remoteUserStoreManager = null;
    
    /**
     * Initialization of environment
     *
     * @throws Exception
     */
    
    
    @Autowired
    public RemoteUMClient(
    		@Value("${remote.server.url}") String serverUrl, 
    		@Value("${remote.server.name}") String username, 
    		@Value("${remote.server.password}") String password) throws Exception {
    	
    	RemoteUMClient.serverUrl = serverUrl;
    	RemoteUMClient.username = username;
    	RemoteUMClient.password = password;
        ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
        String authEPR = serverUrl + "AuthenticationAdmin";
        authstub = new AuthenticationAdminStub(ctx, authEPR);
    }
    
    /**
     * Authenticate to carbon as admin user and obtain the authentication cookie
     *
     * @return
     * @throws Exception
     */
    public String login() throws Exception {
    	String authCookie = null;
        boolean loggedIn = authstub.login(username, password, null);
        if (loggedIn) {
            authCookie = (String) authstub._getServiceClient().getServiceContext().getProperty(HTTPConstants.COOKIE_STRING);
        }
        return authCookie;
    }

    /**
     * create web service client for RemoteUserStoreManager service from the wrapper api provided
     * in carbon - remote-usermgt component
     *
     * @throws UserStoreException, Exception
     */
    public void createRemoteUserStoreManager() throws UserStoreException, Exception {
    	String authCookie;
    	// Login as admin user and obtain the cookie 
        authCookie = login();
        remoteUserStoreManager = new WSUserStoreManager(serverUrl, authCookie, ctx);
    }
}
