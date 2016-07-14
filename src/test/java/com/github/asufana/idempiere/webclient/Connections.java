package com.github.asufana.idempiere.webclient;

import org.idempiere.webservice.client.base.LoginRequest;
import org.idempiere.webservice.client.net.WebServiceConnection;

public class Connections {
    
    private final static String logonId = "SuperUser";
    private final static String logonPw = "System";
    private final static Integer clientId = 11;
    private final static Integer orgId = 0;
    private final static Integer roleId = 102;
    private final static Integer stageId = 2;
    
    private final static String baseUrl = "http://192.168.56.42:8080";
    private final static Integer requestTimeout = 5000;
    private final static Integer numOfAttempts = 3;
    private final static Integer attemptTimeout = 5000;
    private final static String userAgentName = "idempiere-java-webclient";
    
    //login settings
    public static LoginRequest loginRequest() {
        final LoginRequest login = new LoginRequest();
        login.setUser(logonId);
        login.setPass(logonPw);
        login.setClientID(clientId);
        login.setOrgID(orgId);
        login.setRoleID(roleId);
        login.setStage(stageId);
        return login;
    }
    
    //connection settings
    public static WebServiceConnection webServiceConnection() {
        final WebServiceConnection client = new WebServiceConnection();
        client.setUrl(baseUrl);
        client.setTimeout(requestTimeout);
        client.setAttempts(numOfAttempts);
        client.setAttemptsTimeout(attemptTimeout);
        client.setAppName(userAgentName);
        return client;
    }
    
}
